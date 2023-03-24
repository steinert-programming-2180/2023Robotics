// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public final class Autos {
  static double maxVelocityMetersPerSecond = 1;
  static double maxAccelerationMetersPerSecondSq = 1;
  public static enum Score {
    Low,
    Mid,
    High
  }

  public static TrajectoryConfig config = new TrajectoryConfig(
    maxVelocityMetersPerSecond, 
    maxAccelerationMetersPerSecondSq
  );

  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  public CommandBase getAuto1(){
    
    return new SequentialCommandGroup(
      
    );
  }

  /** Create Ramsete Command that Follows a Trajectory */ 
  public static RamseteCommand followTrajectoryCommand(DriveTrain drivetrain, Trajectory trajectory){
    RamseteController ramseteController = new RamseteController();

    RamseteCommand ramseteCommand = new RamseteCommand(
      trajectory, 
      drivetrain::getPose, 
      ramseteController, 
      drivetrain.getFeedForward(), 
      drivetrain.getKinematics(), 
      drivetrain::getDifferentialDriveWheelSpeeds, 
      drivetrain.getPID(), 
      drivetrain.getPID(), 
      drivetrain::tankDriveVolts, 
      drivetrain
    );

    return ramseteCommand;
  }

  // scoreHigh or scoreLow
  // backOut
  // scoreMid, scoreLow, or scoreHigh

  // EXPERIMENTAL: balance
  void generateAutoChooser(){
    SendableChooser<Score> sendableChooser = new SendableChooser<Score>();
    sendableChooser.addOption("Score High", Score.High);
    sendableChooser.addOption("Score Low", Score.Low);

    SendableChooser sendableChooser2 = new SendableChooser<>();
    sendableChooser2.addOption("Back Out", sendableChooser2);
    sendableChooser2.addOption("Do Nothing", sendableChooser2);

    SendableChooser sendableChooser3 = new SendableChooser<>();
    sendableChooser3.addOption("Score Mid", Score.Mid);
    sendableChooser3.addOption("Score Low", Score.Low);
    sendableChooser3.addOption("Score High", Score.High);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
