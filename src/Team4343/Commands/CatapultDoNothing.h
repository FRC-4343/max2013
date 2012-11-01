#ifndef CATAPULTDONOTHING_H
#define CATAPULTDONOTHING_H

#include "../CommandBase.h"

class CatapultDoNothing: public CommandBase {
private:
public:
	CatapultDoNothing();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
