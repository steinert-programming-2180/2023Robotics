
package frc.robot.subsystems;

import java.lang.ModuleLayer.Controller;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;

public class Intake extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  CANSparkMax leftMotor;
  CANSparkMax rightMotor;
  XboxController xbox;
  double speed = .5;


  public Intake() {
    leftMotor = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor = new CANSparkMax(1, MotorType.kBrushless);
    xbox = new XboxController(0);
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
  public void intake(){
    while(xbox.getAButton()){
      leftMotor.set(speed);
      rightMotor.set(speed);
    } 

  }
  public void outtake(){
    while(xbox.getBButton()){
      leftMotor.set(speed*-1);
      rightMotor.set(speed*-1);
    } 

  }


  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }

  public void intakeOn(){
  }
}
