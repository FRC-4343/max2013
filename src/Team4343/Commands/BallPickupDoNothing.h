#ifndef BALLPICKUPDONOTHING_H
#define BALLPICKUPDONOTHING_H

#include "../CommandBase.h"

class BallPickupDoNothing: public CommandBase {
private:
public:
	BallPickupDoNothing();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
