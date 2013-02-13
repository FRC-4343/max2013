package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Vector;

public class TargetWithCamera extends CommandBase {
    Command turnCommand;

    public TargetWithCamera() {
        requires(camera);
        requires(chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (!chassis.isMoving()) {
            Vector centers = camera.getCenterOfMass();

            for (int x = 0; x < centers.size(); x++) {
                double value = Constants.getDoubleFromVector(centers, x);

                if (value > -Constants.CAMERA_TARGET_DEADZONE) {
                    System.out.println("Turning Left.");
                    turnCommand = new Turn(Constants.CAMERA_TURN_TIMEOUT, 1.0);
                    turnCommand.start();
                } else if (value < Constants.CAMERA_TARGET_DEADZONE) {
                    System.out.println("Turning Right.");
                    turnCommand = new Turn(Constants.CAMERA_TURN_TIMEOUT, -1.0);
                    turnCommand.start();
                } else {
                    turnCommand.cancel();
                    chassis.driveWithTurn(1.0, 0.0);
                    cancel();
                }
                System.out.println(Constants.combineStringWithDouble("Val: ", value));
            }
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        turnCommand.cancel();
    }

    protected void interrupted() {
    }
}
