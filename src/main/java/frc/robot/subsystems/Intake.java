
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  CANSparkMax clawMotor;

  public Intake() {
    clawMotor = new CANSparkMax(IntakeConstants.intakeMotorID, MotorType.kBrushless);
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
  public void IntakeRevese() {
    clawMotor.set(-IntakeConstants.intakeSpeed);
  }
  
  public void intakeOn() {
    clawMotor.set(IntakeConstants.intakeSpeed);
  }

  public void intakeStop() {
    clawMotor.set(0);
  }
  
  // TODO: how are we sensing if the claw has grabbed something? Encoders? Test out encoders.
  // public boolean hasFieldElement(){
  //   return true;
  // }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }

}
