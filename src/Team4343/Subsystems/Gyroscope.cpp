#include "Gyroscope.h"
#include "../RobotMap.h"

Gyroscope::Gyroscope()
	: Subsystem("Gyroscope"), 
	  gyro(GYRO_PORT) {
	printf("Initializing gyroscope.\n");
	gyro.Reset(); 
}
    
void Gyroscope::InitDefaultCommand() {
}

Gyro * Gyroscope::getGyro() {
	return &gyro;
}
