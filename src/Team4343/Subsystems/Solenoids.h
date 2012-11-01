#ifndef SOLENOIDS_H
#define SOLENOIDS_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Solenoids: public Subsystem {
	Solenoid *solenoids[8];
public:
	Solenoids();
	void InitDefaultCommand();
	void openSolenoid(int solenoidNum);
	void closeSolenoid(int solenoidNum);
	void openAll();
	void closeAll();
	void toggleSolenoid(int solenoidNum);
	bool getSolenoidStatus(int solenoidNum);
};

#endif
