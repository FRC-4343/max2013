#include "DriveInASquare.h"
#include "DriveStraight.h"
#include "Turn.h"

DriveInASquare::DriveInASquare() {
	AddSequential(new DriveStraight(1));
	AddSequential(new Turn(1, 5.0));
	AddSequential(new DriveStraight(1));
	AddSequential(new Turn(1, 5.0));
	AddSequential(new DriveStraight(1));
	AddSequential(new Turn(1, 5.0));
	AddSequential(new DriveStraight(1));
	AddSequential(new Turn(1, 5.0));
}
