package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Mappings;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LauncherDriveWithJoystick extends CommandBase {
    public LauncherDriveWithJoystick(double speedVal) {
        requires(launcher);
        launcher.speedVal += speedVal;
    }

    protected void initialize() {
    }

    protected void execute() {
        launcher.launcherMotor(launcher.speedVal);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
