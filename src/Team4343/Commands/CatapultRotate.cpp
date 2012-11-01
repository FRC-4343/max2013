#include "CatapultRotate.h"

CatapultRotate::CatapultRotate(double speed) {
	resting = true;
	m_speed = speed;
	Requires(catapult);
}

void CatapultRotate::Initialize() {
	finished = false;
}

void CatapultRotate::Execute() {
	if (!catapult->isRotated()) {
		resting = false;
		printf("Catapult: Not resting.\n");
	}
	if (resting == false) {
		if (catapult->isRotated()) {
			printf("Catapult: resting.\n");
			resting = true;
			finished = true;
		}
	}
	
	catapult->windMotor(m_speed);
}

bool CatapultRotate::IsFinished() {
	return finished;
}

void CatapultRotate::End() {
	catapult->windMotor(0.0);
}

void CatapultRotate::Interrupted() {
}
