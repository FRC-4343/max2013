#ifndef CATAPULT_H
#define CATAPULT_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Catapult: public Subsystem {
private:
	//RobotDrive *catapultDrive; // Used to wind the catapult.
	//RobotDrive *triggerDrive; // Used to power the trigger.
	Jaguar *catapultMotor;
	Jaguar *triggerMotor;
	
	DigitalInput *hasBallSwitch;
	DigitalInput *isRotatedSwitch;
	DigitalInput *triggerSwitch;
public:
	Catapult();
	void InitDefaultCommand();
	void windMotor(double speed);
	void triggerRelease(double speed);
	bool getTriggerDriveState();
	bool getCatapultDriveState();
	bool hasBall();
	bool isRotated();
	bool triggerState();
};

#endif
