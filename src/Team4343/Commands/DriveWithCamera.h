#ifndef DRIVEWITHCAMERA_H
#define DRIVEWITHCAMERA_H

#include "../CommandBase.h"

class DriveWithCamera: public CommandBase {
private:
	double m_timeout;
public:
	DriveWithCamera(double timeout);
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
