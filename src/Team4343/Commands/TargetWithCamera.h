#ifndef TARGETWITHCAMERA_H
#define TARGETWITHCAMERA_H

#include "../CommandBase.h"

class TargetWithCamera: public CommandBase {
private: 
	Command *turnCommand;
public:
	TargetWithCamera();
	virtual void Initialize();
	virtual void Execute();
	virtual bool IsFinished();
	virtual void End();
	virtual void Interrupted();
};

#endif
