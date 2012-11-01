#ifndef RANGEFINDER_H
#define RANGEFINDER_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Rangefinder: public Subsystem {
private:
	AnalogChannel sonic;
public:
	Rangefinder();
	void InitDefaultCommand();
	double getInches();
};

#endif
