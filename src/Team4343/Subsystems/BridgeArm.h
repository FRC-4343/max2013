#ifndef BRIDGEARM_H
#define BRIDGEARM_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class BridgeArm: public Subsystem {
private:
	//RobotDrive *bridgeDrive; // Used to wind the catapult.
	Jaguar *bridgeMotor;
public:
	BridgeArm();
	void InitDefaultCommand();
	void drive(double speed);
	bool getBridgeDriveState();
};

#endif
