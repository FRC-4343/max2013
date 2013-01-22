package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class BridgeRotate extends CommandBase {
    double m_speed;
    double m_timeout;

    public BridgeRotate(double timeout, double speed) {
        m_speed = speed;
        m_timeout = timeout;

        requires(arm);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        arm.drive(m_speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        arm.drive(0.0);
    }

    protected void interrupted() {
    }
}
