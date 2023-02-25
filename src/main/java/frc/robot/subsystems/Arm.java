// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  private CANSparkMax m_armRaiserMotor; 
  private CANSparkMax m_armExtenderMotor;

  private DigitalInput extensionLimitSwitch;
  private DigitalInput retractionLimitSwitch;
  private DigitalInput raisingLimitSwitch;
  private DigitalInput loweringLimitSwitch;

  private RelativeEncoder elevationEncoder;
  private RelativeEncoder extensionEncoder;


  /** Creates a new ExampleSubsystem. */
  public Arm() {
    // TODO: make mechanical mount these on there
    raisingLimitSwitch = new DigitalInput(ArmConstants.UpperLimitSwitchID);
    loweringLimitSwitch = new DigitalInput(ArmConstants.LowerLimitSwitchID);
    extensionLimitSwitch = new DigitalInput(ArmConstants.ExtensionLimitSwitchID);
    retractionLimitSwitch = new DigitalInput(ArmConstants.RetractionLimitSwitchID);

    m_armRaiserMotor = new CANSparkMax(ArmConstants.ArmExtendMotorID, MotorType.kBrushless);
    m_armExtenderMotor = new CANSparkMax(ArmConstants.ArmExtendMotorID, MotorType.kBrushless);
  
    elevationEncoder = m_armRaiserMotor.getEncoder();
    extensionEncoder = m_armExtenderMotor.getEncoder();
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce( 
        () -> {
          /* one-time action goes here */
        });
  }

  public boolean isOverExtending() {
    return extensionLimitSwitch.get();
  }

  public boolean isOverRetracting() {
    return retractionLimitSwitch.get();
  }

  public boolean isOverRaising() {
    return raisingLimitSwitch.get();
  }

  public boolean isOverLowering() {
    return loweringLimitSwitch.get();
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
    if (isOverRaising()) {
      m_armRaiserMotor.set(0);
    } else {
      m_armRaiserMotor.set(ArmConstants.armLiftingSpeed);
    }
  }

  public void lowerArm() {
    if (isOverLowering()) {
      m_armRaiserMotor.set(0);
    } else {
      m_armRaiserMotor.set(-ArmConstants.armLiftingSpeed);
    }
  }

  public void extendArm() {
    if (isOverExtending()) {
      m_armExtenderMotor.set(0);
    } else {
      m_armExtenderMotor.set(ArmConstants.armExtensionSpeed);
    } 
  }

  public void retractArm() {
    if (isOverRetracting()) {
      m_armExtenderMotor.set(0);
    } else {
      m_armExtenderMotor.set(-ArmConstants.armExtensionSpeed);
    }
  }

  @Override
   
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}