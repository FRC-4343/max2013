#ifndef DEFINES_H
#define DEFINES_H

#include "WPILib.h"

// CAMERA
const int CAMERA_BRIGHTNESS = 50;
const int CAMERA_COMPRESSION = 80;
const int CAMERA_COLOUR_LEVEL = 100;

// Tape Reflection HSL values
const int HMIN = 5;
const int HMAX = 50;

const int SMIN = 120;
const int SMAX = 255;

const int LMIN = 120;
const int LMAX = 255;

// Minumum camera target area.
const int PARTICLE_AREA = 500;
const double CAMERA_TARGET_DEADZONE = 0.1;
const double CAMERA_TURN_TIMEOUT = 0.1;

// TIMEOUTS
const double WINDING_TIME = 0.3;
const double TRIGGER_TIME = 0.575;
const double BRIDGE_TIME = 1;

// GYRO
const float Kp = 200;

// MOTOR VARIABLES
const bool SAFETY_ENABLED = false; // If this is true, it throws errors but the trigger works.
const double EXPIRATION = 0.01;

// JOYSTICKS
const int JOYSTICK_PORT = 1;

// SOLENOIDS
const int SOLENOID_ONE = 0;
const int SOLENOID_TWO = 1;

// BUTTON MAPPINGS
const UINT32 CATAPULT_WIND_BUTTON = 2;
const UINT32 CATAPULT_UNWIND_BUTTON = 3;

const UINT32 UNPICKUP_BUTTON = 4;
const UINT32 PICKUP_BUTTON = 5;

const UINT32 BRIDGE_LOWER_BUTTON = 6;
const UINT32 BRIDGE_RAISE_BUTTON = 7;

const UINT32 CAMERA_LIGHT_BUTTON = 8;
const UINT32 CAMERA_TARGET_BUTTON = 9;

const UINT32 AUTO_FIRE_BUTTON = 10;
//const UINT32 SOLENOID_ONE_BUTTON = 10;
const UINT32 SOLENOID_TWO_BUTTON = 11;
#endif
