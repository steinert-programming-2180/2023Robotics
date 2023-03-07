// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int operatorControllerPort = 2;
  }

  public static class LEDConstants{
    public static final int yellowPort = 0;
    public static final int purplePort = 15;
  }

  public static class DriveTrainConstants {
    public static final int servoID = 0;

    public static final double wheelWidthInches = 6;

    public static final int[] leftMotorIds = {3,4};
    public static final int[] rightMotorIds = {1,2};

    public static final double P = 2.3697;
    public static final double I = 0;  
    public static final double D = 0.22582;

    public static final double kS = 0.21077;
    public static final double kV = 0.1066;
    public static final double kA = 0.012185;

    public static final double gearRatio = 15.57/1.0;
  }

  public static class ArmConstants {
    public static final int ArmRaiserMotorID = 5;
    public static final int ArmExtendMotorID = 6;

    public static final int UpperLimitSwitchID = 1;
    public static final int LowerLimitSwitchID = 2;
    public static final int ExtensionLimitSwitchID = 3;
    public static final int RetractionLimitSwitchID = 4;

    public static final double armLiftingSpeed = 0.65; 
    public static final double armFallingSpeed = 0.15;
    public static final double armExtensionSpeed = 0.7; 
  }

  public static class IntakeConstants{
    public static final int intakeMotorID = 7;
    public static final double intakeSpeed = 0.65;
  }

  public static class LimelightConstants {
    public static final int LIME_ANGLE = 0;
    public static final double LIME_HEIGHT = 1.0;
  }

  public static class FieldConstants {
    public static final double TARGET_MAX_HEIGHT = 10.0;
  }

  public static double inchesToMeters(double inches) {
    return 39.37 * inches;
  }

  public static class BreackConstants {
    public static int ServoId=0;
    public static int servoOpenAngle=0;
    public static int servoCloseAngle=90;
  }
}
