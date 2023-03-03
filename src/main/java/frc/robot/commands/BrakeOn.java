// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.BreackConstants;
import frc.robot.subsystems.Brake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class BrakeOn extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Brake brake;

  /**
   * Creates a new ExampleCommand.
   *
   * @param brake The subsystem used by this command.
   */
  public BrakeOn(Brake brake) {
    this.brake = brake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(brake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    brake.break_acc(BreackConstants.servoCloseAngle);
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
