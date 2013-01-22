package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class CatapultRelease extends CommandBase {
    boolean reachedSwitch;
    boolean finished;

    public CatapultRelease() {
        requires(catapult);
    }

    protected void initialize() {
        finished = false;
        reachedSwitch = false;
        if (!catapult.hasBall()) {
            System.out.println("Catapult does not have ball.");
            finished = true;
        } else {
            System.out.println("Catapult has ball.");
        }
    }

    protected void execute() {
        if (catapult.triggerState()) {
            reachedSwitch = true;
            System.out.println("Trigger switch pressed.\n");
        }
        if (reachedSwitch == true) {
            if (!catapult.triggerState()) {
                System.out.println("Trigger switch not pressed.\n");
                finished = true;
            }
        }

        catapult.triggerRelease(-1.0);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
        catapult.triggerRelease(0.0);
    }

    protected void interrupted() {
    }
}
