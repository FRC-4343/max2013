#include "MSKinect.h"
#include "../CommandBase.h"
#include "../Defines.h"
#include "../Robotmap.h"

MSKinect::MSKinect()
	: Subsystem("MSKinect"),
	  leftArm(LEFT_KINECT_ARM),
	  rightArm(RIGHT_KINECT_ARM)
	{
	printf("Initializing Kinect.\n");
}
    
void MSKinect::InitDefaultCommand() {
}

void MSKinect::moveWithKinect() {
	CommandBase::chassis->tankDrive(leftArm.GetY()*3, rightArm.GetY()*3);
	//SmartDashboard::GetInstance()->Log(leftArm.GetY(), "LeftArm");
	//SmartDashboard::GetInstance()->Log(rightArm.GetY(), "RightArm");
}
