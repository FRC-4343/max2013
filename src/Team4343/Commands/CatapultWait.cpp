#include "CatapultWait.h"

CatapultWait::CatapultWait(double timeout) {
	m_timeout = timeout;
	Requires(catapult);
}

void CatapultWait::Initialize() {
	SetTimeout(m_timeout);
}

void CatapultWait::Execute() {
	catapult->triggerRelease(0.0);
	catapult->windMotor(0.0);
}

bool CatapultWait::IsFinished() {
	return IsTimedOut();
}

void CatapultWait::End() {
}

void CatapultWait::Interrupted() {
}
