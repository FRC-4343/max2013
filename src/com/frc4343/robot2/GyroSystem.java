package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Gyro;

public class GyroSystem extends System {

    Gyro gyro = new Gyro(1);
    int deadZone = 5;
    double motorSpeed = 0.7;
    boolean isButtonPressed = false;

    public GyroSystem(RobotTemplate robot) {
        super(robot);
    }

    public void reset() {
        gyro.reset();
    }

    public void run() {
    }
}
