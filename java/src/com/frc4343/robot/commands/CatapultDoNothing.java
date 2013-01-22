package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class CatapultDoNothing extends CommandBase {
    public CatapultDoNothing() {
        requires(catapult);
    }

    protected void initialize() {
    }

    protected void execute() {
        catapult.triggerRelease(0.0);
        catapult.windMotor(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
