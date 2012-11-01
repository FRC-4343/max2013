#include "ToggleSolenoid.h"

ToggleSolenoid::ToggleSolenoid(int solenoidNum) {
	m_solenoidNum = solenoidNum;
	Requires(solenoid);
}

void ToggleSolenoid::Initialize() {
}

void ToggleSolenoid::Execute() {
	solenoid->toggleSolenoid(m_solenoidNum);
}

bool ToggleSolenoid::IsFinished() {
	return false;
}

void ToggleSolenoid::End() {
}

void ToggleSolenoid::Interrupted() {
}
