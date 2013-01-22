package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class DriveStraight extends CommandBase {
    double m_timeout;

    public DriveStraight(double timeout) {
        m_timeout = timeout;
        requires(chassis);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        chassis.goStraight();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
