package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Solenoids extends Subsystem {
    Solenoid[] solenoids = new Solenoid[8];

    public Solenoids() {
        super("Solenoids");

        System.out.print("Initializing solenoids.");

        for (int i = 0; i < 8; i++) {
            solenoids[i] = new Solenoid(i + 1);
            openSolenoid(i);
        }
    }

    public void initDefaultCommand() {
    }

    public void openSolenoid(int solenoidNum) {
        solenoids[solenoidNum].set(true);
    }

    public void closeSolenoid(int solenoidNum) {
        solenoids[solenoidNum].set(false);
    }

    public void openAll() {
        for (int i = 0; i < 8; i++) {
            openSolenoid(i);
        }
    }

    public void closeAll() {
        for (int i = 0; i < 8; i++) {
            closeSolenoid(i);
        }
    }

    public void toggleSolenoid(int solenoidNum) {
        solenoids[solenoidNum].set(!getSolenoidStatus(solenoidNum));
    }

    public boolean getSolenoidStatus(int solenoidNum) {
        return solenoids[solenoidNum].get();
    }
}
