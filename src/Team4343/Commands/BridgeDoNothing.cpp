#include "BridgeDoNothing.h"

BridgeDoNothing::BridgeDoNothing() {
	Requires(arm);
}

void BridgeDoNothing::Initialize() {
}

void BridgeDoNothing::Execute() {
	arm->drive(0.0);
}

bool BridgeDoNothing::IsFinished() {
	return false;
}

void BridgeDoNothing::End() {
}

void BridgeDoNothing::Interrupted() {
}
