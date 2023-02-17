package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends CommandBase {
  private DriveTrain driveTrain;
  public DefaultDrive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
  }
}
