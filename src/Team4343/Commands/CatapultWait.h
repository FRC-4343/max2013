#ifndef CATAPULTWAIT_H
#define CATAPULTWAIT_H

#include "../CommandBase.h"

class CatapultWait: public CommandBase {
private:
	double m_timeout;
public:
	CatapultWait(double timeout);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
