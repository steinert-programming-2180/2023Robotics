// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public final class Autos {
  double maxVelocityMetersPerSecond = 1;
  double maxAccelerationMetersPerSecondSq = 1;

  TrajectoryConfig config = new TrajectoryConfig(
    maxVelocityMetersPerSecond, 
    maxAccelerationMetersPerSecondSq
  );

  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  /** Create Ramsete Command that Follows a Trajectory */ 
  private RamseteCommand followTrajectoryCommand(DriveTrain drivetrain, Trajectory trajectory){
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

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
