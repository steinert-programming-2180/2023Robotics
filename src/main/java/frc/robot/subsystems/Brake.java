// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BreackConstants;

public class Brake extends SubsystemBase {
  Servo brakeServo;
  
  /** Creates a new ExampleSubsystem. */
  public Brake() {
    brakeServo = new Servo(BreackConstants.ServoId);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase ServoCommand() {
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("Brake", getRaw());
    // SmartDashboard.putNumber("Brake 2", brakeServo.getPosition());
    // SmartDashboard.putNumber("Brake 3", brakeServo.getAngle());

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void engageBrakes(){
    brakeServo.setRaw(255);
    // brakeServo.set(1);
  }

  public void disengageBrakes(){
    brakeServo.setRaw(0);
  }
  
  public double getRaw(){
    return brakeServo.getRaw();
  }
}
