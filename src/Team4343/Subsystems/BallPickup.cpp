#include "BallPickup.h"
#include "../Robotmap.h"
#include "../Defines.h"
#include "../Commands/BallPickupDoNothing.h"

BallPickup::BallPickup() : Subsystem("BallPickup") {
	printf("Initializing ball pickup.\n");
	pickupMotor = new Jaguar(PICKUP_MOTOR); 
	
	pickupMotor->SetSafetyEnabled(SAFETY_ENABLED);
	
	pickupMotor->SetExpiration(EXPIRATION);
	
	enabledMotor = false;
}

void BallPickup::InitDefaultCommand() {
	SetDefaultCommand(new BallPickupDoNothing());
}

void BallPickup::drive(double speed) {
	pickupMotor->SetSpeed(speed);
}

bool BallPickup::getPickupDriveState() {
	return pickupMotor->IsAlive();
}

bool BallPickup::isEnabled() {
	return enabledMotor;
}

void BallPickup::isEnabled(bool enabled) {
	enabledMotor = enabled;
}
