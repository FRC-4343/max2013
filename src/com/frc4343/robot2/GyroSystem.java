package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class GyroSystem extends System {

    Gyro gyro = new Gyro(1);
    Timer timer = new Timer();
    int deadZone = 5;
    double motorSpeed = 0.7;
    boolean isButtonPressed = false;

    public GyroSystem(RobotTemplate robot) {
        super(robot);
    }

    public void switchMode() {
        gyro.reset();
        timer.reset();
        timer.stop();

        if (robot.isAutonomous()) {

        }
    }

    public void run() {
        if (robot.isAutonomous() && robot.firingSystem.isFinishedFiring()) {
        }
    }
}
