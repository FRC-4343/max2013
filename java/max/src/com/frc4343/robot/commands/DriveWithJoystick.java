package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class DriveWithJoystick extends CommandBase {
    public DriveWithJoystick() {
        requires(chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
        chassis.driveWithJoystick(oi.getJoystick());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
