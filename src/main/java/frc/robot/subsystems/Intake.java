
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.PneumaticConstants;

public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  CANSparkMax clawMotor;
  DoubleSolenoid intakeSolenoid;

  public Intake() {
    clawMotor = new CANSparkMax(IntakeConstants.intakeMotorID, MotorType.kBrushless);
    intakeSolenoid = new DoubleSolenoid(
      PneumaticConstants.pneumaticsModuleType, 
      IntakeConstants.solenoidOpenPort,
      IntakeConstants.solenoidClosePort
    );
  }

  public void closeIntake(){
    intakeSolenoid.set(Value.kReverse);
  }

  public void openIntake(){
    intakeSolenoid.set(Value.kForward);
  }

  public void IntakeRevese() {
    IntakeRevese(-IntakeConstants.intakeSpeed);
  }
  public void IntakeRevese(double intakeSpeed) {
    clawMotor.set(-Math.abs(intakeSpeed));
  }
  
  public void intakeOn() {
    intakeOn(IntakeConstants.intakeSpeed);
  }
  public void intakeOn(double intakeSpeed) {
    clawMotor.set(Math.abs(intakeSpeed));
  }

  public void intakeStop() {
    clawMotor.set(IntakeConstants.idleSpeed);
  }
  
  // TODO: how are we sensing if the claw has grabbed something? Encoders? Test out encoders.
  // public boolean hasFieldElement(){
  //   return true;
  // }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Temp", clawMotor.getMotorTemperature());
  }

  @Override
  public void simulationPeriodic() {
  }

}
