// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  boolean syncWheels = false;

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
    DriveTrainConstants.kS, 
    DriveTrainConstants.kV,
    DriveTrainConstants.kA
  );

  DifferentialDriveOdometry odometry; 
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
    Units.inchesToMeters(DriveTrainConstants.wheelWidthInches)
  );

  public DriveTrain() {
    // brakeServo = new Servo(DriveTrainConstants.servoID);

    setupMotors();
    setupDriveTrainSensors();
    setPositionConversion();

    leftMotorGroup = new MotorControllerGroup(leftMotors);
    rightMotorGroup = new MotorControllerGroup(rightMotors);

    difDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
 
    odometry = new DifferentialDriveOdometry(
      navx.getRotation2d(),
      leftEncoder.getPosition(),
      rightEncoder.getPosition()
    );

    // TODO: cleaner way to handle limits
    setSpeedLimit(0.5);
  }

  public float getYawRotation(){
    return navx.getYaw();
  }

  public void setSyncWheels(boolean syncWheels) {
    this.syncWheels = syncWheels;
  }

  public boolean areWheelsSync() {
    return this.syncWheels;
  }

  /** Instantiate NavX and Left/Right Encoders */
  private void setupDriveTrainSensors(){
    /*
    IMPORTANT NOTE: don't pass in a parameter while making the navx. 
    Just use the default constructor. I2C.kMXP port cause issues. SPI doesn't.
    */
    navx = new AHRS();
    leftEncoder = leftMotors[0].getEncoder();
    rightEncoder = rightMotors[0].getEncoder();
  }
  
  /** This function drives the robot using whatever method the driver is most comfortable with.
   * This should NOT be used with anything other than user inputs.
   * 
   * Positive speed is backwards; Negative speed is forwards
   */
  public void move(double leftSpeed, double rightSpeed) {
    difDrive.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    difDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double linearSpeed, double angleSpeed){
    difDrive.arcadeDrive(linearSpeed, angleSpeed);
  }

  public void resetSpeedLimit(){ difDrive.setMaxOutput(1); }
  public void setSpeedLimit(double limit){ difDrive.setMaxOutput(limit); }

  // TODO: watch out for this type of usage
  public void stopMoving(){ difDrive.setMaxOutput(0); }

  // Brakes
  // public void activateBrakes(){
  //   stopMoving();
  //   brakeServo.set(1);
  // }

  // public boolean isInBrakeMode() {
  //   return brakeServo.get() == 1;
  // }

  // public void deactivateBrakes(){
  //   resetSpeedLimit();
  //   brakeServo.set(0);
  // }

  // Sensors + Odometry
  public void resetSensors(){
    navx.reset();
    navx.resetDisplacement();
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

  /** Get Robot Speeds */
  public DifferentialDriveWheelSpeeds getDifferentialDriveWheelSpeeds(){
    double leftMetersPerSecond = leftEncoder.getVelocity();
    double rightMetersPerSecond = rightEncoder.getVelocity();
    return new DifferentialDriveWheelSpeeds(leftMetersPerSecond, rightMetersPerSecond);
  }
  
  /** Set Motor Speeds by using Voltage instead of Percent */
  public void tankDriveVolts(double leftVolts, double rightVolts){
   // SmartDashboard.putNumber("Left Volts", leftVolts);
    // SmartDashboard.putNumber("Right Volts", leftVolts);
    // System.out.println(leftVolts);

    leftMotorGroup.setVoltage(leftVolts);
    rightMotorGroup.setVoltage(rightVolts);
    difDrive.feed();
  }

  public void resetOdometry(Pose2d pose){
    odometry.resetPosition(navx.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition(), pose);
  }

  public void setPositionConversion(){
    double doubleMotorFactor = 2; // We multiply by 2 because there are 2 motors running on one side
    double wheelCircumferenceMeters = Units.inchesToMeters(6)*Math.PI;
    double minutesToSecondsFactor = 60;
    double gearRatioConversion = 1.0/DriveTrainConstants.gearRatio;
    double totalPositionConversionFactor = doubleMotorFactor * wheelCircumferenceMeters * gearRatioConversion;

    leftEncoder.setPositionConversionFactor(totalPositionConversionFactor);
    leftEncoder.setVelocityConversionFactor(totalPositionConversionFactor * minutesToSecondsFactor);

    rightEncoder.setPositionConversionFactor(totalPositionConversionFactor);
    rightEncoder.setVelocityConversionFactor(totalPositionConversionFactor * minutesToSecondsFactor);
  }

  /** Create Motor Arrays */
  private void setupMotors() {
    int amountOfLeftMotors = DriveTrainConstants.leftMotorIds.length;
    int amountOfRightMotors = DriveTrainConstants.rightMotorIds.length;

    leftMotors = new CANSparkMax[amountOfLeftMotors];
    rightMotors = new CANSparkMax[amountOfRightMotors];

    // Make Left Sparks from the ports
    for (int i = 0; i < amountOfLeftMotors; i++){
        leftMotors[i] = new CANSparkMax(DriveTrainConstants.leftMotorIds[i], MotorType.kBrushless);
        leftMotors[i].setInverted(true);
        // TODO: not actually use brake mode
        leftMotors[i].setIdleMode(IdleMode.kBrake);
    }

    // Make Right Sparks from the ports
    for (int i = 0; i < amountOfRightMotors; i++){
        rightMotors[i] = new CANSparkMax(DriveTrainConstants.rightMotorIds[i], MotorType.kBrushless);
        rightMotors[i].setInverted(false);
        rightMotors[i].setIdleMode(IdleMode.kBrake);

    }
  }
  
  public double getDistanceX(){
    return navx.getDisplacementX();
  }

  public double getDistanceY(){
    return navx.getDisplacementY();
  }

  public double getDistanceZ(){
    return navx.getDisplacementZ();
  }

  @Override
  public void periodic() {
    // this updates the MotorSafety class with motor info so we don't get errors
    difDrive.feed();

    odometry.update(
      navx.getRotation2d(), 
      leftEncoder.getPosition(), 
      rightEncoder.getPosition()
    );

    // SmartDashboard.putNumber("Angle", getYawRotation());
    // SmartDashboard.putNumber("X", getDistanceX());
    // SmartDashboard.putNumber("Y", getDistanceY());
    // SmartDashboard.putNumber("Z", getDistanceZ());
    // SmartDashboard.putNumber("Left P", leftEncoder.getPosition());
    // SmartDashboard.putNumber("Right P", rightEncoder.getPosition());
    // SmartDashboard.putNumber("V Err 2", leftMotors[1].getEncoder().getVelocity()-leftMotors[0].getEncoder().getVelocity());
    // // SmartDashboard.putNumber("Left V", leftEncoder.getVelocity());
    // // SmartDashboard.putNumber("Right V", rightEncoder.getVelocity());
    // SmartDashboard.putNumber("V Err", rightEncoder.getVelocity()-leftEncoder.getVelocity());
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
