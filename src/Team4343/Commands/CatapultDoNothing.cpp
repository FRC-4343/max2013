#include "CatapultDoNothing.h"

CatapultDoNothing::CatapultDoNothing() {
	Requires(catapult);
}

void CatapultDoNothing::Initialize() {
}

void CatapultDoNothing::Execute() {
	catapult->triggerRelease(0.0);
	catapult->windMotor(0.0);
}

bool CatapultDoNothing::IsFinished() {
	return false;
}

void CatapultDoNothing::End() {
}

void CatapultDoNothing::Interrupted() {
}
