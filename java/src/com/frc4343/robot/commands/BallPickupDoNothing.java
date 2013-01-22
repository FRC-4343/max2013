package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class BallPickupDoNothing extends CommandBase {
    public BallPickupDoNothing() {
        requires(pickup);
    }

    protected void initialize() {
    }

    protected void execute() {
        pickup.drive(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
