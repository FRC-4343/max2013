package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class DriveSystem extends System {

    RobotDrive drive = new RobotDrive(1, 2);
    Timer timer = new Timer();
    Timer pause = new Timer();
    double axisCompensation = 0.5;
    double driveSpeed = 1.0;
    double turnSpeed = 1.0;
    double timerGoal = 0.0;
    double pauseTime = 0.0;
    boolean isDrivingWithJoystick = false;
    boolean isDrivingWithTimer = false;

    // IDLE indicates no activity.
    static final byte IDLE = 0;
    // DRIVING indicates that the robot is moving.
    static final byte DRIVING = 1;
    // PAUSED indicates that the robot is waiting for a timer to complete before it begins moving.
    static final byte PAUSED = 2;
    // TIMED indicates that the robot is the robot is under a timed driving state.
    static final byte TIMED = 3;
    // DRIVE_BACK indicates that the robot has fired its shots and is driving back to pickup more frisbees.
    static final byte DRIVE_BACK = 4;
    // DRIVE_FORWARD indicates that the robot has picked up extra frisbees and is moving back to its original position.
    static final byte DRIVE_FORWARD = 5;
    // DONE indicates that the robot has finished moving for the state.
    static final byte DONE = 6;

    public DriveSystem(RobotTemplate robot) {
        super(robot);
    }

    public void switchMode() {
        drive.drive(0.0, 0.0);

        timer.reset();
        timer.stop();
        pause.reset();
        pause.stop();

        systemState = IDLE;
    }

    public void run() {
        if (robot.isAutonomous()) {
            switch (systemState) {
                case IDLE:
                    if (robot.firingSystem.isFinishedFiring()) {
                        driveWithTimer(-1.0, 0.0, 1);
                        systemState = DRIVE_BACK;
                    }
                    break;
                case DRIVE_BACK:
                    drive.arcadeDrive(driveSpeed, turnSpeed);

                    if (timer.get() > timerGoal) {
                        timer.reset();
                        timer.stop();

                        timerGoal = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;

                        driveAfterPause(1.0, 0.0, 1, 1);
                        systemState = PAUSED;
                    }
                    break;
                case PAUSED:
                    if (pause.get() > pauseTime) {
                        pause.reset();
                        pause.stop();

                        driveWithTimer(driveSpeed, turnSpeed, timerGoal);

                        pauseTime = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;

                        systemState = DRIVE_FORWARD;
                    }
                    break;
                case DRIVE_FORWARD:
                    drive.arcadeDrive(driveSpeed, turnSpeed);

                    if (timer.get() > timerGoal) {
                        timer.reset();
                        timer.stop();

                        timerGoal = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;

                        // Causes the firingSystem to fire the frisbees contained once more.
                        robot.firingSystem.switchMode();
                        robot.firingSystem.setNumberOfFrisbeesToFireInAutonomous(2);
                        systemState = DONE;
                    }
                    break;
                case DONE:
                    break;
                default:
                    break;
            }
        } else {
            switch (systemState) {
                case IDLE:
                    systemState = DRIVING;
                    break;
                case DRIVING:
                    drive.arcadeDrive(robot.joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kX), robot.joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kY));
                    drive.arcadeDrive(robot.joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kX) * axisCompensation, robot.joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kY) * axisCompensation);
                    break;
                case TIMED:
                    drive.arcadeDrive(driveSpeed, turnSpeed);

                    if (timer.get() > timerGoal) {
                        timer.reset();
                        timer.stop();

                        timerGoal = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void driveWithTimer(double speed, double turn, double seconds) {
        timer.reset();
        timer.start();

        driveSpeed = speed;
        turnSpeed = turn;
        timerGoal = seconds;
    }

    public void driveAfterPause(double speed, double turn, double seconds, double pause) {
        timer.reset();
        timer.stop();

        driveSpeed = speed;
        turnSpeed = turn;
        timerGoal = seconds;
        pauseTime = pause;
    }
}
