#ifndef CATAPULTROTATE_H
#define CATAPULTROTATE_H

#include "../CommandBase.h"

class CatapultRotate: public CommandBase {
private:
	double m_speed;
	bool resting;
	bool finished;
public:
	CatapultRotate(double speed);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
