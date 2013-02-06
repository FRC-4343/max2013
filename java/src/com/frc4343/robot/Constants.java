package com.frc4343.robot;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;
import java.util.Vector;

public final class Constants {
    // Prevents instantiation of the class.
    private Constants() {
    }

    // CAMERA
    public static final ResolutionT CAMERA_RESOLUTION = AxisCamera.ResolutionT.k320x240;
    public static final int CAMERA_BRIGHTNESS = 50;
    public static final int CAMERA_COMPRESSION = 80;
    public static final int CAMERA_COLOUR_LEVEL = 100;
    // Tape Reflection HSL values
    public static final int HMIN = 5;
    public static final int HMAX = 50;
    public static final int SMIN = 120;
    public static final int SMAX = 255;
    public static final int LMIN = 120;
    public static final int LMAX = 255;
    // Minumum camera target area.
    public static final int PARTICLE_AREA = 500;
    public static final double CAMERA_TARGET_DEADZONE = 0.1;
    public static final double CAMERA_TURN_TIMEOUT = 0.1;
    // TIMEOUTS
    public static final double WINDING_TIME = 0.3;
    public static final double TRIGGER_TIME = 0.575;
    public static final double BRIDGE_TIME = 1;
    // GYRO
    public static final float Kp = 200;
    // MOTOR VARIABLES
    public static final boolean SAFETY_ENABLED = false; // If this is true, it throws errors but the trigger works.
    public static final double EXPIRATION = 0.01;

    public static String combineStringWithDouble(String string, double doubleVal) {
        return string.concat(Double.toString(doubleVal));
    }

    public static double getDoubleFromVector(Vector vector, int index) {
        return ((Double) vector.elementAt(index)).doubleValue();
    }
}