#include "CatapultRotate.h"
#include "CatapultRelease.h"
#include "CatapultWait.h"
#include "Fire.h"

Fire::Fire() {
	AddSequential(new CatapultRotate(1.0));
	AddSequential(new CatapultRelease());
	AddSequential(new CatapultRotate(-1.0));
	AddSequential(new CatapultRotate(-1.0));
	AddSequential(new CatapultWait(2.0));
	AddSequential(new CatapultRotate(1.0));
}
