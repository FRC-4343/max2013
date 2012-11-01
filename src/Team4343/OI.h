#ifndef OI_H
#define OI_H

#include "WPILib.h"

class OI {
private:
	Joystick *stick; // Instance of the current joystick.
	
	JoystickButton *CATAPULT_WIND;
	JoystickButton *CATAPULT_UNWIND;
	
	JoystickButton *SOLENOID_ONE_TOGGLE;
	JoystickButton *SOLENOID_TWO_TOGGLE;
	
	JoystickButton *BRIDGE_RAISE;
	JoystickButton *BRIDGE_LOWER;
	
	JoystickButton *TRIGGER_RELEASE;
	
	JoystickButton *BALL_PICKUP;
	JoystickButton *BALL_RELEASE;
	
	JoystickButton *CAMERA_LIGHT;
	JoystickButton *CAMERA_TARGET;
	
	JoystickButton *AUTO_FIRE;
public:
	OI();
	Joystick *getJoystick();
	SmartDashboard *getDashboard();
};

#endif
