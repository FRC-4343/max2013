package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import com.frc4343.robot2.Sonar;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class GyroSystem extends System {

    public Gyro gyro = new Gyro(Mappings.GYRO_PORT);
    Sonar sonar = new Sonar(1, 1);
    double initialAngle = 0;
    double initialDistance = 0;
    boolean isRotatingClockwise = true;
    // IDLE indicates no activity.
    static final byte IDLE = 0;
    // ROTATING indicates that the robot is rotating around a point.
    static final byte ROTATING = 1;
    // ALIGNING indicates that the robot is aligning itself to shoot at the pyramid.
    static final byte ALIGNING = 2;
    // DISTANCING indicates that the robot is calculating and correcting the distance from the pyramid.
    static final byte DISTANCING = 3;

    public GyroSystem(RobotTemplate robot) {
        super(robot);
    }

    public void switchMode() {
        gyro.reset();

        initialAngle = 0;
        initialDistance = 0;

        systemState = IDLE;
    }

    public void run() {
        if (!robot.isAutonomous()) {
            switch (systemState) {
                case IDLE:
                    if (robot.joystickSystem.getJoystick(1).getRawButton(Mappings.ALIGN_TO_CENTER_GOAL_CLOCKWISE) || robot.joystickSystem.getJoystick(1).getRawButton(Mappings.ALIGN_TO_CENTER_GOAL_COUNTERCLOCKWISE)) {
                        isRotatingClockwise = robot.joystickSystem.getJoystick(1).getRawButton(Mappings.ALIGN_TO_CENTER_GOAL_CLOCKWISE) ? true : false;
                        if (robot.joystickSystem.getJoystick(1).getRawButton(Mappings.ALIGN_TO_CENTER_GOAL_CLOCKWISE)) {
                            robot.driveSystem.driveIndefinitely(1.0, 0.0);
                        } else if (robot.joystickSystem.getJoystick(1).getRawButton(Mappings.ALIGN_TO_CENTER_GOAL_COUNTERCLOCKWISE)) {
                            robot.driveSystem.driveIndefinitely(-1.0, 0.0);
                        }

                        initialDistance = sonar.getDistanceInInches();
                        robot.driveSystem.isDrivingWithJoystick = false;

                        systemState = DISTANCING;
                    }
                    break;
                case DISTANCING:
                    if (sonar.getDistanceInInches() > Mappings.DISTANCE_FROM_WALL_IN_INCHES + (Mappings.DISTANCE_DEADZONE_IN_INCHES / 2)) {
                        robot.driveSystem.driveIndefinitely(1.0, 0.0);
                    } else if (sonar.getDistanceInInches() < Mappings.DISTANCE_FROM_WALL_IN_INCHES - (Mappings.DISTANCE_DEADZONE_IN_INCHES / 2)) {
                        robot.driveSystem.driveIndefinitely(-1.0, 0.0);
                    } else {
                        initialAngle = gyro.getAngle();
                        robot.driveSystem.driveIndefinitely(0.0, isRotatingClockwise ? Mappings.ROTATE_SPEED : -Mappings.ROTATE_SPEED);

                        systemState = ROTATING;
                    }
                case ROTATING:
                    if (gyro.getAngle() >= initialAngle + Mappings.ANGLE_TO_ROTATE_BY || gyro.getAngle() <= initialAngle - Mappings.ANGLE_TO_ROTATE_BY) {
                        robot.driveSystem.isDrivingWithJoystick = true;
                        switchMode();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public String getState() {
        switch (systemState) {
            case 0:
                return "IDLE";
            case 1:
                return "ROTATING";
            case 2:
                return "ALIGNING";
            default:
                return "ERROR";
        }
    }
}
