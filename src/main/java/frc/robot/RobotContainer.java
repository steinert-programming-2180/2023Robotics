// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  CommandJoystick leftJoystick;
  CommandJoystick rightJoystick;
  CommandXboxController operatorController;
  
  public DriveTrain drivetrain;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drivetrain = new DriveTrain();
    setupIO();
    setupDriveTrainCommand();
    configureBindings();
  }

  /** Instantiate Joysticks and Controllers */
  private void setupIO(){
    leftJoystick = new CommandJoystick(OperatorConstants.leftJoystickPort);
    rightJoystick = new CommandJoystick(OperatorConstants.rightJoystickPort);
    operatorController = new CommandXboxController(OperatorConstants.operatorControllerPort);
  }

  /** Create Default Command so Joysticks ALWAYS control drivetrain */
  private void setupDriveTrainCommand(){
    RunCommand driveCommand = new RunCommand(
      () -> drivetrain.move(
              leftJoystick.getY(), 
              rightJoystick.getY()
            )
      , drivetrain);

    drivetrain.setDefaultCommand(driveCommand);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    drivetrain.resetSensors();
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(.5, 1);
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      new Pose2d(0, 0, new Rotation2d()),
      List.of(),
      new Pose2d(0, 1, new Rotation2d()),
      trajectoryConfig
    );
    return Autos.followTrajectoryCommand(drivetrain, trajectory);
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
  }
}
