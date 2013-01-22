package com.frc4343.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveInASquare extends CommandGroup {
    public DriveInASquare() {
        addSequential(new DriveStraight(1));
        addSequential(new Turn(1, 5.0));
        addSequential(new DriveStraight(1));
        addSequential(new Turn(1, 5.0));
        addSequential(new DriveStraight(1));
        addSequential(new Turn(1, 5.0));
        addSequential(new DriveStraight(1));
        addSequential(new Turn(1, 5.0));
    }
}
