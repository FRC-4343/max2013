package com.frc4343.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Fire extends CommandGroup {
    public Fire() {
        addSequential(new LauncherRotate(1.0));
        addSequential(new LauncherRelease());
        addSequential(new LauncherRotate(-1.0));
        addSequential(new LauncherRotate(-1.0));
        addSequential(new LauncherWait(2.0));
        addSequential(new LauncherRotate(1.0));
    }
}
