package com.frc4343.robot;

import com.frc4343.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {
    // Create a single static instance of all of your subsystems
    public static BridgeArm arm = new BridgeArm();
    public static BallPickup pickup = new BallPickup();
    public static Camera camera = new Camera();
    public static Catapult catapult = new Catapult();
    public static Chassis chassis = new Chassis();
    public static Gyroscope gyroscope = new Gyroscope();
    public static MSKinect kinect = new MSKinect();
    public static Rangefinder sonar = new Rangefinder();
    public static Solenoids solenoid = new Solenoids();
    public static OI oi;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(arm);
        SmartDashboard.putData(pickup);
        SmartDashboard.putData(camera);
        SmartDashboard.putData(catapult);
        SmartDashboard.putData(chassis);
        SmartDashboard.putData(gyroscope);
        SmartDashboard.putData(kinect);
        SmartDashboard.putData(sonar);
        SmartDashboard.putData(solenoid);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
