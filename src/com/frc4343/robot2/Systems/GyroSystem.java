package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class GyroSystem extends System {

    Gyro gyro = new Gyro(1);
    Timer timer = new Timer();
    int deadZone = 5;
    double motorSpeed = 0.7;
    double initialAngle = 0;
    double turnAngle = 45;
    boolean isButtonPressed = false;
    boolean isRotatingClockwise = true;

    // IDLE indicates no activity.
    static final byte IDLE = 0;
    // ROTATING indicates that the robot is rotating around a point.
    static final byte ROTATING = 1;
    // ALIGNING indicates that the robot is aligning itself to shoot at the pyramid.
    static final byte ALIGNING = 2;

    public GyroSystem(RobotTemplate robot) {
        super(robot);
    }

    public void switchMode() {
        gyro.reset();
        timer.reset();
        timer.stop();

        initialAngle = gyro.getAngle();

        systemState = IDLE;
    }

    public void run() {
        if (!robot.isAutonomous()) {
            switchMode(systemState) {
                case IDLE:
                    if (robot.joystickSystem.getJoystick(1).getRawButton(10)) {
                        robot.driveSystem.driveIndefinitely(0.0, motorSpeed);
                        initialAngle = gyro.getAngle();
                        robot.driveSystem.isDrivingWithJoystick = false;
                        systemState = ROTATING;
                    } else if (robot.joystickSystem.getJoystick(1).getRawButton(11)) {
                        robot.driveSystem.driveIndefinitely(0.0, -motorSpeed);
                        initialAngle = gyro.getAngle();
                        robot.driveSystem.isDrivingWithJoystick = false;
                        systemState = ROTATING;
                    }
                    break;
                case ROTATING:
                    if (gyro.getAngle() >= initialAngle + turnAngle || gyro.getAngle() <= initialAngle - turnAngle) {
                        robot.driveSystem.isDrivingWithJoystick = true;
                        switchMode();
                    }
                    break;
                case ALIGNING:
                    break;
                case default:
                    break;
            }
        }
    }
}
