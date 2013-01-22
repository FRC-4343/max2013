package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class CatapultRotate extends CommandBase {
    double m_speed;
    boolean resting;
    boolean finished;

    public CatapultRotate(double speed) {
        resting = true;
        m_speed = speed;
        requires(catapult);
    }

    protected void initialize() {
        finished = false;
    }

    protected void execute() {
        if (!catapult.isRotated()) {
            resting = false;
            System.out.println("Catapult: Not resting.\n");
        }
        if (resting == false) {
            if (catapult.isRotated()) {
                System.out.println("Catapult: resting.\n");
                resting = true;
                finished = true;
            }
        }

        catapult.windMotor(m_speed);
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
        catapult.windMotor(0.0);
    }

    protected void interrupted() {
    }
}
