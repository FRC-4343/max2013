package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LauncherDoNothing extends CommandBase {
    public LauncherDoNothing() {
        requires(launcher);
    }

    protected void initialize() {
    }

    protected void execute() {
        launcher.launcherMotor(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
