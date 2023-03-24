// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** An example command that uses an example subsystem. */
public class DriveForward extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_drivetrain;

  /**
   * Creates a new ExampleCommand.
   *
   * @param driveTrain The subsystem used by this command.
   */
  public DriveForward(DriveTrain driveTrain, double inchesForward) {
    super(
        new PIDController(DriveTrainConstants.P, DriveTrainConstants.I, DriveTrainConstants.D),
        driveTrain::getDistanceY,
        2,
        output -> driveTrain.arcadeDrive(output, 0),
        driveTrain
    );
    driveTrain.resetSensors();
    m_drivetrain = driveTrain;
    // getController().setTolerance(.5);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    //return getController().atSetpoint();
  }
}
