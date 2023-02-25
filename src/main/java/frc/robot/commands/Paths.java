package frc.robot.commands;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;

public class Paths {
    public static Trajectory edgeToCubePlatform () {
        return TrajectoryGenerator.generateTrajectory(
          // place cone goes here
          List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
          new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
          new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
          new Pose2d(0.9, 16, Rotation2d.fromDegrees(90)),
          //grab cube goes here
          new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
          new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
          new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(90)), 
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(90)),
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(0)),
          new Pose2d(1.9, 0.0, Rotation2d.fromDegrees(0)),
          //place cube goes here
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(0)),
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(90)),
          new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(90)),
          new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(0)),
          new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0))),
          new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
    }

    public static Trajectory edgeToCubeNoPlatform () {
      return TrajectoryGenerator.generateTrajectory(
        // place cone goes here
        List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(90)),
        //grab cube goes here
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(90)), 
        new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(1.9, 0.0, Rotation2d.fromDegrees(0)),
        //place cube goes here
        new Pose2d(1.9, 0.0, Rotation2d.fromDegrees(0))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }

  public static Trajectory edgeToConeNoPlatform () {
      return TrajectoryGenerator.generateTrajectory(
        // place cone goes here
        List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(90)),
        //grab cone goes here
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(90)), 
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0)),
        //place cone goes here
        new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  

  public static Trajectory edgeToConePlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cone goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(0.9, 16, Rotation2d.fromDegrees(90)),
      //grab cone goes here
      new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(90)), 
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0)),
      //place cone goes here
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  
      //WARNIG THIS GOES OVER THE PLATFORM TO GRAB THE PIECE.
  public static Trajectory midToCubePlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cube goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(90)),
      //grab cube goes here
      new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(90)), 
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0)),
      //place cube goes here
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  

  public static Trajectory midToCubeNoPlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cube goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(90)),
      //grab cube goes here
      new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(90)), 
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0)),
      //place cube goes here
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  

  public static Trajectory midToConePlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cube goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(90)),
      //grab cone goes here
      new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(0)),
      //place cone goes here 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(1, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  } 

  public static Trajectory midToConeNoPlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cube goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(90)),
      //grab cone goes here
      new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(0)),
      //place cone goes here 
      new Pose2d(1, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  } 

  public static Trajectory EndToConePlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cone goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(-90)),
      //grab cone goes here
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.75, 0.0, Rotation2d.fromDegrees(0)),
        //place cone goes here
        new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }

  public static Trajectory EndToConeNoPlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cone goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(-90)),
      //grab cone goes here
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.75, 0.0, Rotation2d.fromDegrees(0)),
        //place cone goes here
      new Pose2d(6.75, 0.0, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }

  public static Trajectory EndToCubePlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cone goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(-90)),
      //grab cube goes here
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(-1, 0.0, Rotation2d.fromDegrees(0)),
        //place cube goes here
        new Pose2d(-1, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-1, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }

  public static Trajectory EndToCubeNoPlatform () {
    return TrajectoryGenerator.generateTrajectory(
      // place cone goes here
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1.75, 16, Rotation2d.fromDegrees(90)),
      //grab cube goes here
      new Pose2d(1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(0)),
        //place cube goes here
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
}

//        new Pose2d(0, 0, Rotation2d.fromDegrees(0)),      copy this
