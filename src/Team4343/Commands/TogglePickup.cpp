#include "TogglePickup.h"

TogglePickup::TogglePickup(double speed) {
	m_speed = speed;
	Requires(pickup);
}

void TogglePickup::Initialize() {
	pickup->isEnabled(!pickup->isEnabled());
}

void TogglePickup::Execute() {
	if (pickup->isEnabled())
		pickup->drive(m_speed);
	else
		Cancel();
}

bool TogglePickup::IsFinished() {
	return false;
}

void TogglePickup::End() {
	pickup->drive(0.0);
}

void TogglePickup::Interrupted() {
}
