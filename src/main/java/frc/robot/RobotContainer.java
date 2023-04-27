// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FollowRecordedAuto;
import frc.robot.commands.RecordAutoPath;
import frc.robot.commands.TimedCommand;
import frc.robot.commands.Arm.ExtendArm;
import frc.robot.commands.Arm.ExtendArmByPins;
import frc.robot.commands.Arm.LowerArm;
import frc.robot.commands.Arm.RaiseArm;
import frc.robot.commands.Arm.RaiseArmToSetpoint;
import frc.robot.commands.Arm.RetractArm;
import frc.robot.commands.DriveTrain.BrakeOff;
import frc.robot.commands.DriveTrain.BrakeOn;
import frc.robot.commands.DriveTrain.DriveForward;
import frc.robot.commands.DriveTrain.SetSpeedLimit;
import frc.robot.commands.DriveTrain.TravelForward;
import frc.robot.commands.DriveTrain.TurnToAngle;
import frc.robot.commands.Intake.CloseIntake;
import frc.robot.commands.Intake.IntakeOn;
import frc.robot.commands.Intake.IntakeReverse;
import frc.robot.commands.Intake.OpenIntake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Brake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDLights;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DataLogIterator;
import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
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
  ExampleSubsystem exampleSubsystem = new ExampleSubsystem(); // create this for "null" requirements
  // The robot's subsystems and commands are defined here...
  public DriveTrain drivetrain = new DriveTrain();

  public final Arm arm = new Arm();
  public final Intake intake = new Intake();
  private final Brake brake = new Brake();
  private final LEDLights ledLights = new LEDLights();

  private final RaiseArm raiseArm = new RaiseArm(arm);
  private final LowerArm lowerArm = new LowerArm(arm);
  private final ExtendArm extendArm = new ExtendArm(arm);
  private final RetractArm retractArm = new RetractArm(arm);

  private final IntakeOn intakeOn = new IntakeOn(intake);
  private final IntakeReverse intakeReverse = new IntakeReverse(intake);
  private final CloseIntake closeIntake = new CloseIntake(intake);
  private final OpenIntake openIntake = new OpenIntake(intake);

  private final BrakeOn brakeOn = new BrakeOn(brake);
  private final BrakeOff brakeOff = new BrakeOff(brake);
  
  // Joysticks and Buttons
  Joystick leftJoystick = new Joystick(OperatorConstants.leftJoystickPort);
  Joystick rightJoystick = new Joystick(OperatorConstants.rightJoystickPort);
  XboxController operatorController = new XboxController(OperatorConstants.operatorControllerPort);

  JoystickButton XboxButtonA = new JoystickButton(operatorController, XboxController.Button.kA.value);
  JoystickButton XboxButtonB = new JoystickButton(operatorController, XboxController.Button.kB.value);
  JoystickButton XboxButtonX = new JoystickButton(operatorController, XboxController.Button.kX.value);
  JoystickButton XboxButtonY = new JoystickButton(operatorController, XboxController.Button.kY.value);
  JoystickButton XboxButtonPause = new JoystickButton(operatorController, XboxController.Button.kStart.value); // looks like three lines on controller
  JoystickButton XboxButtonMap = new JoystickButton(operatorController, XboxController.Button.kBack.value); // looks like two squares on controller
  JoystickButton XboxLeftStickButton = new JoystickButton(operatorController, XboxController.Button.kLeftStick.value);
  JoystickButton XboxRightStickButton = new JoystickButton(operatorController, XboxController.Button.kRightStick.value);

  JoystickButton XboxLeftBumper = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
  JoystickButton XboxRightBumper = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

  JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);
  JoystickButton rightTrigger = new JoystickButton(rightJoystick, 1);

  JoystickButton rightButtonThree = new JoystickButton(rightJoystick, 3);
  JoystickButton leftButtonThree = new JoystickButton(leftJoystick, 3);

  // private final ExtendArmByPins extendArmByPins = new ExtendArmByPins(arm, 36);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // setupDriveTrainCommand();
    Autos.generateAutoChooser();
    configureBindings();
  }

  /** Create Default Command so Joysticks ALWAYS control drivetrain */
  private CommandBase getArcadeDriveCommand() {
    return new RunCommand(
        () -> drivetrain.arcadeDrive(
            Math.abs(leftJoystick.getY()) <= .1 ? 0 : leftJoystick.getY(),
            Math.abs(leftJoystick.getX()) <= .1 ? 0 : leftJoystick.getX()),
        drivetrain);
  }

  private CommandBase getTankDriveCommand() {
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
    rightButtonThree.and(leftButtonThree).whileTrue(new SetSpeedLimit(0.15, drivetrain));

    rightButtonThree.or(leftButtonThree).whileTrue(
      new RunCommand(
        () -> drivetrain.tankDrive(rightJoystick.getY(), 1.05*rightJoystick.getY()),
        drivetrain
      )
    );

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

    XboxLeftPad.onTrue(openIntake);
    XboxRightPad.onTrue(closeIntake);

    XboxUpPad.whileTrue(extendArm);
    XboxDownPad.whileTrue(retractArm);

    // XboxButtonPause.onTrue(brakeOn);

    // Auto Arm Controls
    XboxLeftBumper.onTrue(
      new RaiseArmToSetpoint(arm, ArmConstants.midFloorArmEncoderValue)
    );
    XboxButtonMap.onTrue(
      new RaiseArmToSetpoint(arm, ArmConstants.substationArmEncoderValue)
    );
    // XboxLeftStickButton.onTrue(
    //   new RaiseArmToSetpoint(arm, ArmConstants.bottomFloorArmEncoderValue)
    // );
    XboxLeftStickButton.onTrue(
      new RecordAutoPath(drivetrain, arm, intake, XboxRightStickButton)  
    );

    XboxRightBumper.onTrue(
      new RaiseArmToSetpoint(arm, ArmConstants.highFloorArmEncoderValue)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    drivetrain.resetSensors();

    Command emptyCommand = new TimedCommand(
      new RunCommand(
        () -> {},
        exampleSubsystem
      ),
      0.05
    );
    Command raiseToHighStand = new TimedCommand(raiseArm, 3);
    Command extendToHigh = new TimedCommand(extendArm, 1.6);
    Command goToFloor = new TimedCommand(lowerArm, 2);
    Command retractArm = new TimedCommand(
      new RunCommand(
        () -> arm.retractArm(),
        arm
      ),
    1.4);
    Command driveBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.75, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      4
    );

    // TODO: might have to use empty command
    SequentialCommandGroup autoSequence = new SequentialCommandGroup();

    Autos.Score firstScore = Autos.getFirstScore();
    switch (firstScore) {
      case High:
        autoSequence.addCommands(raiseToHighStand);
        autoSequence.addCommands(extendToHigh);
        autoSequence.addCommands(new OpenIntake(intake));
        autoSequence.addCommands(retractArm);
        autoSequence.addCommands(new CloseIntake(intake));
        autoSequence.addCommands(goToFloor);
        break;
      case Mid:
        break;
      case Low:
        autoSequence.addCommands(new RaiseArmToSetpoint(arm, 25));
        autoSequence.addCommands(new IntakeReverse(intake, .05));
        break;
      case None:
        break;
      default:
        break;
    }

    if(Autos.willDoAutoMobility()) autoSequence.addCommands(driveBackOut);

    return autoSequence;
  }

  public Command newAuto(){
    arm.resetEncoders();
    
    Command raiseToHighStand = new RaiseArmToSetpoint(arm, 67, 3);
    Command extendToHigh = new TimedCommand(extendArm, 1.6);
    Command raiseAboveStand = new RaiseArmToSetpoint(arm, arm.getArmPosition()+2);
    Command goToFloor = new RaiseArmToSetpoint(arm, ArmConstants.bottomFloorArmEncoderValue, 5);
    Command retractArm = new TimedCommand(
      new RunCommand(
        () -> arm.retractArm(),
        arm
      ),
    1);
    Command driveForward = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(-0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );
    Command goSlightBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );
    Command driveBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.75, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      4
    );


    return new SequentialCommandGroup(
      raiseToHighStand,
      driveForward,
      extendToHigh,
      new OpenIntake(intake),
      retractArm,
      new CloseIntake(intake),
      goSlightBackOut,
      goToFloor,
      driveBackOut
    );
  }

  public Command twoAuto(){
    arm.resetEncoders();
    
    Command raiseToHighStand = new RaiseArmToSetpoint(arm, 67, 3);
    Command extendToHigh = new TimedCommand(extendArm, 1.6);
    Command raiseAboveStand = new RaiseArmToSetpoint(arm, arm.getArmPosition()+2);
    Command goToFloor = new RaiseArmToSetpoint(arm, ArmConstants.bottomFloorArmEncoderValue, 5);
    Command retractArm = new TimedCommand(
      new RunCommand(
        () -> arm.retractArm(),
        arm
      ),
    1);
    Command driveForward = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(-0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );
    Command goSlightBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );
    Command rotate =  new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0, .75),
        () -> drivetrain.arcadeDrive(0, 0),
        drivetrain
      ),
      .95
    );
    Command driveBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.75, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      3.6
    );


    return new SequentialCommandGroup(
      raiseToHighStand,
      new TimedCommand(new ExampleCommand(exampleSubsystem), .5),
      driveForward,
      extendToHigh,
      new OpenIntake(intake),
      retractArm,
      new CloseIntake(intake),
      new TimedCommand(new ExampleCommand(exampleSubsystem), .5),
      goSlightBackOut,
      goToFloor,
      new TimedCommand(new ExampleCommand(exampleSubsystem), .5),
      driveBackOut
    );
  }

  public Command testAuto(){
    arm.resetEncoders();
    Command driveForward = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(-0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );
    Command goSlightBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.4, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      .5
    );

    Command driveBackOut = new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(0.75, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      3
    );

    Command randomTimer = new TimedCommand(new ExampleCommand(exampleSubsystem), 2);


    return new SequentialCommandGroup(
      driveForward,
      new TimedCommand(new ExampleCommand(exampleSubsystem), 2),
      goSlightBackOut,
      driveBackOut
    );
  }

  public Command testOutDriveTrainEncoders(){
    drivetrain.resetSensors();

     return new TimedCommand(
      new StartEndCommand(
        () -> drivetrain.arcadeDrive(-0.5, 0), 
        () -> drivetrain.arcadeDrive(0, 0), 
        drivetrain
      ), 
      4
    );
  }

  public Command otherTest(){
    return new TravelForward(drivetrain, Units.inchesToMeters(120));
  }

  public CommandBase getDSFollow(){
    return new FollowRecordedAuto(drivetrain);
  }
}
