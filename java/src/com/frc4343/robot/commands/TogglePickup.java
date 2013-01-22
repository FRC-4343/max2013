package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Vector;

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
