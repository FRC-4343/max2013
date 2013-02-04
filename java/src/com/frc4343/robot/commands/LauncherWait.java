package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LauncherWait extends CommandBase {
    double m_timeout;

    public LauncherWait(double timeout) {
        m_timeout = timeout;
        requires(launcher);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        launcher.triggerRelease(0.0);
        launcher.windMotor(0.0);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
