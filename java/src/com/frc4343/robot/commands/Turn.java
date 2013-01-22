package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;
import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Vector;

public class Turn extends CommandBase {
    double m_timeout;
    double turnValue;

    public Turn(double timeout, double turnVal) {
        m_timeout = timeout;
        turnValue = turnVal;
        requires(chassis);
    }

    protected void initialize() {
        setTimeout(m_timeout);
    }

    protected void execute() {
        chassis.turn(turnValue);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
