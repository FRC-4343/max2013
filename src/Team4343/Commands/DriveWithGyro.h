#ifndef DRIVEWITHGYRO_H
#define DRIVEWITHGYRO_H

#include "../CommandBase.h"

class DriveWithGyro: public CommandBase {
private:
	double m_timeout;
public:
	DriveWithGyro();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
