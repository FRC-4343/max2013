package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Vector;

public class ToggleLight extends CommandBase {
    public ToggleLight() {
        requires(camera);
    }

    protected void initialize() {
    }

    protected void execute() {
        camera.toggleLight();
        cancel();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
