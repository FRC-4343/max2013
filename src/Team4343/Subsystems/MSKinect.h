#ifndef MSKINECT_H
#define MSKINECT_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class MSKinect: public Subsystem {
	KinectStick leftArm;
	KinectStick rightArm;
public:
	MSKinect();
	void InitDefaultCommand();
	void moveWithKinect();
};

#endif
