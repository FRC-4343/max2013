#include "TargetWithCamera.h"
#include "Turn.h"
#include "../Defines.h"

TargetWithCamera::TargetWithCamera() {
	Requires(camera);
	Requires(chassis);
}

void TargetWithCamera::Initialize() {
}

void TargetWithCamera::Execute() {
	if (!chassis->isMoving()) {
		vector<double> centers = camera->getCenterOfMass();
	
		for(UINT32 x = 0; x < centers.size(); x++)
		{
			if(centers[x] > (0 - CAMERA_TARGET_DEADZONE)) { 
				printf("Turning Left.\n");
				turnCommand = new Turn(CAMERA_TURN_TIMEOUT, 1.0);
				turnCommand->Start();
			}
			else if(centers[x] < CAMERA_TARGET_DEADZONE) { 
				printf("Turning Right.\n");
				turnCommand = new Turn(CAMERA_TURN_TIMEOUT, -1.0);
				turnCommand->Start();
			}
			else {
				turnCommand->Cancel();
				chassis->driveWithTurn(1.0, 0.0);
				Cancel();
			}
			printf("Val: %f", centers[x]);
		}
	}
}

bool TargetWithCamera::IsFinished() {
	return false;
}

void TargetWithCamera::End() {
	turnCommand->Cancel();
}

void TargetWithCamera::Interrupted() {
}
