
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
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  CANSparkMax clawMotor;
  DoubleSolenoid intakeSolenoid;
  private AnalogPotentiometer USensor;
  double USensorPlace;

  public Intake() {
    clawMotor = new CANSparkMax(IntakeConstants.intakeMotorID, MotorType.kBrushless);
    clawMotor.setSmartCurrentLimit(60, 100);
    intakeSolenoid = new DoubleSolenoid(
      PneumaticConstants.pneumaticsModuleType, 
      IntakeConstants.solenoidOpenPort,
      IntakeConstants.solenoidClosePort
    );
    USensor = new AnalogPotentiometer(0);
    // USensorPlace = USensor.get();
  }

  public void closeIntake(){
    intakeSolenoid.set(Value.kReverse);
  }

  public void openIntake(){
    intakeSolenoid.set(Value.kForward);
  }

  public void autoIntake(){
    // if(USensorPlace <= 0.2)
    // {
    // // closeIntake();
    // }
  SmartDashboard.putBoolean("AutoSensor", USensor.get() <= 0.2);
  SmartDashboard.putNumber("USensor", USensor.get());
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
    autoIntake();
  }

  @Override
  public void simulationPeriodic() {
  }

}
