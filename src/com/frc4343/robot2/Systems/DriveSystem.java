package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class DriveSystem extends System {

    RobotDrive drive = new RobotDrive(1, 2);
    Timer timer = new Timer();
    Timer autonomousDriveTimer = new Timer();
    Timer pause = new Timer();
    double driveSpeed = 1.0;
    double turnSpeed = 1.0;
    double timerGoal = 0.0;
    double pauseTime = 0.0;

    final double AUTONOMOUS_REVERSE_DURATION = 1.3;
    final double AUTONOMOUS_ROTATE_DURATION = 0.79;
    public boolean isDrivingWithJoystick = true;
    // IDLE indicates no activity.
    static final byte IDLE = 0;
    // DRIVING indicates that the robot is moving.
    static final byte DRIVING = 1;
    // PAUSED indicates that the robot is waiting for a timer to complete before it begins moving.
    static final byte TURN_180 = 2;
    // TIMED indicates that the robot is the robot is under a timed driving state.
    static final byte TIMED = 3;
    // DRIVE_BACK indicates that the robot has fired its shots and is driving back to pickup more frisbees.
    static final byte DRIVE_REVERSE = 4;
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
                        systemState = DRIVE_REVERSE;
                    }
                    break;
                case DRIVE_REVERSE:
                    if (autonomousDriveTimer.get() <= AUTONOMOUS_REVERSE_DURATION) {
                        drive.arcadeDrive(1.0, 0.0);
                    } else if (autonomousDriveTimer.get() > AUTONOMOUS_REVERSE_DURATION) {
                        drive.arcadeDrive(0.0, 0.0);
                        autonomousDriveTimer.reset();
                        systemState = TURN_180;
                    }
                    break;
                case TURN_180:
                    if (autonomousDriveTimer.get() >= 0.1 && autonomousDriveTimer.get() <= AUTONOMOUS_ROTATE_DURATION) {
                        drive.tankDrive(-1.0, 1.0);
                    } else if (autonomousDriveTimer.get() > AUTONOMOUS_ROTATE_DURATION + 0.5) {
                        drive.arcadeDrive(0.0, 0.0);
                        autonomousDriveTimer.reset();
                        autonomousDriveTimer.stop();
                        systemState = DONE;
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

                        robot.firingSystem.switchMode();
                        //robot.firingSystem.setNumberOfFrisbeesToFireInAutonomous(2);
                        //robot.firingSystem.initialAutonomousDelayOver = true;
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
                        double sumOfYAxes = robot.joystickSystem.getJoystick((byte) 2).getAxis(Joystick.AxisType.kY) + (robot.joystickSystem.getJoystick((byte) 1).getAxis(Joystick.AxisType.kY) * Mappings.PRECISION_COMPENSATION);
                        double sumOfXAxes = -robot.joystickSystem.getJoystick((byte) 2).getAxis(Joystick.AxisType.kX) * Mappings.AXIS_COMPENSATION + (-robot.joystickSystem.getJoystick((byte) 1).getAxis(Joystick.AxisType.kX) * Mappings.PRECISION_COMPENSATION);
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
                return "TURN_180";
            case 3:
                return "TIMED";
            case 4:
                return "DRIVE_BACK";
            case 5:
                return "DRIVE_FORWARD";
            case 6:
                return "DONE";
            default:
                return "ERROR";
        }
    }
}
