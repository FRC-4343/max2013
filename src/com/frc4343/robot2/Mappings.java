package com.frc4343.robot2;

public class Mappings {

    // Robot Mappings
    public final static byte CLIMBING_PISTON_SOLENOID_ONE = 3;
    public final static byte CLIMBING_PISTON_SOLENOID_TWO = 4;
    public final static boolean CLIMBING_PISTON_EXTENDED_BY_DEFAULT = true;
    public final static byte COLOR_CUTOFF = 50;
    // Gyro Mappings
    public final static byte GYRO_PORT = 1;
    public final static double DRIVE_SPEED = 1.0;
    public final static double ROTATE_SPEED = 0.7;
    public final static double DISTANCE_DEADZONE_IN_INCHES = 1;
    public final static double ANGLE_TO_ROTATE_BY = 40;
    public final static double DISTANCE_FROM_WALL_IN_INCHES = 210;
    // Firing Mappings
    public final static byte INDEXER_MOTOR_PORT = 2;
    public final static byte LAUNCHER_MOTOR_PORT = 3;
    public final static byte FIRING_PISTON_SOLENOID_ONE = 1;
    public final static byte FIRING_PISTON_SOLENOID_TWO = 2;
    public final static boolean FIRING_PISTON_EXTENDED_BY_DEFAULT = false;
    public final static byte INDEXER_LIMIT_SWITCH_PORT = 2;
    public final static double DEFAULT_LAUNCHER_MOTOR_SPEED = 0.4;
    public final static double AUTONOMOUS_DELAY_BEFORE_FIRST_SHOT = 2;
    public final static double AUTONOMOUS_DELAY_BETWEEN_EACH_SHOT = 0;
    // Timeouts
    public final static double LOADING_DELAY = 0.5;
    public final static double FRISBEE_FALL_TIMER = 0.5;
    public final static double ACCELERATION_DELAY = 0.0;
    public final static double EXTEND_TIME = 0.3;
    public final static double RETRACT_TIME = 0.2;
    public final static double AUTO_CLIMB_TIME = 119;
    // Drive Mappings
    public final static double PRECISION_COMPENSATION = 0.4;
    public final static double AUTONOMOUS_TIME_SPENT_DRIVING_BACK = 1;
    public final static double AUTONOMOUS_TIME_BEFORE_DRIVING_FORWARD = 1;
    public final static double AUTONOMOUS_TIME_SPENT_DRIVING_FORWARD = 1;
    public final static double AUTONOMOUS_DRIVE_SPEED = 0.6;
    // Button mappings
    public final static byte TRIGGER = 1;
    public final static byte RETRACT_CLIMBING_PISTONS = 2;
    public final static byte EXTEND_CLIMBING_PISTONS = 3;
    public final static byte SPEED_DECREASE = 4;
    public final static byte SPEED_INCREASE = 5;
    public final static byte LAUNCHER_MOTOR_ENABLE = 6;
    public final static byte LAUNCHER_MOTOR_DISABLE = 7;
    public final static byte FLUSH_HOPPER = 8;
    public final static byte MANUAL_EJECT = 9;
    public final static byte ALIGN_TO_CENTER_GOAL_CLOCKWISE = 10;
    public final static byte ALIGN_TO_CENTER_GOAL_COUNTERCLOCKWISE = 11;










    // Joystick
    public final static byte JOYSTICK_COUNT = 2;

    // Solenoids
    public final static byte SOLENOID_COUNT = 4;

    // Firing System
    public final static byte LAUNCH_MOTOR_RELAY_PORT = 3;
    public final static byte INDEX_MOTOR_RELAY_PORT = 2;
    public final static byte INDEX_LIMIT_SWITCH = 2;

    // Compressor
    public final static byte COMPRESSOR_DIGITAL_IO = 1;
    public final static byte COMPRESSOR_RELAY = 1;


    // Constants
    public final static double DEFAULT_LAUNCHER_SPEED = 0.4;
    public final static double AXIS_COMPENSATION = 0.8;
    public final static double INDEXER_TIMEOUT = 1.5;
    // Autonomous
    public final static double AUTOMATIC_CLIMB_TIME = 119.9;
    public final static byte FRISBEES_TO_FIRE = 3;
    public final static double DELAY_BETWEEN_SHOTS = 3;
    public final static double DELAY_BEFORE_FIRST_SHOT = 1;
    public final static double DELAY_TO_PISTON_RETRACTION = 0.3;
    public final static double REVERSE_DURATION = 1.3;
    public final static double ROTATE_DURATION = 0.79;


    // Button Mappings
    // PS3
    final byte TRIANGLE = 13;
    final byte CIRCLE = 14;
    final byte CROSS = 15;
    final byte SQUARE = 16;
    final byte L1 = 11;
    final byte R1 = 12;
    final byte L2 = 9;
    final byte R2 = 10;
    final byte SELECT = 1;
    final byte L3 = 2;
    final byte R3 = 3;
    final byte START = 4;
    final byte DPAD_UP = 5;
    final byte DPAD_DOWN = 7;
    final byte PSBUTTON = 17;
    // Joystick
    final byte INDEX_AND_FIRE = R1;
    final byte FRISBEE_MANUAL_EJECT = R2;
    final byte LAUNCHER_SPEED_DECREASE = L2;
    final byte LAUNCHER_SPEED_INCREASE = L1;
    final byte LAUNCHER_MOTOR_ENABLE = START;
    final byte LAUNCHER_MOTOR_DISABLE = SELECT;
    final byte EXTEND_CLIMBING_PISTONS = DPAD_UP;
    final byte RETRACT_CLIMBING_PISTONS = DPAD_DOWN;
    final byte FRISBEE_STUCK_EJECT = SQUARE;
}
