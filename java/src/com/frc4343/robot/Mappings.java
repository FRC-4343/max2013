package com.frc4343.robot;

/**
 * The Mappings is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Mappings {
    // Prevents instantiation.
    private Mappings() {
    }
    // Define the motors used to move the robot.
    public static final byte LEFT_MOTOR = 1;
    public static final byte RIGHT_MOTOR = 2;
    // Define the pickup and trigger motors.
    public static final byte PICKUP_MOTOR = 3;
    public static final byte TRIGGER_MOTOR = 4;
    // Define the bridge arm and winder motors
    public static final byte BRIDGE_MOTOR = 5;
    public static final byte WINDER_MOTOR = 6;
    // Define the arms used to move the kinect.
    public static final byte LEFT_KINECT_ARM = 1;
    public static final byte RIGHT_KINECT_ARM = 2;
    // Define the gyro and range finder i/o ports.
    public static final byte GYRO_PORT = 1;
    public static final byte ROTATE_SWITCH_PORT = 3;
    public static final byte BALL_SWITCH_PORT = 4;
    public static final byte TRIGGER_SWITCH_PORT = 5;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    public static final byte RANGE_FINDER_PORT = 6;
    public static final byte CAMERA_LIGHT_RELAY = 1;
}
