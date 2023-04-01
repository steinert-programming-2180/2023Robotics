// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.TelescopeConstants;

public class Arm extends SubsystemBase {
  private CANSparkMax m_armRaiserMotor; 
  private CANSparkMax m_armExtenderMotor;

  private DigitalInput extensionLimitSwitch;
  private DigitalInput retractionLimitSwitch;
  private DigitalInput raisingLimitSwitch;
  private DigitalInput loweringLimitSwitch;

  private RelativeEncoder elevationEncoder;
  private RelativeEncoder extensionEncoder;
  private ArmFeedforward armFeedforward = new ArmFeedforward(0, 0, 0);

  private double counterArmTorqueSpeed = ArmConstants.counterArmTorqueSpeed;


  /** Creates a new ExampleSubsystem. */
  public Arm() {
    // TODO: make mechanical mount these on there
    raisingLimitSwitch = new DigitalInput(ArmConstants.UpperLimitSwitchID);
    // loweringLimitSwitch = new DigitalInput(ArmConstants.LowerLimitSwitchID);
    // extensionLimitSwitch = new DigitalInput(ArmConstants.ExtensionLimitSwitchID);
    // retractionLimitSwitch = new DigitalInput(ArmConstants.RetractionLimitSwitchID);

    m_armRaiserMotor = new CANSparkMax(ArmConstants.ArmRaiserMotorID, MotorType.kBrushless);
    m_armRaiserMotor.setInverted(true);

    m_armExtenderMotor = new CANSparkMax(ArmConstants.ArmExtendMotorID, MotorType.kBrushless);

    m_armRaiserMotor.setIdleMode(IdleMode.kBrake);
    m_armExtenderMotor.setIdleMode(IdleMode.kBrake);
  
    elevationEncoder = m_armRaiserMotor.getEncoder();
    extensionEncoder = m_armExtenderMotor.getEncoder();
    
    m_armExtenderMotor.setSmartCurrentLimit(40, 40);
    
    resetEncoders();
  }

  private double getPositionConversion(double motorRPM){
    double totalGearRatio = 5 * 3 * 3 * 3;
    double outputRotations = motorRPM / totalGearRatio;
    /* 
    * 1 full rotation = 360
    * .5 rotation = 180
    * .25 rotation = 90
    * 
    */
    return rotationAngleToHeight(outputRotations);
  }

  private double rotationAngleToHeight(double angleInDegrees){
    double angleInRadians = Math.toRadians(angleInDegrees);
    
    double r = ArmConstants.armLengthInInches;
    double yCoordinateFromAngle = r * Math.sin(angleInRadians); 

    double height = yCoordinateFromAngle + r; // add r to make sure height is never below 0
    return height;
  }

  public void resetEncoders() {
    elevationEncoder.setPosition(0);
    extensionEncoder.setPosition(0);
  }

  // TODO: test out arm angle
  // public double getArmAngel() {
  //   return ArmConstants.ArmAngle(elevationEncoder.getPosition());
  // }


  public void raiseArm() {
    m_armRaiserMotor.set(ArmConstants.armLiftingSpeed);
  }

  public void lowerArm() {
    m_armRaiserMotor.set(-ArmConstants.armFallingSpeed);
  }

  public void extendArm() {
    m_armExtenderMotor.set(-ArmConstants.armExtensionSpeed);
  }

  public void retractArm() {
    m_armExtenderMotor.set(ArmConstants.armExtensionSpeed);
  }

  public void stopExtension(){m_armExtenderMotor.set(0);}
  public void stopRetraction(){m_armExtenderMotor.set(0);}
  public void stopRaising(){m_armRaiserMotor.set(0);}
  public void stopLowering(){m_armRaiserMotor.set(0);}

  public double getTorque(){
    return Units.lbsToKilograms(20) * 9.81 * Units.inchesToMeters(46);
  }

  public double getCounterTorqueSpeed(){
    return ArmConstants.counterArmTorqueSpeed;
  }

  public void counterTorque(){
    m_armRaiserMotor.set(getCounterTorqueSpeed());
  }

  public void extendByPins(int amountOfPins){
    
  }

  public double getPositionForPins(int amountOfPins){
    return -23.97604751586914 * amountOfPins/36;
  }

  public double getPinPosition(){
    
    return extensionEncoder.getPosition() / -23.97604751586914 * 36;
  }

  public void setRaiserVoltage(double voltage){
    m_armRaiserMotor.setVoltage(voltage);
  }

  public void setTelescopeVoltage(double voltage) {
    m_armExtenderMotor.setVoltage(voltage);
  }

  public double getArmPosition(){
    return elevationEncoder.getPosition();
  }

  public double getTelescopePosition() {
    return extensionEncoder.getPosition();
  }

  public void testInit(){
    elevationEncoder.setPosition(0);
    extensionEncoder.setPosition(0);
  }

  // ONLY testing
  public void testPeriodic(){
    double p = ArmConstants.pidController.getP();
    double i = ArmConstants.pidController.getI();
    double d = ArmConstants.pidController.getD();

    // double s = ArmConstants.kS;
    // double g = ArmConstants.kG;
    // double v = ArmConstants.kV;
    // double a = ArmConstants.kA;


    ArmConstants.pidController.setP(p);
    ArmConstants.pidController.setI(i);
    ArmConstants.pidController.setD(d);
    ArmConstants.pidController.setSetpoint(54);

    TelescopeConstants.telescopePidController.setSetpoint(24);

    // armFeedforward = new ArmFeedforward(s, g, v, a);

    SmartDashboard.putNumber("Arm", getArmPosition());
    SmartDashboard.putNumber("Error", ArmConstants.pidController.getPositionError());

    SmartDashboard.putNumber("Telescope", getTelescopePosition());
    SmartDashboard.putNumber("Telescope Error", TelescopeConstants.telescopePidController.getPositionError());

    double feedForwardCalc = ArmConstants.armFeedForward.calculate(getArmPosition(), 0.5);
    double pidCalc = ArmConstants.pidController.calculate(getArmPosition());
    setRaiserVoltage(feedForwardCalc + pidCalc);

    double telescopeFeedForwardCalc = TelescopeConstants.telescopeFeedForward.calculate(getTelescopePosition(), 0.5);
    double telescopePidCalc = TelescopeConstants.telescopePidController.calculate(getTelescopePosition());

    double extensionTemperature = m_armExtenderMotor.getMotorTemperature();
    double raisingTemperature = m_armRaiserMotor.getMotorTemperature();

    SmartDashboard.putNumber("Ext Motor", extensionTemperature);
    SmartDashboard.putNumber("Raising Temp", raisingTemperature);

    // if (Math.abs(ArmConstants.pidController.getPositionError()) < 2) {
    //   setTelescopeVoltage((telescopeFeedForwardCalc + telescopePidCalc)*-2);
    //   SmartDashboard.putNumber("Telescope Voltage", (telescopeFeedForwardCalc + telescopePidCalc)*2);
    // }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double extensionTemperature = m_armExtenderMotor.getMotorTemperature();
    double raisingTemperature = m_armRaiserMotor.getMotorTemperature();
    double armPosition = elevationEncoder.getPosition();

    SmartDashboard.putNumber("Ext Motor", extensionTemperature);
    SmartDashboard.putNumber("Raising Temp", raisingTemperature);
    SmartDashboard.putNumber("Arm Pos", armPosition);
    // SmartDashboard.putBoolean("Limit Switch", raisingLimitSwitch.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}