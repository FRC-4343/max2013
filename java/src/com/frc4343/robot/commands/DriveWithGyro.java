package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import java.util.Vector;

public class DriveWithGyro extends CommandBase {
    double m_timeout;

    public DriveWithGyro(double timeout) {
        m_timeout = timeout;
        requires(gyroscope);
        requires(chassis);
    }

    protected void initialize() {
        gyroscope.getGyro().reset();
    }

    protected void execute() {
        double angle = gyroscope.getGyro().getAngle();
        chassis.driveWithTurn(-1.0, angle * Constants.Kp);
        System.out.println(Constants.combineStringWithDouble("Gyro Reading: ", angle));
        System.out.println(Constants.combineStringWithDouble("Heading: ", angle * Constants.Kp));
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        chassis.driveWithTurn(0.0, 0.0);
    }

    protected void interrupted() {
    }
}
