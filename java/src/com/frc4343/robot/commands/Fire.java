package com.frc4343.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {
    public Fire() {
        addSequential(new CatapultRotate(1.0));
        addSequential(new CatapultRelease());
        addSequential(new CatapultRotate(-1.0));
        addSequential(new CatapultRotate(-1.0));
        addSequential(new CatapultWait(2.0));
        addSequential(new CatapultRotate(1.0));
    }
}
