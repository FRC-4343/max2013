#include "Turn.h"

Turn::Turn(double timeout, double turnVal) {
	m_timeout = timeout;
	turnValue = turnVal;
	Requires(chassis);
}

void Turn::Initialize() {
	SetTimeout(m_timeout);
}

void Turn::Execute() {
	chassis->turn(turnValue);
}

bool Turn::IsFinished() {
	return IsTimedOut();
}

void Turn::End() {
}

void Turn::Interrupted() {
}
