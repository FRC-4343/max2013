package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import com.frc4343.robot.commands.BridgeDoNothing;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BridgeArm extends Subsystem {
    Jaguar bridgeMotor;

    public BridgeArm() {
        super("BridgeArm");

        System.out.print("Initializing bridge arm.");

        bridgeMotor = new Jaguar(RobotMap.BRIDGE_MOTOR);

        bridgeMotor.setSafetyEnabled(Constants.SAFETY_ENABLED);
        bridgeMotor.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BridgeDoNothing());
    }

    public void drive(double speed) {
        bridgeMotor.set(speed);
    }

    public boolean getBridgeDriveState() {
        return bridgeMotor.isAlive();
    }
}
