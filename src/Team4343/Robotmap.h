#ifndef ROBOTMAP_H
#define ROBOTMAP_H
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
 
// Define the motors used to move the robot.
#define LEFT_MOTOR 1
#define RIGHT_MOTOR 2

// Define the pickup and trigger motors.
#define PICKUP_MOTOR 3
#define TRIGGER_MOTOR 4

// Define the bridge arm and winder motors
#define BRIDGE_MOTOR 5
#define WINDER_MOTOR 6

// Define the arms used to move the kinect.
#define LEFT_KINECT_ARM 1
#define RIGHT_KINECT_ARM 2

// Define the gyro and range finder i/o ports.
#define GYRO_PORT 1
#define ROTATE_SWITCH_PORT 3
#define BALL_SWITCH_PORT 4
#define TRIGGER_SWITCH_PORT 5

// If you are using multiple modules, make sure to define both the port
// number and the module. For example you with a rangefinder:
#define RANGE_FINDER_PORT 6

#define CAMERA_LIGHT_RELAY 1
#endif
