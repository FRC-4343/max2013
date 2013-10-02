package com.frc4343.robot2;

import com.frc4343.robot2.Systems.DriveSystem;
import com.frc4343.robot2.Systems.FiringSystem;
import com.frc4343.robot2.Systems.GyroSystem;
import com.frc4343.robot2.Systems.JoystickSystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
//import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotTemplate extends IterativeRobot {

    // Various systems
    public FiringSystem firingSystem = new FiringSystem(this);
    public JoystickSystem joystickSystem = new JoystickSystem(this);
    public GyroSystem gyroSystem = new GyroSystem(this);
    public DriveSystem driveSystem = new DriveSystem(this);
    // Miscellaneous robot components/helpers
    Logger logger = new Logger();
    Piston climbingPiston = new Piston(Mappings.CLIMBING_PISTON_SOLENOID_ONE, Mappings.CLIMBING_PISTON_SOLENOID_TWO, Mappings.CLIMBING_PISTON_EXTENDED_BY_DEFAULT);
    Compressor compressor = new Compressor(1, 1);

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
        if (joystickSystem.isButtonPressed((byte) 1, Mappings.EXTEND_CLIMBING_PISTONS)) {
            climbingPiston.extend();
        } else if (joystickSystem.isButtonPressed((byte) 1, Mappings.RETRACT_CLIMBING_PISTONS) || (m_ds.getMatchTime() <= 0.3 && m_ds.isFMSAttached())) {
            climbingPiston.retract();
        }
    }

    private void printConsoleOutput() {
        // Clears driverStation text.
        logger.clearWindow();
        // Prints firing system state
        logger.printLine(Line.kUser1, "FSS: " + firingSystem.getState());
        // Prints information of Launcher
        logger.printLine(Line.kUser2, "Launcher: " + (firingSystem.getLauncherMotorState() ? "ON [" : "OFF [") + (byte) (firingSystem.getLauncherSpeed() * 100) + "%" + "]");
        //logger.printLine(Line.kUser3, "State: " + (firingSystem.getIndexerMotorState() ? "INDEXING" : firingSystem. ? "READY" : "NOT LOADED"));
        // Print the tank pressurization state.
        logger.printLine(Line.kUser3, "Tanks: " + (compressor.getPressureSwitchValue() ? "FULL" : "COMPRESSING"));
        // Updates the output window.
        logger.updateLCD();
    }
}
