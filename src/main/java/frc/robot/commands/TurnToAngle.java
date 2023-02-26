package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;

public class TurnToAngle extends PIDCommand{

    public TurnToAngle(double angleDegrees, DriveTrain drivetrain) {
        super(
            new PIDController(
                DriveTrainConstants.P, 
                DriveTrainConstants.I, 
                DriveTrainConstants.D
            ),
            drivetrain::getYawRotation,
            angleDegrees,
            output -> drivetrain.arcadeDrive(0, output),
            drivetrain
        );

        getController().setTolerance(3);
    }

    @Override
    public boolean isFinished(){
        return getController().atSetpoint();
    }
}
