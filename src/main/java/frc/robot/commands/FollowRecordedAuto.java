package frc.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DataStructure;
import frc.robot.subsystems.DriveTrain;

public class FollowRecordedAuto extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    DriveTrain drivetrain;

    int leftIndex = 0;
    int rightIndex = 0;
    double[][] drivetrainLeft = DataStructure.drivetrainLeft;
    double[][] drivetrainRight = DataStructure.drivetrainRight;

    
    public FollowRecordedAuto(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double leftSpeed1 = drivetrainLeft[leftIndex][0];
        double leftSpeed2 = drivetrainLeft[leftIndex][1];
    
        double rightSpeed1 = drivetrainRight[rightIndex][0];
        double rightSpeed2 = drivetrainRight[rightIndex][1];
    
        drivetrain.setLeft1Speed(leftSpeed1);
        drivetrain.setLeft2Speed(leftSpeed2);
        drivetrain.setRight1Speed(rightSpeed1);
        drivetrain.setRight2Speed(rightSpeed2);

        leftIndex++;
        rightIndex++;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // drivetrain.enableSafety();
        drivetrain.stopMoving();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return leftIndex >= drivetrainLeft.length-1 || rightIndex >= drivetrainRight.length-1;
    }
}
