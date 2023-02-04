// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm extends SubsystemBase {
  private CANSparkMax m_armLifterMotor; 
  private CANSparkMax m_armExtenderMotor;
  private static final int deviceIDOne = 1; 
  private static final int deviceIDTwo = 2;

  /** Creates a new ExampleSubsystem. */
  public Arm() {
    m_armLifterMotor = new CANSparkMax(deviceIDOne, MotorType.kBrushless);
    m_armExtenderMotor = new CANSparkMax(deviceIDTwo, MotorType.kBrushless);
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

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void raiseArm() {
    m_armLifterMotor.set(1);
  }

  public void lowerArm() {
    m_armLifterMotor.set(-1);
  }

  public void extendArm() {
    m_armExtenderMotor.set(1);
  }

  public void retractArm() {
    m_armExtenderMotor.set(-1);
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