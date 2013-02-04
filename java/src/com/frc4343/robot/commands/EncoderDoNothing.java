package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class EncoderDoNothing extends CommandBase {
    public EncoderDoNothing() {
        requires(encoder);
    }

    protected void initialize() {
    }

    protected void execute() {
        encoder.drive(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
