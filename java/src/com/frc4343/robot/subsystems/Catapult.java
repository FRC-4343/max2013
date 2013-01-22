package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.CatapultDoNothing;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {
    Jaguar catapultMotor;
    Jaguar triggerMotor;
    DigitalInput hasBallSwitch;
    DigitalInput isRotatedSwitch;
    DigitalInput triggerSwitch;

    public Catapult() {
        super("Catapult");

        System.out.println("Initializing catapult.");

        catapultMotor = new Jaguar(Mappings.WINDER_MOTOR);
        triggerMotor = new Jaguar(Mappings.TRIGGER_MOTOR);

        hasBallSwitch = new DigitalInput(Mappings.BALL_SWITCH_PORT);
        isRotatedSwitch = new DigitalInput(Mappings.ROTATE_SWITCH_PORT);
        triggerSwitch = new DigitalInput(Mappings.TRIGGER_SWITCH_PORT);

        catapultMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        catapultMotor.setExpiration(Constants.EXPIRATION);

        triggerMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        triggerMotor.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CatapultDoNothing());
    }

    public void windMotor(double speed) {
        catapultMotor.set(speed);
    }

    public void triggerRelease(double speed) {
        triggerMotor.set(speed);
    }

    public boolean getTriggerDriveState() {
        return triggerMotor.isAlive();
    }

    public boolean getCatapultDriveState() {
        return catapultMotor.isAlive();
    }

    public boolean hasBall() {
        return hasBallSwitch.get();
    }

    public boolean isRotated() {
        return isRotatedSwitch.get();
    }

    public boolean triggerState() {
        return triggerSwitch.get();
    }
}
