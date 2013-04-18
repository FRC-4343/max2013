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

    // Pistons
    public final static byte CLIMBING_PISTON_SOLENOID_ONE = 3;
    public final static byte CLIMBING_PISTON_SOLENOID_TWO = 4;
    public final static boolean CLIMBING_PISTON_EXTENDED_BY_DEFAULT = true;


    // Constants
    public final static double DEFAULT_LAUNCHER_SPEED = 0.4;
    public final static double AXIS_COMPENSATION = 0.8;
    public final static double ALTERNATE_COMPENSATION = 0.5;
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
    public final static byte TRIANGLE = 13;
    public final static byte CIRCLE = 14;
    public final static byte CROSS = 15;
    public final static byte SQUARE = 16;
    public final static byte L1 = 11;
    public final static byte R1 = 12;
    public final static byte L2 = 9;
    public final static byte R2 = 10;
    public final static byte SELECT = 1;
    public final static byte L3 = 2;
    public final static byte R3 = 3;
    public final static byte START = 4;
    public final static byte DPAD_UP = 5;
    public final static byte DPAD_DOWN = 7;
    public final static byte PSBUTTON = 17;
    // Joystick
    public final static byte INDEX_AND_FIRE = R1;
    public final static byte MANUAL_EJECT = R2;
    public final static byte LAUNCHER_SPEED_DECREASE = L2;
    public final static byte LAUNCHER_SPEED_INCREASE = L1;
    public final static byte LAUNCHER_MOTOR_ENABLE = START;
    public final static byte LAUNCHER_MOTOR_DISABLE = SELECT;
    public final static byte EXTEND_CLIMBING_PISTONS = DPAD_UP;
    public final static byte RETRACT_CLIMBING_PISTONS = DPAD_DOWN;
    public final static byte EJECT_STUCK_FRISBEE = SQUARE;
}
