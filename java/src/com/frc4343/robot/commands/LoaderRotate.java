package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LoaderRotate extends CommandBase {
    double m_speed;
    double m_timeout;

    public LoaderRotate(double timeout, double speed) {
        m_speed = speed;
        m_timeout = timeout;

        requires(loader);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        loader.drive(m_speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        loader.drive(0.0);
    }

    protected void interrupted() {
    }
}
