package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class CatapultWait extends CommandBase {
    double m_timeout;

    public CatapultWait(double timeout) {
        m_timeout = timeout;
        requires(catapult);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        catapult.triggerRelease(0.0);
        catapult.windMotor(0.0);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
