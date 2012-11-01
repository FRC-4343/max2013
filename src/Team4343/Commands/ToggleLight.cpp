#include "ToggleLight.h"

ToggleLight::ToggleLight() {
	Requires(camera);
}

void ToggleLight::Initialize() {
}

void ToggleLight::Execute() {
	camera->toggleLight();
	Cancel();
}

bool ToggleLight::IsFinished() {
	return false;
}

void ToggleLight::End() {
}

void ToggleLight::Interrupted() {
}
