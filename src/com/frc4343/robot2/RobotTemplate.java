package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class RobotTemplate extends IterativeRobot {

    Logger logger = new Logger();
    RobotDrive robotDrive = new RobotDrive(1, 2);
    Piston climbingPiston = new Piston((byte) 3, (byte) 4, true);
    Compressor compressor = new Compressor(1, 1);
    FiringSystem firingSystem = new FiringSystem(this);
    JoystickSystem joystickSystem = new JoystickSystem(this);
    GyroSystem gyroSystem = new GyroSystem(this);
    double axisCompensation = 0.5;
    // Button mappings
    final byte EXTEND_CLIMBING_PISTONS = 3;
    final byte RETRACT_CLIMBING_PISTONS = 2;

    private void resetRobot() {
        compressor.start();
        // Reset the climber piston to its initial position.
        climbingPiston.extend();
        // Initialize the firing system.
        firingSystem.switchMode();
        // Initialize the gyro system.
        gyroSystem.reset();
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

        robotDrive.arcadeDrive(joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kX), joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kY));
        robotDrive.arcadeDrive(joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kX) * axisCompensation, joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kY) * axisCompensation);

        climbingHandler();

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
    }

    public void autonomousPeriodic() {
        firingSystem.run();

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

    public void testPeriodic() {
        firingSystem.run();
        gyroSystem.run();

        //robotDrive.arcadeDrive(joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kX), joystickSystem.getJoystick(1).getAxis(Joystick.AxisType.kY));
        //robotDrive.arcadeDrive(joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kX) * axisCompensation, joystickSystem.getJoystick(2).getAxis(Joystick.AxisType.kY) * axisCompensation);

        climbingHandler();

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
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
