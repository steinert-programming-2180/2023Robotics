// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;;

public class DriveTrain extends SubsystemBase {
  CANSparkMax L1, L2, R1, R2;
  DifferentialDrive difDrive;

  MotorControllerGroup LeftMotors;
  MotorControllerGroup RightMotors;
  
  public DriveTrain() {
    L1 = new CANSparkMax(DriveTrainConstants.leftMotorIds[0], MotorType.kBrushless);
    L2 = new CANSparkMax(DriveTrainConstants.leftMotorIds[1], MotorType.kBrushless);

    LeftMotors = new MotorControllerGroup(L1, L2);

    R1 = new CANSparkMax(DriveTrainConstants.rightMotorIds[0], MotorType.kBrushless);
    R2 = new CANSparkMax(DriveTrainConstants.rightMotorIds[1], MotorType.kBrushless);

    RightMotors = new MotorControllerGroup(R1, R2);

    difDrive = new DifferentialDrive(LeftMotors, RightMotors);
  }
  
  public void move(Double LeftSpeed, Double RightSpeed) {
    difDrive.tankDrive(LeftSpeed, RightSpeed);
  }
     
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

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
