#ifndef TOGGLELIGHT_H
#define TOGGLELIGHT_H

#include "../CommandBase.h"

class ToggleLight: public CommandBase {
public:
	ToggleLight();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
