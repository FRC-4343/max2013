#include "DriveWithGyro.h"
#include "../Defines.h"

DriveWithGyro::DriveWithGyro() {
	Requires(gyroscope);
}

void DriveWithGyro::Initialize() {
	gyroscope->getGyro()->Reset();
}

void DriveWithGyro::Execute() { 
	 float angle = gyroscope->getGyro()->GetAngle();
	 chassis->driveWithTurn(-1.0, angle * Kp);
	 printf("Gyro Reading: %f \n", angle);
	 printf("Heading: %f \n", angle * Kp);
}

bool DriveWithGyro::IsFinished() {
	return false;
}

void DriveWithGyro::End() {
	chassis->driveWithTurn(0.0, 0.0);
}

void DriveWithGyro::Interrupted() {
}
