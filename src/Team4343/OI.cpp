#include "OI.h"
#include "Commands/BridgeRotate.h"
#include "Commands/CatapultRelease.h"
#include "Commands/CatapultRotate.h"
#include "Commands/Fire.h"
#include "Commands/TargetWithCamera.h"
#include "Commands/TogglePickup.h"
#include "Commands/ToggleSolenoid.h"
#include "Commands/ToggleLight.h"
#include "Defines.h"

OI::OI() {
	// Sets stick to a new joystick at JOYSTICK_PORT
	stick = new Joystick(JOYSTICK_PORT);
	
	CATAPULT_WIND = new JoystickButton(stick, CATAPULT_WIND_BUTTON);
	CATAPULT_UNWIND = new JoystickButton(stick, CATAPULT_UNWIND_BUTTON);
	//SOLENOID_ONE_TOGGLE = new JoystickButton(stick, SOLENOID_ONE_BUTTON);
	SOLENOID_TWO_TOGGLE = new JoystickButton(stick, SOLENOID_TWO_BUTTON);
	BRIDGE_RAISE = new JoystickButton(stick, BRIDGE_RAISE_BUTTON);
	BRIDGE_LOWER = new JoystickButton(stick, BRIDGE_LOWER_BUTTON);
	TRIGGER_RELEASE = new JoystickButton(stick, Joystick::kDefaultTriggerButton);
	BALL_PICKUP = new JoystickButton(stick, PICKUP_BUTTON);
	BALL_RELEASE = new JoystickButton(stick, UNPICKUP_BUTTON);
	CAMERA_LIGHT = new JoystickButton(stick, CAMERA_LIGHT_BUTTON);
	CAMERA_TARGET = new JoystickButton(stick, CAMERA_TARGET_BUTTON);
	AUTO_FIRE = new JoystickButton(stick, AUTO_FIRE_BUTTON);
	
	CATAPULT_WIND->WhenPressed(new CatapultRotate(-1.0));
	CATAPULT_UNWIND->WhenPressed(new CatapultRotate(1.0));
	//SOLENOID_ONE_TOGGLE->WhenPressed(new ToggleSolenoid(SOLENOID_ONE));
	SOLENOID_TWO_TOGGLE->WhenPressed(new ToggleSolenoid(SOLENOID_TWO));
	BRIDGE_RAISE->WhenPressed(new BridgeRotate(BRIDGE_TIME, -0.25));
	BRIDGE_LOWER->WhenPressed(new BridgeRotate(BRIDGE_TIME, 0.25));
	TRIGGER_RELEASE->WhenPressed(new CatapultRelease());
	AUTO_FIRE->WhenPressed(new Fire());
	BALL_PICKUP->WhenPressed(new TogglePickup(1.0));
	BALL_RELEASE->WhenPressed(new TogglePickup(-1.0));
	CAMERA_LIGHT->WhenPressed(new ToggleLight());
	CAMERA_TARGET->WhileHeld(new TargetWithCamera());
}

// Used to get the current joystick.
Joystick * OI::getJoystick() {
	return stick;
}

SmartDashboard * OI::getDashboard() {
	return SmartDashboard::GetInstance();
}
