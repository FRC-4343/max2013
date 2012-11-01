#include "BridgeRotate.h"

BridgeRotate::BridgeRotate(double timeout, double speed) {
	m_speed = speed;
	m_timeout = timeout;
	Requires(arm);
}

void BridgeRotate::Initialize() {
	SetTimeout(m_timeout);
}

void BridgeRotate::Execute() {
	arm->drive(m_speed);
}

bool BridgeRotate::IsFinished() {
	return IsTimedOut();
}

void BridgeRotate::End() {
	arm->drive(0.0);
}

void BridgeRotate::Interrupted() {
}
