#include "Solenoids.h"
#include "../Robotmap.h"
#include "../Defines.h"

Solenoids::Solenoids()
	: Subsystem("Solenoids")
	{
	printf("Initializing solenoids.\n");
	
	for (int i = 0; i < 8; i++) {
	  solenoids[i] = new Solenoid(i + 1);
	  openSolenoid(i);
	}
}
    
void Solenoids::InitDefaultCommand() {
}

void Solenoids::openSolenoid(int solenoidNum) {
	solenoids[solenoidNum]->Set(true);
}

void Solenoids::closeSolenoid(int solenoidNum) {
	solenoids[solenoidNum]->Set(false);
}

void Solenoids::openAll() {
	for (int i = 0; i < 8; i++) {
		openSolenoid(i);
	}
}

void Solenoids::closeAll() {
	for (int i = 0; i < 8; i++) {
		closeSolenoid(i);
	}
}

void Solenoids::toggleSolenoid(int solenoidNum) {
	solenoids[solenoidNum]->Set(!solenoids[solenoidNum]);
}

bool Solenoids::getSolenoidStatus(int solenoidNum) {
	return solenoids[solenoidNum];
}
