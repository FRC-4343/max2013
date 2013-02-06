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
    // The motors used to move the robot.
    public static final byte LEFT_MOTORS = 1;
    public static final byte RIGHT_MOTORS = 2;
    // Other various motors.
    public static final byte LAUNCHER_MOTOR_A = 3;
    public static final byte LAUNCHER_MOTOR_B = 4;
    public static final byte INDEX_MOTOR = 5;
    public static final byte LOADER_MOTOR = 6;
    public static final byte LIFT_MOTOR = 7;
    // The arms used to move the kinect.
    public static final byte LEFT_KINECT_ARM = 1;
    public static final byte RIGHT_KINECT_ARM = 2;
    // The gyro, range finder, and various other I/O ports.
    public static final byte GYRO_PORT = 1;
    public static final byte ENCODER_PORT_A = 2;
    public static final byte ENCODER_PORT_B = 3;
    public static final byte RANGE_FINDER_PORT = 4;
    public static final byte DISC_DETECTION_SWITCH_PORT = 5;
    // The relay controlling the current to the camera light
    public static final byte CAMERA_LIGHT_RELAY = 1;
    // The port to which the joystick is connected to.
    public static final int JOYSTICK_PORT = 1;
    // The solenoid ports.
    public static final int SOLENOID_ONE = 0;
    public static final int SOLENOID_TWO = 1;
    // All of the various button mappings on the joystick.
    public static final int CAMERA_LIGHT_BUTTON = 8;
    public static final int CAMERA_TARGET_BUTTON = 9;
    public static final int SOLENOID_ONE_BUTTON = 10;
    public static final int SOLENOID_TWO_BUTTON = 11;
}
