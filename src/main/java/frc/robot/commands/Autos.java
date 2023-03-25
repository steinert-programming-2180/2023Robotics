// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public final class Autos {
  static SendableChooser<Score> firstScore;
  static SendableChooser<Score> secondScore;
  static String autoMobilityTitle = "Auto Mobility"; // this is here so no silly typo breaks our auto
  public static enum Score {
    None,
    Low,
    Mid,
    High
  }

  public static void generateAutoChooser(){
    firstScore = new SendableChooser<Score>();
    firstScore.setDefaultOption("Score High", Score.High);
    firstScore.addOption("Score Low", Score.Low);
    firstScore.addOption("None", Score.None);

    secondScore = new SendableChooser<Score>();
    secondScore.setDefaultOption("None", Score.None);
    secondScore.addOption("Score Mid", Score.Mid);
    secondScore.addOption("Score Low", Score.Low);
    secondScore.addOption("Score High", Score.High);

    SmartDashboard.putData(firstScore);
    SmartDashboard.setDefaultBoolean(autoMobilityTitle, true);
    SmartDashboard.putData(secondScore);
  }

  public static Score getFirstScore(){ return firstScore.getSelected(); }
  public static boolean willDoAutoMobility(){ return SmartDashboard.getBoolean(autoMobilityTitle, false); }
  public static Score getSecondScore(){ return secondScore.getSelected(); }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
