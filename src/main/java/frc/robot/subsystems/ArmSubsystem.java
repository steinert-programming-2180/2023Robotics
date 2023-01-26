
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  CANSparkMax leftMotor;
  CANSparkMax rightMotor;

  public ArmSubsystem() {
    leftMotor = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor = new CANSparkMax(1, MotorType.kBrushless);
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


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void intakeOn(){
    leftMotor.set(1);
    rightMotor.set(-1);
  }
}
