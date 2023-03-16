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
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

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


  /** Creates a new ExampleSubsystem. */
  public Arm() {
    // TODO: make mechanical mount these on there
    // raisingLimitSwitch = new DigitalInput(ArmConstants.UpperLimitSwitchID);
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
  }

  public boolean isOverExtending() {
    // return extensionLimitSwitch.get();
    return false;
  }

  public boolean isOverRetracting() {
    // return retractionLimitSwitch.get();
    return false;
  }

  public boolean isOverRaising() {
    // return raisingLimitSwitch.get();
    return false;
  }

  public boolean isOverLowering() {
    // return loweringLimitSwitch.get();
    return false;
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
    double armSpeed = isOverRaising() ? 0:ArmConstants.armLiftingSpeed;
    m_armRaiserMotor.set(armSpeed);
  }

  public void lowerArm() {
    double armSpeed = isOverLowering() ? 0:ArmConstants.armFallingSpeed;
    m_armRaiserMotor.set(-armSpeed);
  }

  public void extendArm() {
    double speed = isOverExtending() ? 0:ArmConstants.armExtensionSpeed;
    m_armExtenderMotor.set(-speed);
  }

  public void retractArm() {
    double speed = isOverRetracting() ? 0:ArmConstants.armExtensionSpeed;
    m_armExtenderMotor.set(speed);
  }

  public void stopExtension(){m_armExtenderMotor.set(0);}
  public void stopRetraction(){m_armExtenderMotor.set(0);}
  public void stopRaising(){m_armRaiserMotor.set(0);}
  public void stopLowering(){m_armRaiserMotor.set(0);}

  public double getTorque(){
    return Units.lbsToKilograms(20) * 9.81 * Units.inchesToMeters(46);
  }

  public void counterTorque(){
    m_armRaiserMotor.set(0.05);
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

  public double getArmPosition(){
    return elevationEncoder.getPosition();
  }

  public void testInit(){
    elevationEncoder.setPosition(0);

  }

  // ONLY testing
  public void testPeriodic(){
    double p = SmartDashboard.getNumber("P", 0.16);
    // double i = SmartDashboard.getNumber("i", 0);
    double d = SmartDashboard.getNumber("d", 0.12);

    double s = SmartDashboard.getNumber("s", 0.0000001);
    double g = SmartDashboard.getNumber("g", 1.61);
    double v = SmartDashboard.getNumber("v", 2.63);
    double a = SmartDashboard.getNumber("a", 0.15);

    ArmConstants.pidController.setP(p);
    ArmConstants.pidController.setD(d);
    ArmConstants.pidController.setSetpoint(54);

    armFeedforward = new ArmFeedforward(s, g, v);

    SmartDashboard.putNumber("Arm", getArmPosition());
    SmartDashboard.putNumber("Error", ArmConstants.pidController.getPositionError());

    double feedForwardCalc = ArmConstants.armFeedForward.calculate(getArmPosition(), 0.5);
    double pidCalc = ArmConstants.pidController.calculate(getArmPosition());
    setRaiserVoltage(feedForwardCalc + pidCalc);
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
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}