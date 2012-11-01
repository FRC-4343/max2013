#include "Chassis.h"
#include "../Robotmap.h"
#include "../Commands/DriveWithJoystick.h"
#include "../Defines.h"

Chassis::Chassis() : Subsystem("Chassis") {
	printf("Initializing chassis.\n");
	drive = new RobotDrive(LEFT_MOTOR, RIGHT_MOTOR);
	drive->SetSafetyEnabled(SAFETY_ENABLED);
	
	drive->SetExpiration(EXPIRATION);
}
    
void Chassis::InitDefaultCommand() {
	SetDefaultCommand(new DriveWithJoystick());
}

void Chassis::goStraight() {
	drive->ArcadeDrive(1.0, 0.0);
}

void Chassis::turn(double turnVal) {
	drive->ArcadeDrive(0.0, turnVal);
}

void Chassis::tankDrive(double left, double right) {
	drive->TankDrive(left, right);
}

void Chassis::driveWithJoystick(Joystick *stick) {
	drive->ArcadeDrive(stick);
}

void Chassis::driveWithTurn(double distance, double turnVal) {
	drive->ArcadeDrive(distance, turnVal);
}

bool Chassis::isMoving() {
	return drive->IsAlive();
}
