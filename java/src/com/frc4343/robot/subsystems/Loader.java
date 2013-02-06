package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.LoaderDoNothing;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Loader extends Subsystem {
    Jaguar loaderMotor;

    public Loader() {
        super("Loader");

        System.out.println("Initializing loader.");

        loaderMotor = new Jaguar(Mappings.LOADER_MOTOR);

        loaderMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        loaderMotor.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LoaderDoNothing());
    }

    public void drive(double speed) {
        loaderMotor.set(speed);
    }

    public boolean getLoaderDriveState() {
        return loaderMotor.isAlive();
    }
}
