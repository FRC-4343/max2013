package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class BridgeDoNothing extends CommandBase {
    public BridgeDoNothing() {
        requires(arm);
    }

    protected void initialize() {
    }

    protected void execute() {
        arm.drive(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
