#ifndef CHASSIS_H
#define CHASSIS_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Chassis: public Subsystem {
private:
	RobotDrive *drive; // Used to drive the robot.
	double speed; // Holds the current speed of the robot.
public:
	Chassis();
	void InitDefaultCommand();
	void goStraight();
	void turn(double turnVal);
	void tankDrive(double left, double right);
	void driveWithJoystick(Joystick *stick, float previousXVal, float previousYVal);
	void driveWithTurn(double distance, double turnVal);
	bool isMoving();
};

#endif
