// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** An example command that uses an example subsystem. */
public class RecordAutoPath extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain drivetrain;
  private final Arm arm;
  private final Intake intake;
  private final JoystickButton stopRecordingButton;
  private HashMap<String, ArrayList> f = new HashMap<String, ArrayList>();

  public RecordAutoPath(DriveTrain drivetrain, Arm arm, Intake intake, JoystickButton stopRecording) {
    this.drivetrain = drivetrain;
    this.arm = arm;
    this.intake = intake;
    this.stopRecordingButton = stopRecording;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    f.put("Drivetrain Left", new ArrayList<ArrayList<Double>>());
    f.put("Drivetrain Right", new ArrayList<ArrayList<Double>>());
    f.put("Arm Raising", new ArrayList<Double>());
    f.put("Arm Extension", new ArrayList<Double>());
    f.put("Intake", new ArrayList<Double>());
    f.put("isIntakeClosed", new ArrayList<Boolean>());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] leftMotors = {drivetrain.getLeft1Speed(), drivetrain.getLeft2Speed()};
    double[] rightMotors = {drivetrain.getRight1Speed(), drivetrain.getRight2Speed()};

    f.get("Drivetrain Left").add(leftMotors);
    f.get("Drivetrain Right").add(rightMotors);
    f.get("Arm Raising").add(arm.getArmRaisingMotorSpeed());
    f.get("Arm Extension").add(arm.getArmExtensionMotorSpeed());
    f.get("Intake").add(intake.getIntakeSpeed());
    f.get("isIntakeClosed").add(intake.isClosed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    String fileName = "Auto.json";
    
    String filePath = Filesystem.getDeployDirectory() + "/" + fileName;
    
    JSONObject jsonObject = new JSONObject(f);
    System.out.println(jsonObject.toString());

    try {
        Files.write(Paths.get(filePath), jsonObject.toString().getBytes());
        System.out.println("Successfully Wrote File");
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stopRecordingButton.getAsBoolean();
  }
}
