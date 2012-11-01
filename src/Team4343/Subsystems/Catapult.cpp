#include "Catapult.h"
#include "../Robotmap.h"
#include "../Defines.h"
#include "../Commands/CatapultDoNothing.h"

Catapult::Catapult() : Subsystem("Catapult") {
	printf("Initializing catapult.\n");
	catapultMotor = new Jaguar(WINDER_MOTOR); 
	triggerMotor = new Jaguar(TRIGGER_MOTOR);
	
	hasBallSwitch = new DigitalInput(BALL_SWITCH_PORT);
	isRotatedSwitch = new DigitalInput(ROTATE_SWITCH_PORT);
	triggerSwitch = new DigitalInput(TRIGGER_SWITCH_PORT);
}
    
void Catapult::InitDefaultCommand() {
	SetDefaultCommand(new CatapultDoNothing());
}

void Catapult::windMotor(double speed) {
	catapultMotor->SetSpeed(speed);
}

void Catapult::triggerRelease(double speed) {
	triggerMotor->SetSpeed(speed);
}

bool Catapult::getTriggerDriveState() {
	return triggerMotor->IsAlive();
}

bool Catapult::getCatapultDriveState() {
	return catapultMotor->IsAlive();
}

bool Catapult::hasBall() {
	return hasBallSwitch->Get();
}

bool Catapult::isRotated() {
	return isRotatedSwitch->Get();
}

bool Catapult::triggerState() {
	return triggerSwitch->Get();
}
