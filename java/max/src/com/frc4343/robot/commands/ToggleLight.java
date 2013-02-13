package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class ToggleLight extends CommandBase {
    public ToggleLight() {
        requires(camera);
    }

    protected void initialize() {
    }

    protected void execute() {
        camera.toggleLight();
        cancel();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
