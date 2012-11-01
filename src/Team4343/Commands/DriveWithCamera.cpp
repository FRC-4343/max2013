#include "DriveWithCamera.h"

DriveWithCamera::DriveWithCamera(double timeout) {
	m_timeout = timeout;
	Requires(camera);
	Requires(chassis);
}

void DriveWithCamera::Initialize() {
	SetTimeout(m_timeout);
}

void DriveWithCamera::Execute() {
	vector<double> centers = camera->getCenterOfMass();

	for(UINT32 x = 0; x < centers.size(); x++)
	{
		if(centers[x] > 0) { 
			printf("Turning Right.\n");
			chassis->turn(1); 
		}
		else if(centers[x] < 0) { 
			printf("Turning Left.\n");
			chassis->turn(-1); 
		}
	}
}

bool DriveWithCamera::IsFinished() {
	return IsTimedOut();
}

void DriveWithCamera::End() {
}

void DriveWithCamera::Interrupted() {
}
