package frc.robot.commands;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;

public class Paths {
    public static Trajectory edgeToCubePlatform1 () {
 
        // place cone goes here
        return TrajectoryGenerator.generateTrajectory(
          List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
          new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
          new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
          new Pose2d(0.9, 16, Rotation2d.fromDegrees(-90))),
          new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
        }
          //grab cube goes here
          public static Trajectory edgeToCubePlatform2 () {
          return TrajectoryGenerator.generateTrajectory(
          List.of(new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
          new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
          new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(-90)), 
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(-90)),
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(180)),
          new Pose2d(1.9, 0.0, Rotation2d.fromDegrees(180))),
          new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
          }
         //place cube goes here 
         public static Trajectory edgeToCubePlatform3 () {
          return TrajectoryGenerator.generateTrajectory(
          List.of(new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(180)),
          new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(-90)),
          new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(-90)),
          new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(0)),
          new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0))),
          new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
          }
    

      // place cone goes here
    public static Trajectory edgeToCubeNoPlatform1 () {
      return TrajectoryGenerator.generateTrajectory(
        List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(-90))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
    }
        //grab cube goes here
        public static Trajectory edgeToCubeNoPlatform2 () {
          return TrajectoryGenerator.generateTrajectory(
       List.of( new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(-90)), 
        new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(1.9, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(1.9, 0.0, Rotation2d.fromDegrees(180))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
        }
        //place cube goes here
  

    // place cone goes here
  public static Trajectory edgeToConeNoPlatform1 () {
      return TrajectoryGenerator.generateTrajectory(
        List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
        new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
        new Pose2d(0.9, 16, Rotation2d.fromDegrees(-90))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
        //grab cone goes here
        public static Trajectory edgeToConeNoPlatform2 () {
          return TrajectoryGenerator.generateTrajectory(
        List.of(new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
        new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(-90)), 
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(180))),
        new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
        }  
        //place cone goes here


        // place cone goes here
  public static Trajectory edgeToConePlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(0.9, 16, Rotation2d.fromDegrees(-90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
    }
      //grab cone goes here
      public static Trajectory edgeToConePlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0.9, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(0.9, 0.5, Rotation2d.fromDegrees(-90)), 
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(180)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      }
      //place cone goes here
      public static Trajectory edgeToConePlatform3 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(7.9, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(7.9, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  


      //WARNIG THIS GOES OVER THE PLATFORM TO GRAB THE PIECE.


      // place cube goes here
  public static Trajectory midToCubePlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(-90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cube goes here
      public static Trajectory midToCubePlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(-90)), 
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(180)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      }
      //place cube goes here
      public static Trajectory midToCubePlatform3 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(2.8, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  


  // place cube goes here
  public static Trajectory midToCubeNoPlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(-90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cube goes here
      public static Trajectory midToCubeNoPlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(0)), 
      new Pose2d(1, 0.5, Rotation2d.fromDegrees(-90)), 
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(-90)),
      new Pose2d(4.16, 0.5, Rotation2d.fromDegrees(180)),
      new Pose2d(2.8, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }  
  //place cube goes here

  
  // place cube goes here
  public static Trajectory midToConePlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(-90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cone goes here
      public static Trajectory midToConePlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1, 16, Rotation2d.fromDegrees(180)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      }
      //place cone goes here 
      public static Trajectory midToConePlatform3 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(1, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(-90)),
        new Pose2d(0.0, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(0.0, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      
  } 


  // place cube goes here
  public static Trajectory midToConeNoPlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(-90)), 
      new Pose2d(1, 16, Rotation2d.fromDegrees(-90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cone goes here
      public static Trajectory midToConeNoPlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1, 16, Rotation2d.fromDegrees(180)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
       } 
      //place cone goes here 

      //end of going over platform

      // place cone goes here
  public static Trajectory EndToConePlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cone goes here
      public static Trajectory EndToConePlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(-1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(180)),
      new Pose2d(-6.75, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      }
        //place cone goes here
        public static Trajectory EndToConePlatform3 () {
          return TrajectoryGenerator.generateTrajectory(
        List.of(new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }


  // place cone goes here
  public static Trajectory EndToConeNoPlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cone goes here
      public static Trajectory EndToConeNoPlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(-1.75, 16, Rotation2d.fromDegrees(0)), 
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(0)),
      new Pose2d(-1.75, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(90)),
      new Pose2d(-6.75, 0.5, Rotation2d.fromDegrees(180)),
      new Pose2d(-6.75, 0.0, Rotation2d.fromDegrees(180))),    
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
    //place cone goes here


    // place cone goes here
  public static Trajectory EndToCubePlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(-1.75, 16, Rotation2d.fromDegrees(90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cube goes here
      public static Trajectory EndToCubePlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(-1.75, 16, Rotation2d.fromDegrees(180)), 
      new Pose2d(-1, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
      }
        //place cube goes here
        public static Trajectory EndToCubePlatform3 () {
          return TrajectoryGenerator.generateTrajectory(
        List.of(new Pose2d(-1, 0.5, Rotation2d.fromDegrees(180)),
        new Pose2d(-1, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(90)),
        new Pose2d(-6.25, 0.5, Rotation2d.fromDegrees(0)),
        new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0)),
      new Pose2d(-6.25, 8.16, Rotation2d.fromDegrees(0))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }


  // place cone goes here
  public static Trajectory EndToCubeNoPlatform1 () {
    return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(0)),
      new Pose2d(0, 16, Rotation2d.fromDegrees(90)), 
      new Pose2d(1.75, 16, Rotation2d.fromDegrees(90))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
      //grab cube goes here
      public static Trajectory EndToCubeNoPlatform2 () {
        return TrajectoryGenerator.generateTrajectory(
      List.of(new Pose2d(1.75, 16, Rotation2d.fromDegrees(180)), 
      new Pose2d(1, 0.0, Rotation2d.fromDegrees(180))),
      new TrajectoryConfig(Units.feetToMeters(1.0), Units.feetToMeters(1.0)));
  }
  //place cube goes here
}

//        new Pose2d(0, 0, Rotation2d.fromDegrees(0)),      copy this
