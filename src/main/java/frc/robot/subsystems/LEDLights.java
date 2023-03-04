package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LEDLights extends SubsystemBase {
    Solenoid yellowLED;
    Solenoid purpleLED;

    public LEDLights() {
        yellowLED = new Solenoid(PneumaticsModuleType.REVPH, LEDConstants.yellowPort);
        purpleLED = new Solenoid(PneumaticsModuleType.REVPH, LEDConstants.purplePort);
    }

    /**
     * Example command factory method.
     *
     * @return a command
     */
    public CommandBase turnOffLEDsCommand(){
        return run( () -> setAllLEDsOff());
    }
    public CommandBase turnOnYellowCommand() {
        return run( () -> {
            setYellowLEDOn();
        } );
    }

    public CommandBase turnOnPurpleCommand(){
        return run( () -> {
            setPurpleLEDOn();
        } );
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
