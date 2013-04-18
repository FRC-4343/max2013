package com.frc4343.robot2;

public class Mappings {

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
    public final static byte DEFAULT_LAUNCHER_SPEED = 0.4;
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
