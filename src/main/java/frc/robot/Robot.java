// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.PneumaticConstants;
import frc.robot.subsystems.Limelight;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Limelight limelight;
  private RobotContainer m_robotContainer;
  PIDController pidController = new PIDController(DriveTrainConstants.P, DriveTrainConstants.I, DriveTrainConstants.D);
  PIDCommand pidCommand;

  PneumaticHub pneumaticHub;
  Compressor compressor;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    pneumaticHub = new PneumaticHub();
    compressor = new Compressor(1, PneumaticConstants.pneumaticsModuleType);
    compressor.enableAnalog(PneumaticConstants.idealPSI-1, PneumaticConstants.idealPSI);

    SmartDashboard.putNumber("p", ArmConstants.pidController.getP());
    SmartDashboard.putNumber("i", ArmConstants.pidController.getI());
    SmartDashboard.putNumber("d", ArmConstants.pidController.getD());


    SmartDashboard.putNumber("s", ArmConstants.armFeedForward.ks);
    SmartDashboard.putNumber("g", ArmConstants.armFeedForward.kg);
    SmartDashboard.putNumber("v", ArmConstants.armFeedForward.kv);
    SmartDashboard.putNumber("a", ArmConstants.armFeedForward.ka);

    CameraServer.startAutomaticCapture(0); // Starts Camera for the Claw
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // m_robotContainer.drivetrain.resetSensors();
    // pidController.setSetpoint(0.12);

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    // pidCommand = new PIDCommand(
    //   pidController, 
    //   m_robotContainer.drivetrain::getDistanceX, 
    //   2, 
    //   output -> m_robotContainer.drivetrain.arcadeDrive(output, 0),
    //   m_robotContainer.drivetrain
    // );
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // double speed = pidController.calculate(m_robotContainer.drivetrain.getDistanceX());
    // SmartDashboard.putNumber("speed", speed);
    // m_robotContainer.drivetrain.tankDrive(speed, Math.min(1.05*speed, 1));
  }

  @Override
  public void teleopInit() {
    limelight = new Limelight();

    // TODO: remove this after testing
    m_robotContainer.arm.resetEncoders();
    
    m_robotContainer.drivetrain.arcadeDrive(-0, 0);
    m_robotContainer.setupDriveTrainCommand();
    m_robotContainer.intake.closeIntake();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out. 
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    m_robotContainer.arm.testInit();
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    m_robotContainer.arm.testPeriodic();
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
