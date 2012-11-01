#ifndef GYROSCOPE_H
#define GYROSCOPE_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Gyroscope: public Subsystem {
private:
	Gyro gyro;
public:
	Gyroscope();
	Gyro *getGyro();
	void InitDefaultCommand();
};

#endif
