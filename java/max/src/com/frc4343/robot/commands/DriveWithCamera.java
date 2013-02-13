package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import java.util.Vector;

public class DriveWithCamera extends CommandBase {
    double m_timeout;

    public DriveWithCamera(double timeout) {
        m_timeout = timeout;
        requires(camera);
        requires(chassis);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        Vector centers = camera.getCenterOfMass();

        for (int x = 0; x < centers.size(); x++) {
            double value = Constants.getDoubleFromVector(centers, x);

            if (value > 0) {
                System.out.println("Turning Right.\n");
                chassis.turn(1);
            } else if (value < 0) {
                System.out.println("Turning Left.\n");
                chassis.turn(-1);
            }
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
