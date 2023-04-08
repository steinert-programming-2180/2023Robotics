// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveStraightPID extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain drivetrain;
  PIDController pidController;
  double leftSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightPID(DriveTrain drivetrain, double leftSpeed) {
    this.drivetrain = drivetrain;
    this.leftSpeed = leftSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.setDefaultNumber("P", 0);
    SmartDashboard.setDefaultNumber("I", 0);
    SmartDashboard.setDefaultNumber("D", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double p = SmartDashboard.getNumber("P", 0);
    double i = SmartDashboard.getNumber("I", 0);
    double d = SmartDashboard.getNumber("D", 0);
    pidController = new PIDController(p, i, d);

    drivetrain.tankDrive(leftSpeed, pidController.calculate(leftSpeed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
