#ifndef BRIDGEDONOTHING_H
#define BRIDGEDONOTHING_H

#include "../CommandBase.h"

class BridgeDoNothing: public CommandBase {
private:
public:
	BridgeDoNothing();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
