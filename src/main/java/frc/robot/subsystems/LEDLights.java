package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDLights extends SubsystemBase {
    Solenoid yellowLED;
    Solenoid purpleLED;

    public LEDLights() {
        yellowLED = new Solenoid(PneumaticsModuleType.REVPH, 0);
        purpleLED = new Solenoid(PneumaticsModuleType.REVPH, 0);
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

    public void setPurpleLEDOn(){
        yellowLED.set(false);
        purpleLED.set(true);
    }

    public void setYellowLEDOn(){
        yellowLED.set(true);
        purpleLED.set(false);
    }

    public void setAllLEDsOff(){
        yellowLED.set(false);
        purpleLED.set(false);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {

    }
}
