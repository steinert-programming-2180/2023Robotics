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
    public static final int kDriverControllerPort = 0;
  }

  public static class ArmConstants {
    public static final int ArmRaiserMotorID = 1;
    public static final int ArmExtendMotorID = 2;

    public static final int UpperLimitSwitchID = 1;
    public static final int LowerLimitSwitchID = 2;
    public static final int ExtensionLimitSwitchID = 3;
    public static final int RetractionLimitSwitchID = 4;

    public static final double armLiftingSpeed = 0.5; 
    public static final double armExtensionSpeed = 0.5; 
  }

  public static class IntakeConstants{
    public static final int intakeMotorID = 0;
    public static final double intakeSpeed = 0.5;
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
    public static int ServoId=1;
    public static int servoOpenAngle=0;
    public static int servoCloseAngle=90;
  }
}
