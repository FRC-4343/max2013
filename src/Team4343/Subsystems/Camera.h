#ifndef CAMERA_H
#define CAMERA_H
#include "Commands/Subsystem.h"
#include "WPILib.h"

class Camera: public Subsystem {
private:
	AxisCamera &axis;
	Relay relay;
	bool lightState;
public:
	Camera();
	void InitDefaultCommand();
	vector<double> getCenterOfMass();
	void toggleLight();
};

#endif
 
 
 
