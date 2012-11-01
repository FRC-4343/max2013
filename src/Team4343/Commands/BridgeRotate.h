#ifndef BRIDGEROTATE_H
#define BRIDGEROTATE_H

#include "../CommandBase.h"

class BridgeRotate: public CommandBase {
private:
	double m_speed;
	double m_timeout;
public:
	BridgeRotate(double timeout, double speed);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
