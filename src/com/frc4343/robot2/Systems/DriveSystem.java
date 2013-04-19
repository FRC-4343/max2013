package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class DriveSystem extends System {

    RobotDrive drive = new RobotDrive(1, 2);
    Timer timer = new Timer();
    Timer pause = new Timer();
    double driveSpeed = 1.0;
    double turnSpeed = 1.0;
    double timerGoal = 0.0;
    double pauseTime = 0.0;
    public boolean isDrivingWithJoystick = true;
    // IDLE indicates no activity.
    static final byte IDLE = 0;
    // DRIVING indicates that the robot is moving.
    static final byte DRIVING = 1;
    // TIMED indicates that the robot is the robot is under a timed driving state.
    static final byte TIMED = 2;
    // DRIVE_TO_CENTER indicates that the robot has fired its shots and is driving back to the center.
    static final byte DRIVE_TO_CENTER = 3;
    // ROTATING_TO_SIDE indicates that the robot is turning.
    static final byte ROTATING_TO_SIDE = 4;
    // DRIVE_BACKWARD indicates that the robot is at the centre line and is moving backwards to the side of the field.
    static final byte DRIVE_BACKWARD = 5;
    // ROTATING_TO_PICKUP indicates that the robot is turning towards the feeder station.
    static final byte ROTATING_TO_PICKUP = 6;
    // DONE indicates that the robot has finished moving for the state.
    static final byte DONE = 7;

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
                        driveWithTimer(-Mappings.AUTONOMOUS_DRIVE_SPEED, 0.0, Mappings.AUTONOMOUS_TIME_SPENT_DRIVING_BACK);
                        systemState = DRIVE_TO_CENTER;
                    }
                    break;
                case DRIVE_TO_CENTER:
                    drive.arcadeDrive(driveSpeed, turnSpeed);

                    if (timer.get() > timerGoal) {
                        timer.reset();
                        timer.stop();

                        timerGoal = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;

                        robot.navigationSystem.rotate(-45);

                        systemState = ROTATING_TO_SIDE;
                    }
                    break;
                case ROTATING_TO_SIDE:
                    if (isDrivingWithJoystick) {
                        driveWithTimer(-1.0, 0.0, 2.0);

                        systemState = DRIVE_BACKWARD;
                    }
                    break;
                case DRIVE_BACKWARD:
                    drive.arcadeDrive(driveSpeed, turnSpeed);

                    if (timer.get() > timerGoal) {
                        timer.reset();
                        timer.stop();

                        timerGoal = 0;
                        driveSpeed = 0;
                        turnSpeed = 0;

                        robot.navigationSystem.rotate(-45);

                        systemState = ROTATING_TO_PICKUP;
                    }
                    break;
                case ROTATING_TO_PICKUP:
                    if (isDrivingWithJoystick) {
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
                    if (isDrivingWithJoystick) {
                        double sumOfYAxes = robot.joystickSystem.getAxis(2, AxisType.kY) + (robot.joystickSystem.getAxis(1, AxisType.kY) * Mappings.PRECISION_COMPENSATION);
                        double sumOfXAxes = -robot.joystickSystem.getAxis(2, AxisType.kX) * Mappings.AXIS_COMPENSATION + (-robot.joystickSystem.getAxis(1, AxisType.kX) * Mappings.PRECISION_COMPENSATION);
                        // Floor the values of the combined js in case they are above 1 or below -1.
                        sumOfYAxes = sumOfYAxes > 1 ? 1 : sumOfYAxes < -1 ? -1 : sumOfYAxes;
                        sumOfXAxes = sumOfXAxes > 1 ? 1 : sumOfXAxes < -1 ? -1 : sumOfXAxes;
                        drive.arcadeDrive(sumOfYAxes, sumOfXAxes);
                    } else {
                        drive.arcadeDrive(driveSpeed, turnSpeed);
                    }
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

    public void driveIndefinitely(double speed, double turn) {
        timer.reset();
        timer.stop();

        driveSpeed = speed;
        turnSpeed = turn;
    }

    public void driveWithTimer(double speed, double turn, double seconds) {
        driveIndefinitely(speed, turn);
        timer.reset();
        timer.start();
        timerGoal = seconds;
    }

    public void driveAfterPause(double speed, double turn, double seconds, double pause) {
        driveWithTimer(speed, turn, seconds);
        timer.stop();
        this.pause.start();

        pauseTime = pause;
    }

    public String getState() {
        switch (systemState) {
            case 0:
                return "IDLE";
            case 1:
                return "DRIVING";
            case 2:
                return "PAUSE";
            case 3:
                return "TIMED";
            case 4:
                return "DRIVE_TO_CENTER";
            case 5:
                return "DRIVE_BACKWARD";
            case 6:
                return "DONE";
            default:
                return "ERROR";
        }
    }
}
