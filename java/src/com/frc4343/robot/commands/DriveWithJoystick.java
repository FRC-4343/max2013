package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Mappings;
import java.util.Vector;

public class DriveWithJoystick extends CommandBase {
    float previousXVal;
    float previousYVal;

    public DriveWithJoystick() {
        requires(chassis);
    }

    protected void initialize() {
        previousXVal = 0.0f;
        previousYVal = 0.0f;
    }

    protected void execute() {
        chassis.driveWithJoystick(oi.getJoystick(), previousXVal, previousYVal);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
