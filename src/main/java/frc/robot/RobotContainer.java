// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.BrakeOff;
import frc.robot.commands.BrakeOn;
import frc.robot.commands.ExtendArm;
import frc.robot.commands.ExtendArmByPins;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeReverse;
import frc.robot.commands.LowerArm;
import frc.robot.commands.RaiseArm;
import frc.robot.commands.RetractArm;
import frc.robot.commands.SetSpeedLimit;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Brake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDLights;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Arm arm = new Arm();
  private final Intake intake = new Intake();
  private final Brake brake = new Brake();
  private final LEDLights ledLights = new LEDLights();

  private final RaiseArm raiseArm = new RaiseArm(arm);
  private final LowerArm lowerArm = new LowerArm(arm);
  private final ExtendArm extendArm = new ExtendArm(arm);
  private final RetractArm retractArm = new RetractArm(arm);

  private final IntakeOn intakeOn = new IntakeOn(intake);
  private final IntakeReverse intakeReverse = new IntakeReverse(intake);

  private final BrakeOn brakeOn = new BrakeOn(brake);
  private final BrakeOff brakeOff = new BrakeOff(brake);

  private final ExtendArmByPins extendArmByPins = new ExtendArmByPins(arm, 36);

  Joystick leftJoystick;
  Joystick rightJoystick;
  XboxController operatorController;

  public DriveTrain drivetrain;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    drivetrain = new DriveTrain();
    setupIO();
    // setupDriveTrainCommand();
    configureBindings();
  }

  /** Instantiate Joysticks and Controllers */
  private void setupIO() {
    leftJoystick = new Joystick(OperatorConstants.leftJoystickPort);
    rightJoystick = new Joystick(OperatorConstants.rightJoystickPort);
    operatorController = new XboxController(OperatorConstants.operatorControllerPort);
  }

  /** Create Default Command so Joysticks ALWAYS control drivetrain */
  private CommandBase getArcadeDriveCommand(){
    return new RunCommand(
      () -> drivetrain.arcadeDrive(
          Math.abs(leftJoystick.getY()) <= .1 ? 0 : leftJoystick.getY(),
          Math.abs(leftJoystick.getX()) <= .1 ? 0 : leftJoystick.getX()),
      drivetrain);
  }

  private CommandBase getTankDriveCommand(){
    return new RunCommand(
      () -> drivetrain.move(
          leftJoystick.getY(),
          rightJoystick.getY()),
      drivetrain);
  }

  public void setupDriveTrainCommand() {
    CommandBase driveCommand = getTankDriveCommand();
    drivetrain.setDefaultCommand(driveCommand);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    JoystickButton XboxButtonA = new JoystickButton(operatorController, XboxController.Button.kA.value);
    JoystickButton XboxButtonB = new JoystickButton(operatorController, XboxController.Button.kB.value);
    JoystickButton XboxButtonX = new JoystickButton(operatorController, XboxController.Button.kX.value);
    JoystickButton XboxButtonY = new JoystickButton(operatorController, XboxController.Button.kY.value);
    JoystickButton XboxButtonMap = new JoystickButton(operatorController, XboxController.Button.kStart.value);
    JoystickButton XboxButtonPause = new JoystickButton(operatorController, XboxController.Button.kBack.value);

    JoystickButton XboxLeftBumper = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
    JoystickButton XboxRightBumper = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

    JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
    JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);

    Trigger rightButtonThree = new JoystickButton(rightJoystick, 3);
    Trigger leftButtonThree = new JoystickButton(leftJoystick, 3);

    rightButtonThree.or(leftButtonThree).whileTrue(new RunCommand(() ->
      drivetrain.arcadeDrive(Math.max(leftJoystick.getY(), rightJoystick.getY()), 0),
      drivetrain));

    Trigger XboxUpPad = new Trigger(() -> operatorController.getPOV() == 0);
    Trigger XboxRightPad = new Trigger(() -> operatorController.getPOV() == 90);
    Trigger XboxDownPad = new Trigger(() -> operatorController.getPOV() == 180);
    Trigger XboxLeftPad = new Trigger(() -> operatorController.getPOV() == 270);

    Trigger XboxLeftTrigger = new Trigger(() -> operatorController.getLeftTriggerAxis() > 0.5);
    Trigger XboxRightTrigger = new Trigger(() -> operatorController.getRightTriggerAxis() > 0.5);

    (leftTrigger.and(rightTrigger)).whileTrue(new SetSpeedLimit(drivetrain));
    (leftTrigger.or(rightTrigger)).whileTrue(new SetSpeedLimit(0.75, drivetrain));

    XboxButtonY.toggleOnTrue(ledLights.turnOnYellowCommand());
    XboxButtonX.toggleOnTrue(ledLights.turnOnPurpleCommand());
    (XboxButtonX.and(XboxButtonY)).onTrue(ledLights.turnOffLEDsCommand());

    XboxButtonA.whileTrue(intakeOn);
    XboxButtonB.whileTrue(intakeReverse);

    XboxLeftTrigger.whileTrue(lowerArm);    
    XboxRightTrigger.whileTrue(raiseArm);

    // XboxUpPad.onTrue(brakeOn).onFalse(brakeOff);
    XboxButtonMap.whileTrue(brakeOn);
    XboxButtonPause.whileTrue(brakeOff);

    XboxUpPad.whileTrue(extendArm);
    XboxDownPad.whileTrue(retractArm);
    
    // XboxRightBumper.whileFalse(brakeOff);
    // XboxRightBumper.whileTrue(brakeOn);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    drivetrain.resetSensors();
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(.1, .1);
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      new Pose2d(0, 0, new Rotation2d()),
      List.of(),
      new Pose2d(1, 0, new Rotation2d()),
      trajectoryConfig
    );
    return Commands.run(
      () -> drivetrain.arcadeDrive(0.1, 0)
    );
    // return new SequentialCommandGroup(
    //   Commands.runOnce(
    //     () -> drivetrain.resetSensors(),
    //     drivetrain
    //   ),
    //   new TurnToAngle(90, drivetrain)
    // );
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
  }
}
