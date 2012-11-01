#include "CommandBase.h"
#include "Commands/Scheduler.h"

CommandBase::CommandBase(const char *name) : Command(name) {
}

CommandBase::CommandBase() : Command() {
}

// Initialize a single static instance of all of your subsystems to NULL
BridgeArm * CommandBase::arm = NULL;
BallPickup * CommandBase::pickup = NULL;
Camera * CommandBase::camera = NULL;
Catapult * CommandBase::catapult = NULL;
Chassis * CommandBase::chassis = NULL;
Gyroscope * CommandBase::gyroscope = NULL;
MSKinect * CommandBase::kinect = NULL;
Rangefinder * CommandBase::sonar = NULL;
Solenoids * CommandBase::solenoid = NULL;

OI* CommandBase::oi = NULL;

void CommandBase::init() {
    // Create a single static instance of all of your subsystems. The following
	// line should be repeated for each subsystem in the project.
	arm = new BridgeArm();
	pickup = new BallPickup();
	camera = new Camera();
	catapult = new Catapult();
	chassis = new Chassis();
	gyroscope = new Gyroscope();
	kinect = new MSKinect();
	sonar = new Rangefinder();
	solenoid = new Solenoids();
	oi = new OI();

	oi->getDashboard()->PutData(Scheduler::GetInstance());
	
	oi->getDashboard()->PutData(arm);
	oi->getDashboard()->PutData(pickup);
	oi->getDashboard()->PutData(camera);
	oi->getDashboard()->PutData(catapult);
	oi->getDashboard()->PutData(chassis);
	oi->getDashboard()->PutData(gyroscope);
	oi->getDashboard()->PutData(kinect);
	oi->getDashboard()->PutData(sonar);
	oi->getDashboard()->PutData(solenoid);
}
