#ifndef TOGGLESOLENOID_H
#define TOGGLESOLENOID_H

#include "../CommandBase.h"

class ToggleSolenoid: public CommandBase {
private:
	int m_solenoidNum;
public:
	ToggleSolenoid(int solenoidNum);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
