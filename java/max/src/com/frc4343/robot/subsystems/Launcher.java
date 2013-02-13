package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.Mappings;
import com.frc4343.robot.commands.LauncherDoNothing;
import com.frc4343.robot.commands.LauncherDriveWithJoystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launcher extends Subsystem {
    Victor launcherMotorA;
    Victor launcherMotorB;
    DigitalInput hasDiscSwitch;
    public double speedVal = 0.0;

    public Launcher() {
        super("Launcher");

        System.out.println("Initializing launcher.");

        launcherMotorA = new Victor(Mappings.LAUNCHER_MOTOR_A);
        launcherMotorB = new Victor(Mappings.LAUNCHER_MOTOR_B);

        hasDiscSwitch = new DigitalInput(Mappings.DISC_DETECTION_SWITCH_PORT);

        launcherMotorA.setSafetyEnabled(Constants.SAFETY_ENABLED);
        launcherMotorB.setSafetyEnabled(Constants.SAFETY_ENABLED);
        launcherMotorA.setExpiration(Constants.EXPIRATION);
        launcherMotorB.setExpiration(Constants.EXPIRATION);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LauncherDoNothing());
    }

    public void launcherMotor(double speed) {
        launcherMotorA.set(speed);
    }

    public boolean getLauncherDriveState() {
        return launcherMotorA.isAlive();
    }

    public boolean hasDisc() {
        return hasDiscSwitch.get();
    }
}
