#ifndef COMMAND_BASE_H
#define COMMAND_BASE_H

#include "Commands/Command.h"

#include "Subsystems/BallPickup.h"
#include "Subsystems/BridgeArm.h"
#include "Subsystems/Camera.h"
#include "Subsystems/Catapult.h"
#include "Subsystems/Chassis.h"
#include "Subsystems/Gyroscope.h"
#include "Subsystems/MSKinect.h"
#include "Subsystems/Rangefinder.h"
#include "Subsystems/Solenoids.h"
#include "OI.h"


/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.examplesubsystem
 */
class CommandBase: public Command {
public:
	CommandBase(const char *name);
	CommandBase();
	static void init();
	// Create a single static instance of all of your subsystems
	static BridgeArm *arm;
	static BallPickup *pickup;
	static Camera *camera;
	static Catapult *catapult;
	static Chassis *chassis;
	static Gyroscope *gyroscope;
	static MSKinect *kinect;
	static Rangefinder *sonar;
	static Solenoids *solenoid;
	static OI *oi;
};

#endif
