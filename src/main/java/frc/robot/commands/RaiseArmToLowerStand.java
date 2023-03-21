// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** An example command that uses an example subsystem. */
public class RaiseArmToLowerStand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Arm arm;
  double setpoint = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param arm The subsystem used by this command.
*/

  public RaiseArmToLowerStand(Arm arm, double setpoint) {
    this.arm = arm;
    this.setpoint = setpoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ArmConstants.pidController.setSetpoint(setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Arm", arm.getArmPosition());
    SmartDashboard.putNumber("Error", ArmConstants.pidController.getPositionError());
    SmartDashboard.putBoolean("Arm Raising FInished", isFinished());


    double feedForwardCalc = ArmConstants.armFeedForward.calculate(arm.getArmPosition(), 0.5);
    double pidCalc = ArmConstants.pidController.calculate(arm.getArmPosition());
    arm.setRaiserVoltage(feedForwardCalc + pidCalc);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.counterTorque();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ArmConstants.pidController.atSetpoint();
  }
}
