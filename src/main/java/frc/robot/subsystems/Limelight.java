package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;
import frc.robot.Constants.*;

// I would like to preface this by saying that it is almost 11 PM when writing this documentation. 

public class Limelight extends SubsystemBase {

    // Gets our Limelight NetworkTable, api located here:
    // https://docs.limelightvision.io/en/latest/networktables_api.html
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    
    private NetworkTableEntry ta = table.getEntry("ta");
    private NetworkTableEntry tv = table.getEntry("tv");

    private NetworkTableEntry camMode = table.getEntry("camMode");
    private NetworkTableEntry ledMode = table.getEntry("ledMode");
    private NetworkTableEntry pipeline = table.getEntry("israel");

    /**
     * Limelight class for command usage, non-static usage pretty much entirely
     * depricated.
     */
    public Limelight() {
    }

    @Override
    public void periodic() {
        putItems();
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);
        System.out.println(targetOffsetAngle_Vertical);
        
    }

    /**
     * Calculates the horizontal distance from target, as described by
     * {@link https://docs.limelightvision.io/en/latest/cs_estimating_distance.html}
     * 
     * @return Distance from target, in meters because we are scientists first and
     *         Americans second.
     */
    public double getHorizontalDistance() {

        // If no valid target, return -1
        if (!hasTarget()) {
            return -1;
        }

        // Gets our angle from horizontal by adding angle of limelight to angle offset
        double theta = Math.toRadians(ty.getDouble(0) + LimelightConstants.LIME_ANGLE);

        // theta = 0 implies tan(theta) = 0, so to avoid division
        // by zero, return -1 if theta = 0
        if (theta == 0) {
            return -1;
        }

        // Gets delta height from top of target to where the limelight is mounted
        double height = (FieldConstants.TARGET_MAX_HEIGHT - LimelightConstants.LIME_HEIGHT);

        // Returns distance by using trigonometry
        return height / Math.tan(theta);
    }

    /**
     * Checks if we have a target, based off of if we are tracking and we see a
     * target
     * 
     * @return True if we are tracking and seeing a target, false otherwise
     */
    public boolean hasTarget() {
        return (isTracking() && tv.getDouble(0) == 1);
    }

    /**
     * Calculates the straight-on distance from target to limelight, inspired by
     * {@link https://docs.limelightvision.io/en/latest/cs_estimating_distance.html}
     * 
     * @return Distance from target, in meters because we are scientists first and
     *         Americans second.
     */
    public double getStraightDistance() {

        // If no valid target, return -1
        if (!hasTarget()) {
            return -1;
        }

        // Gets our angle from horizontal by adding angle of limelight to angle offset
        double theta = Math.toRadians(ty.getDouble(0) + LimelightConstants.LIME_ANGLE);

        // theta = 0 implies sin(theta) = 0, so to avoid division
        // by zero, return -1 if theta = 0
        if (theta == 0) {
            return -1;
        }

        // Gets delta height from top of target to where the limelight is mounted
        double height = Constants.inchesToMeters(FieldConstants.TARGET_MAX_HEIGHT - LimelightConstants.LIME_HEIGHT);

        // Returns distance by using trigonometry
        return height / Math.sin(theta);
    }

    /**
     * Determines if we are in tracking mode based on what our camera and lights are
     * doing
     * 
     * @return True if we are tracking, false otherwise
     */
    public boolean isTracking() {
        return (getCameraMode() == 0) && (getLightsMode() == 0);
    }

    /**
     * Puts x angle of target, y angle of target, and area of target. If distance is
     * valid, put that, too.
     */
    public void putItems() {

        SmartDashboard.putBoolean("Has Target?", hasTarget());
        SmartDashboard.putBoolean("Is Tracking?", isTracking());

        // If there is no target, we just end here
        if (!hasTarget()) {
            return;
        }

        // Gets x, y, area. If invalid, defaults to 0.0
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // Puts x, y, area to SmartDashboard
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);

        // Gets the horizontal distance from the target
         System.out.println(getHorizontalDistance());

        // If this distance is positive, i.e. didn't trigger a failsafe, put it to
        // SmartDashboard
    }

    /**
     * Sets the current pipeline to the parameter.
     * 
     * @param pipeline The pipeline we wish to use
     */
    public void setPipeline(double mode) {
        pipeline.setNumber(mode);
    }

    /**
     * Returns the current pipeline in use.
     * 
     * @return The pipeline in use right now
     */
    public double getPipeline() {
        return (double) pipeline.getNumber(0);
    }

    /**
     * Sets the current camera mode to the camera,
     * 0 = pipeline mode,
     * 1 = driver mode
     * 
     * @param mode The camera mode we wish to use
     */
    public void setCameraMode(double mode) {
        camMode.setNumber(mode);
    }

    /**
     * Gets the current camera mode.
     * 
     * @return The current camera mode
     */
    public double getCameraMode() {
        return (double) camMode.getNumber(0);
    }

    /**
     * Sets the LED mode of the limelight,
     * 0 = pipeline camera,
     * 1 = force off,
     * 2 = force blink,
     * 3 = force on
     * 
     * @param mode The mode of the LED we want to use
     */
    public void setLightsMode(double mode) {
        ledMode.setNumber(mode);
    }

    /**
     * Gets the current LED mode of the limelight.
     * 
     * @return The current LED mode,
     *         0 = pipeline camera,
     *         1 = force off,
     *         2 = force blink,
     *         3 = force on
     */
    public double getLightsMode() {
        return (double) ledMode.getNumber(0);
    }

    /**
     * Returns the limelight table to be accessed elsewhere, if necessary.
     * 
     * @return The NetworkTable for the limelight, API found here
     *         {@link https://docs.limelightvision.io/en/latest/networktables_api.html}
     */
    public NetworkTable getLimelightTable() {
        return table;
    }

    /**
     * Swaps the LED Status between 0 = pipeline and 1 = force off.
     */
    public void swapLights() {
        // The ? statement handles the "if our current LED status is not 0 or 1"
        setLightsMode(1 - (getLightsMode() < 2 ? getLightsMode() : 1));
    }

    /**
     * Swaps the Camera Status between 0 = pipeline camera and 1 = drive camera
     */
    public void swapCamera() {
        setCameraMode(1 - getCameraMode());
    }
    
}