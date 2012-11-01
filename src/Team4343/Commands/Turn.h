#ifndef TURN_H
#define TURN_H

#include "../CommandBase.h"

class Turn: public CommandBase {
private:
	double m_timeout;
	double turnValue;
public:
	Turn(double timeout, double turnVal);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
