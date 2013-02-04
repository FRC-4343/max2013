package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class ToggleEncoder extends CommandBase {
    double m_speed;

    public ToggleEncoder(double speed) {
        m_speed = speed;
        requires(encoder);
    }

    protected void initialize() {
        encoder.setEnabled(!encoder.isEnabled());
    }

    protected void execute() {
        if (encoder.isEnabled()) {
            encoder.drive(m_speed);
        } else {
            cancel();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        encoder.drive(0.0);
    }

    protected void interrupted() {
    }
}
