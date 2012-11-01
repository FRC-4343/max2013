#ifndef BALLPICKUP_H
#define BALLPICKUP_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class BallPickup: public Subsystem {
private:
	Jaguar *pickupMotor; // Used to wind the ball pickup.
	bool enabledMotor;
public:
	BallPickup();
	void InitDefaultCommand();
	void drive(double speed);
	bool getPickupDriveState();
	bool isEnabled();
	void isEnabled(bool enabled);
};

#endif
