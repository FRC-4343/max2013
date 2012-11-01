#include "BallPickupDoNothing.h"

BallPickupDoNothing::BallPickupDoNothing() {
	Requires(pickup);
}

void BallPickupDoNothing::Initialize() {
}

void BallPickupDoNothing::Execute() {
	pickup->drive(0.0);
}

bool BallPickupDoNothing::IsFinished() {
	return false;
}

void BallPickupDoNothing::End() {
}

void BallPickupDoNothing::Interrupted() {
}
