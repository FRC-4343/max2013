package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class TogglePickup extends CommandBase {
    double m_speed;

    public TogglePickup(double speed) {
        m_speed = speed;
        requires(pickup);
    }

    protected void initialize() {
        pickup.setEnabled(!pickup.isEnabled());
    }

    protected void execute() {
        if (pickup.isEnabled()) {
            pickup.drive(m_speed);
        } else {
            cancel();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        pickup.drive(0.0);
    }

    protected void interrupted() {
    }
}
