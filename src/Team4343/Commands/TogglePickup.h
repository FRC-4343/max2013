#ifndef TOGGLEPICKUP_H
#define TOGGLEPICKUP_H

#include "../CommandBase.h"

class TogglePickup: public CommandBase {
private:
	double m_speed;
public:
	TogglePickup(double speed);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
