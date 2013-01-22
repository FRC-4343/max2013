package com.frc4343.robot.commands;

import com.frc4343.robot.CommandBase;

public class ToggleSolenoid extends CommandBase {
    int m_solenoidNum;

    public ToggleSolenoid(int solenoidNum) {
        m_solenoidNum = solenoidNum;
        requires(solenoid);
    }

    protected void initialize() {
    }

    protected void execute() {
        solenoid.toggleSolenoid(m_solenoidNum);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
