// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;;

public class DriveTrain extends SubsystemBase {
  DifferentialDrive difDrive;

  CANSparkMax[] leftMotors;
  CANSparkMax[] rightMotors;

  MotorControllerGroup leftMotorGroup;
  MotorControllerGroup rightMotorGroup;

  Servo brakeServo;
  
  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  AHRS navx;
  DifferentialDriveOdometry odometry;

  public DriveTrain() {
    brakeServo = new Servo(DriveTrainConstants.servoID);

    setupMotors();
    leftMotorGroup = new MotorControllerGroup(leftMotors);
    rightMotorGroup = new MotorControllerGroup(rightMotors);

    difDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

    navx = new AHRS(Port.kMXP);
    leftEncoder = leftMotors[0].getEncoder();
    rightEncoder = rightMotors[0].getEncoder();
    odometry = new DifferentialDriveOdometry(navx.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition());
  }
  
  public void move(double leftSpeed, double rightSpeed) {
    difDrive.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void resetSpeedLimit(){ difDrive.setMaxOutput(1); }
  public void stopMoving(){ difDrive.setMaxOutput(0); }

  // Brakes
  public void activateBrakes(){
    stopMoving();
    brakeServo.set(1);
  }

  public void deactivateBrakes(){
    resetSpeedLimit();
    brakeServo.set(0);
  }

  public boolean isInBrakeMode() {
    return brakeServo.get() == 1;
  }
  
  // Sensors + Odometry
  public void resetSensors(){
    navx.reset();
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
    resetOdometry(new Pose2d());
  }

  public void resetOdometry(Pose2d pose){
    odometry.resetPosition(navx.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition(), pose);
  }

  private void setupMotors() {
    int amountOfLeftMotors = DriveTrainConstants.leftMotorIds.length;
    int amountOfRightMotors = DriveTrainConstants.rightMotorIds.length;

    leftMotors = new CANSparkMax[amountOfLeftMotors];
    rightMotors = new CANSparkMax[amountOfRightMotors];

    // Make Left Sparks from the ports
    for (int i = 0; i < amountOfLeftMotors; i++){
        leftMotors[i] = new CANSparkMax(DriveTrainConstants.leftMotorIds[i], MotorType.kBrushless);
        leftMotors[i].setInverted(false);
    }

    // Make Right Sparks from the ports
    for (int i = 0; i < amountOfRightMotors; i++){
        rightMotors[i] = new CANSparkMax(DriveTrainConstants.rightMotorIds[i], MotorType.kBrushless);
        rightMotors[i].setInverted(true);
    }
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
