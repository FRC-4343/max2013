package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.LoaderDoNothing;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Loader extends Subsystem {
    Jaguar bridgeMotor;

    public Loader() {
        super("BridgeArm");

        System.out.println("Initializing bridge arm.");

        bridgeMotor = new Jaguar(Mappings.BRIDGE_MOTOR);

        bridgeMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        bridgeMotor.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LoaderDoNothing());
    }

    public void drive(double speed) {
        bridgeMotor.set(speed);
    }

    public boolean getBridgeDriveState() {
        return bridgeMotor.isAlive();
    }
}
