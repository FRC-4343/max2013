#ifndef BRIDGEARM_H
#define BRIDGEARM_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class BridgeArm: public Subsystem {
private:
	Jaguar *bridgeMotor;
public:
	BridgeArm();
	void InitDefaultCommand();
	void drive(double speed);
	bool getBridgeDriveState();
};

#endif
