package com.frc4343.robot2;

import com.frc4343.robot2.Systems.DriveSystem;
import com.frc4343.robot2.Systems.FiringSystem;
import com.frc4343.robot2.Systems.GyroSystem;
import com.frc4343.robot2.Systems.JoystickSystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotTemplate extends IterativeRobot {

    // Various systems
    public FiringSystem firingSystem = new FiringSystem(this);
    public JoystickSystem joystickSystem = new JoystickSystem(this);
    public GyroSystem gyroSystem = new GyroSystem(this);
    public DriveSystem driveSystem = new DriveSystem(this);
    // Miscellaneous robot components/helpers
    Logger logger = new Logger();
    Piston climbingPiston = new Piston((byte) 3, (byte) 4, true);
    Compressor compressor = new Compressor(1, 1);
    // Button mappings
    final byte EXTEND_CLIMBING_PISTONS = 3;
    final byte RETRACT_CLIMBING_PISTONS = 2;

    private void resetRobot() {
        compressor.start();
        // Reset the climber piston to its initial position.
        climbingPiston.extend();
        // Initialize and/or reset the various systems.
        firingSystem.switchMode();
        gyroSystem.switchMode();
        driveSystem.switchMode();
    }

    public void teleopInit() {
        resetRobot();
    }

    public void autonomousInit() {
        resetRobot();
    }

    public void testInit() {
        resetRobot();
    }

    public void teleopPeriodic() {
        firingSystem.run();
        gyroSystem.run();
        driveSystem.run();

        climbingHandler();

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
    }

    public void autonomousPeriodic() {
        firingSystem.run();
        gyroSystem.run();
        driveSystem.run();

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
    }

    public void testPeriodic() {
        firingSystem.run();
        gyroSystem.run();
        driveSystem.run();

        climbingHandler();

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
    }

    private void climbingHandler() {
        if (joystickSystem.getJoystick(1).getRawButton(EXTEND_CLIMBING_PISTONS)) {
            climbingPiston.extend();
        } else if (joystickSystem.getJoystick(1).getRawButton(RETRACT_CLIMBING_PISTONS)) {
            climbingPiston.retract();
        }
    }

    private void printConsoleOutput() {
        // Clears driverStation text.
        logger.clearWindow();
        // Prints State of Frisbee
        logger.printLine(Line.kUser1, "Firing System State: " + firingSystem.getState());
        // Print the speed.
        logger.printLine(Line.kUser2, "Launcher Speed: " + (byte) (firingSystem.getLauncherSpeed() * 100) + "%");
        // Prints State of Launcher Motor
        logger.printLine(Line.kUser3, "Launcher Motor: " + (firingSystem.getLauncherMotorState() ? "ON" : "OFF"));
        // Prints State of Launcher Motor
        logger.printLine(Line.kUser4, "Indexer Motor: " + (firingSystem.getIndexerMotorState() ? "ON" : "OFF"));
        // Print the tank pressurization state.
        logger.printLine(Line.kUser5, "Tanks Full: " + (compressor.getPressureSwitchValue() ? "YES" : "NO"));
        // Updates the output window.
        logger.updateLCD();
    }
}
