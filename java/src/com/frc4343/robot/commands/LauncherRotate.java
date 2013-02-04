package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class LauncherRotate extends CommandBase {
    double m_speed;
    boolean resting;
    boolean finished;

    public LauncherRotate(double speed) {
        resting = true;
        m_speed = speed;
        requires(launcher);
    }

    protected void initialize() {
        finished = false;
    }

    protected void execute() {
        if (!launcher.isRotated()) {
            resting = false;
            System.out.println("Catapult: Not resting.\n");
        }
        if (resting == false) {
            if (launcher.isRotated()) {
                System.out.println("Catapult: resting.\n");
                resting = true;
                finished = true;
            }
        }

        launcher.windMotor(m_speed);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
        launcher.windMotor(0.0);
    }

    protected void interrupted() {
    }
}
