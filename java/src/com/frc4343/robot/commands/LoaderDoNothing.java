package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LoaderDoNothing extends CommandBase {
    public LoaderDoNothing() {
        requires(loader);
    }

    protected void initialize() {
    }

    protected void execute() {
        loader.drive(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
