#ifndef DRIVEWITHJOYSTICK_H
#define DRIVEWITHJOYSTICK_H

#include "../CommandBase.h"

class DriveWithJoystick: public CommandBase {
private:
    float previousXVal;
    float previousYVal;
public:
	DriveWithJoystick();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
