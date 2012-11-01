#include "BridgeArm.h"
#include "../Robotmap.h"
#include "../Defines.h"
#include "../Commands/BridgeDoNothing.h"

BridgeArm::BridgeArm() : Subsystem("BridgeArm") {
	printf("Initializing bridge arm.\n");
	
	bridgeMotor = new Jaguar(BRIDGE_MOTOR);
}
    
void BridgeArm::InitDefaultCommand() {
	SetDefaultCommand(new BridgeDoNothing());
}

void BridgeArm::drive(double speed) {
	bridgeMotor->SetSpeed(speed);
}

bool BridgeArm::getBridgeDriveState() {
	return bridgeMotor->IsAlive();
}
