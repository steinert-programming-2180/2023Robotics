package frc.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import edu.wpi.first.wpilibj.Filesystem;
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
    JSONObject jsonObject;

    boolean isReady = false;

    public FollowRecordedAuto(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        try {
            String myString = Files.readString(java.nio.file.Paths.get(Filesystem.getDeployDirectory() + "/Auto.json"));
            jsonObject = new JSONObject(myString);
            System.out.println(jsonObject.get("Drivetrain Left"));
            isReady = true;
            
            // drivetrainLeft = (double[][]) jsonObject.get("Drivetrain Left");
            // drivetrainRight = (double[][]) jsonObject.get("Drivetrain Right");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(!isReady) return;

        double leftSpeed1 = jsonObject.getJSONArray("Drivetrain Left").getJSONArray(leftIndex).getDouble(0);
        double leftSpeed2 = jsonObject.getJSONArray("Drivetrain Left").getJSONArray(leftIndex).getDouble(1);
    
        double rightSpeed1 = jsonObject.getJSONArray("Drivetrain Right").getJSONArray(rightIndex).getDouble(0);
        double rightSpeed2 = jsonObject.getJSONArray("Drivetrain Right").getJSONArray(rightIndex).getDouble(1);
    
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
