#include "DriveWithJoystick.h"

DriveWithJoystick::DriveWithJoystick() {
	Requires(chassis);
}

void DriveWithJoystick::Initialize() {
    previousXVal = 0.0f;
    previousYVal = 0.0f;
}

void DriveWithJoystick::Execute() {
	chassis->driveWithJoystick(oi->getJoystick(), previousXVal, previousYVal);
}

bool DriveWithJoystick::IsFinished() {
	return false;
}

void DriveWithJoystick::End() {
}

void DriveWithJoystick::Interrupted() {
}
