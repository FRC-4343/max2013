package com.frc4343.robot2;

public class System {

    protected final RobotTemplate robot;
    protected byte systemState = 0;

    protected System(RobotTemplate robot) {
        this.robot = robot;
    }

    protected void switchMode() { }

    protected void run() { }
}
