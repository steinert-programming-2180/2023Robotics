// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
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

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
    DriveTrainConstants.KS, 
    DriveTrainConstants.KV,
    DriveTrainConstants.KA
  );

  DifferentialDriveOdometry odometry; 
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
    Units.inchesToMeters(6)
  );

  public DriveTrain() {
    // brakeServo = new Servo(DriveTrainConstants.servoID);

    setupMotors();
    leftMotorGroup = new MotorControllerGroup(leftMotors);
    rightMotorGroup = new MotorControllerGroup(rightMotors);

    difDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

    navx = new AHRS(Port.kMXP);
    leftEncoder = leftMotors[0].getEncoder();
    rightEncoder = rightMotors[0].getEncoder();
    
    odometry = new DifferentialDriveOdometry(
      navx.getRotation2d(),
      leftEncoder.getPosition(),
      rightEncoder.getPosition()
    );

    // TODO: cleaner way to handle limits
    setSpeedLimit(0.5);
  }
  
  public void move(double leftSpeed, double rightSpeed) {
    difDrive.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void arcadeMove(double leftSpeed, double angleSpeed){
    difDrive.arcadeDrive(leftSpeed, angleSpeed);
  }

  public void resetSpeedLimit(){ difDrive.setMaxOutput(1); }
  public void setSpeedLimit(double limit){ difDrive.setMaxOutput(limit); }
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

  public SimpleMotorFeedforward getFeedForward(){ return feedforward; }
  public DifferentialDriveKinematics getKinematics(){ return kinematics; }
  public PIDController getPID(){
    return new PIDController(
      DriveTrainConstants.P, 
      DriveTrainConstants.I, 
      DriveTrainConstants.D
    );
  }
  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }
  public DifferentialDriveWheelSpeeds getDifferentialDriveWheelSpeeds(){
    return kinematics.toWheelSpeeds(new ChassisSpeeds(1.0, 1.0, 0.0));
  }
  public void tankDriveVolts(double leftVolts, double rightVolts){
    leftMotorGroup.setVoltage(leftVolts);
    rightMotorGroup.setVoltage(rightVolts);
    difDrive.feed();
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
        leftMotors[i].setInverted(true);
    }

    // Make Right Sparks from the ports
    for (int i = 0; i < amountOfRightMotors; i++){
        rightMotors[i] = new CANSparkMax(DriveTrainConstants.rightMotorIds[i], MotorType.kBrushless);
        rightMotors[i].setInverted(false);
    }
  }

  @Override
  public void periodic() {
    difDrive.feed();
    odometry.update(
      navx.getRotation2d(), 
      leftEncoder.getPosition(), 
      rightEncoder.getPosition()
    );
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
