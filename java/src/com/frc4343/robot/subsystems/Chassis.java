package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem {
    RobotDrive drive; // Used to drive the robot.
    double speed; // Holds the current speed of the robot.

    private double previousXVal = 0.0;
    private double previousYVal = 0.0;

    public Chassis() {
        super("Chassis");

        System.out.println("Initializing chassis.");

        drive = new RobotDrive(Mappings.LEFT_MOTOR, Mappings.RIGHT_MOTOR);

        drive.setSafetyEnabled(Constants.SAFETY_ENABLED);
        drive.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystick());
    }

    public void goStraight() {
        drive.arcadeDrive(1.0, 0.0);
    }

    public void turn(double turnVal) {
        drive.arcadeDrive(0.0, turnVal);
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    public void driveWithJoystick(Joystick stick) {
        double currentXVal = stick.getAxis(Joystick.AxisType.kY);
        double currentYVal = stick.getAxis(Joystick.AxisType.kX);

        double accelerationRate = 0.2;

        previousXVal = getCurrentXVal() + (currentXVal - getCurrentXVal()) * accelerationRate;
        previousYVal = getCurrentYVal() + (currentYVal - getCurrentYVal()) * accelerationRate;

        drive.arcadeDrive(getCurrentXVal(), getCurrentYVal());
    }

    public void driveWithTurn(double distance, double turnVal) {
        drive.arcadeDrive(distance, turnVal);
    }

    public boolean isMoving() {
        return drive.isAlive();
    }

    public double getCurrentXVal() {
        return previousXVal;
    }

    public double getCurrentYVal() {
        return previousYVal;
    }
}
