// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** An example command that uses an example subsystem. */
public class RaiseArmToSetpoint extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Arm arm;
  double setpoint = 0;
  double tolerance = ArmConstants.pidTolerance;

  /**
   * Creates a new ExampleCommand.
   *
   * @param arm The subsystem used by this command.
*/

  public RaiseArmToSetpoint(Arm arm, double setpoint) {
    this(arm, setpoint, 1.5);
  }

  public RaiseArmToSetpoint(Arm arm, double setpoint, double tolerance) {
    this.arm = arm;
    this.setpoint = setpoint;
    this.tolerance = tolerance;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ArmConstants.pidController.setSetpoint(setpoint);
    ArmConstants.pidController.setTolerance(tolerance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    putDataToSmartDashboard();

    double feedForwardCalc = ArmConstants.armFeedForward.calculate(arm.getArmPosition(), 0.5);
    double pidCalc = ArmConstants.pidController.calculate(arm.getArmPosition());
    arm.setRaiserVoltage(feedForwardCalc + pidCalc);
  }

  public void putDataToSmartDashboard(){
    SmartDashboard.putNumber("Arm", arm.getArmPosition());
    SmartDashboard.putNumber("Error", ArmConstants.pidController.getPositionError());
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