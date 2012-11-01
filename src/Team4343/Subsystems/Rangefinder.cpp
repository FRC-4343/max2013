#include "Rangefinder.h"
#include "../RobotMap.h"

Rangefinder::Rangefinder()
	: Subsystem("Rangefinder"), 
	  sonic(RANGE_FINDER_PORT) {
	printf("Initializing rangefinder.\n");
}
    
void Rangefinder::InitDefaultCommand() {
}

double Rangefinder::getInches() {
	double volt = sonic.GetVoltage();
	double inches = volt * 100;
	return inches;
}
