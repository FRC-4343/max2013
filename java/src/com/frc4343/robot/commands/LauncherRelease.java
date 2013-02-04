package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LauncherRelease extends CommandBase {
    boolean reachedSwitch;
    boolean finished;

    public LauncherRelease() {
        requires(launcher);
    }

    protected void initialize() {
        finished = false;
        reachedSwitch = false;
        if (!launcher.hasBall()) {
            System.out.println("Catapult does not have ball.");
            finished = true;
        } else {
            System.out.println("Catapult has ball.");
        }
    }

    protected void execute() {
        if (launcher.triggerState()) {
            reachedSwitch = true;
            System.out.println("Trigger switch pressed.\n");
        }
        if (reachedSwitch == true) {
            if (!launcher.triggerState()) {
                System.out.println("Trigger switch not pressed.\n");
                finished = true;
            }
        }

        launcher.triggerRelease(-1.0);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
        launcher.triggerRelease(0.0);
    }

    protected void interrupted() {
    }
}
