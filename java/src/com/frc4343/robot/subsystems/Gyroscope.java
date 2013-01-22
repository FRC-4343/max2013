package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyroscope extends Subsystem {
    Gyro gyro = new Gyro(Mappings.GYRO_PORT);

    public Gyroscope() {
        super("Gyroscope");

        System.out.print("Initializing gyroscope.");

        gyro.reset();
    }

    public void initDefaultCommand() {
    }

    public Gyro getGyro() {
        return gyro;
    }
}
