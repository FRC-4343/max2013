#include "CatapultRelease.h"

CatapultRelease::CatapultRelease() {
	Requires(catapult);
}

void CatapultRelease::Initialize() {
	finished = false;
	reachedSwitch = false;
	if (!catapult->hasBall()) {
		printf("Catapult does not have ball.\n");
		finished = true;
	}
	else
		printf("Catapult has ball.\n");
}

void CatapultRelease::Execute() {
	if (catapult->triggerState()) {
		reachedSwitch = true;
		printf("Trigger switch pressed.\n");
	}
	if (reachedSwitch == true) {
		if (!catapult->triggerState()) {
			printf("Trigger switch not pressed.\n");
			finished = true;
		}
	}
	
	catapult->triggerRelease(-1.0);
}

bool CatapultRelease::IsFinished() {
	return finished;
}

void CatapultRelease::End() {
	catapult->triggerRelease(0.0);
}

void CatapultRelease::Interrupted() {
}
