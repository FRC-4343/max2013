package com.frc4343.robot.subsystems;

import com.frc4343.robot.Mappings;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyroscope extends Subsystem {
    Gyro gyro = new Gyro(Mappings.GYRO_PORT);

    public Gyroscope() {
        super("Gyroscope");

        System.out.println("Initializing gyroscope.");

        gyro.reset();
    }

    public void initDefaultCommand() {
    }

    public Gyro getGyro() {
        return gyro;
    }
}
