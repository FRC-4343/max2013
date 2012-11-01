#include "WPILib.h"
#include "Commands/Command.h"
#include "Commands/DriveWithCamera.h"
#include "CommandBase.h"
#include "Defines.h"

class CommandBasedRobot : public IterativeRobot {
private:
	Command *autonomousCommand;
	
	virtual void RobotInit() {
		CommandBase::init();
		autonomousCommand  = new DriveWithCamera(10000);
	}
	
	virtual void AutonomousInit() {
		autonomousCommand->Start();
		CommandBase::camera->toggleLight();
	}
	
	virtual void AutonomousPeriodic() {
		Scheduler::GetInstance()->Run();
		//CommandBase::solenoid->openAll();
		//Wait(1);
		//CommandBase::solenoid->closeAll();
		//Wait(1);
		
		//CommandBase::kinect->moveWithKinect();
		
		//Wait(0.01);
	}
	
	virtual void TeleopInit() {
		autonomousCommand->Cancel();
		//Wait(0.01);
	}
	
	virtual void TeleopPeriodic() {
		Scheduler::GetInstance()->Run();
		
		//printf("Sonar distance: %f inches\n", CommandBase::sonar->getInches());
		//Wait(0.01);
	}
	
	virtual void DisabledInit() {}
};

START_ROBOT_CLASS(CommandBasedRobot);

