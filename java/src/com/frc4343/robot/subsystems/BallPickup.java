package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.BallPickupDoNothing;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickup extends Subsystem {
    Jaguar pickupMotor; // Used to wind the ball pickup.
    boolean enabledMotor;

    public BallPickup() {
        super("BallPickup");

        System.out.println("Initializing ball pickup.");

        pickupMotor = new Jaguar(Mappings.PICKUP_MOTOR);

        pickupMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        pickupMotor.setExpiration(Constants.EXPIRATION);

        enabledMotor = false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BallPickupDoNothing());
    }

    public void drive(double speed) {
        pickupMotor.set(speed);
    }

    public boolean getPickupDriveState() {
        return pickupMotor.isAlive();
    }

    public boolean isEnabled() {
        return enabledMotor;
    }

    public void setEnabled(boolean enabled) {
        enabledMotor = enabled;
    }
}
