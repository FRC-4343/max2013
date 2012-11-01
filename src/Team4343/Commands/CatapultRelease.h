#ifndef CATAPULTRELEASE_H
#define CATAPULTRELEASE_H

#include "../CommandBase.h"

class CatapultRelease: public CommandBase {
private:
	bool reachedSwitch;
	bool finished;
public:
	CatapultRelease();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
