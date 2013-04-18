package com.frc4343.robot2;

import com.frc4343.robot2.Systems.JoystickSystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotTemplate extends IterativeRobot {

    Logger logger = new Logger();
    Piston climbingPiston = new Piston(Mappings.CLIMBING_PISTON_SOLENOID_ONE, Mappings.CLIMBING_PISTON_SOLENOID_TWO, Mappings.CLIMBING_PISTON_EXTENDED_BY_DEFAULT);
    JoystickSystem joystickSystem = new JoystickSystem(this);
    // Timers
    Timer timer = new Timer();
    Timer climbTimer = new Timer();
    Timer indexerReverseDelayTimer = new Timer();
    Timer autonomousDriveTimer = new Timer();
    // Non-Drive Motors
    Victor launcherMotor = new Victor(Mappings.LAUNCH_MOTOR_RELAY_PORT);
    Relay indexerMotor = new Relay(Mappings.INDEX_MOTOR_RELAY_PORT);
    // Drive Motors
    RobotDrive robotDrive = new RobotDrive(1, 2);
    // Solenoids
    Solenoid[] solenoids = new Solenoid[Mappings.SOLENOID_COUNT];
    // Compressor
    Compressor compressor = new Compressor(Mappings.COMPRESSOR_DIGITAL_IO, Mappings.COMPRESSOR_RELAY);
    // DigitalInput - Limit Switches
    DigitalInput indexerLimitSwitch = new DigitalInput(Mappings.INDEX_LIMIT_SWITCH);
    double launcherSpeed = Mappings.DEFAULT_LAUNCHER_SPEED;
    // Whether or not the launch speed launcherMotor buttons are being pressed.
    boolean isLauncherMotorRunning = false;
    boolean isIndexerMotorRunning = false;
    boolean isFrisbeeLoaded = false;
    boolean isFrisbeeStuck = false;
    boolean driveBack = false;
    boolean turn180 = false;
    // This section is relevant only to autonomous.
    boolean isInitialAutonomousDelayOver = false;
    boolean readyToIndexNextFrisbee = false;
    byte numberOfFrisbeesFired = 0;

    public void robotInit() {
        // Initialize solenoids
        for (byte i = 0; i < solenoids.length; i++) {
            solenoids[i] = new Solenoid(i + 1);
        }
    }

    public void teleopInit() {
        init();
    }

    public void teleopPeriodic() {
        // This combines the axes in order to allow for both joysticks to control the robot's movement.
        // One of the joysticks will be made less sensitive to allow for precision control.
        double sumOfYAxes = joystickSystem.getAxis(1, AxisType.kX) + (joystickSystem.getAxis(2, AxisType.kX) * Mappings.ALTERNATE_COMPENSATION);
        double sumOfXAxes = -joystickSystem.getAxis(1, AxisType.kY) * Mappings.AXIS_COMPENSATION;
        // Floor the values of the combined joysticks in case they are above 1 or below -1.
        sumOfYAxes = sumOfYAxes > 1 ? 1 : sumOfYAxes < -1 ? -1 : sumOfYAxes;
        sumOfXAxes = sumOfXAxes > 1 ? 1 : sumOfXAxes < -1 ? -1 : sumOfXAxes;

        robotDrive.arcadeDrive(sumOfYAxes, sumOfXAxes);
        handleHopperAndLauncherControls();
        handleIndexer();
        handleLauncherMotorSettings();
        handleSolenoids();
        handleConsoleOutputAndMotorBooleans();
    }

    public void autonomousInit() {
        init();
    }

    private void init() {
        compressor.start();
        setPistonExtended(true); // Extend launcher piston
        numberOfFrisbeesFired = 0; // Allow re-enabling of autonomous
        isFrisbeeLoaded = false; // Assume no frisbees are loaded
        // Reset the timer if coming from autonomous.
        timer.reset();
        timer.stop();
        if (isAutonomous()) {
            isInitialAutonomousDelayOver = false;
            isLauncherMotorRunning = true;
            isIndexerMotorRunning = true;
            launcherSpeed = 0.5;
            timer.start();
        } else if (isOperatorControl()) {
            climbTimer.reset();
            climbTimer.start();
            isLauncherMotorRunning = true;
            isIndexerMotorRunning = false;
        }
    }

    public void autonomousPeriodic() {
        // If the autonomous delay has not finished previously and the is now over, set the boolean and reset the timer.
        if (timer.get() >= Mappings.DELAY_BEFORE_FIRST_SHOT && !isInitialAutonomousDelayOver) {
            launcherSpeed = 0.4;
            isInitialAutonomousDelayOver = true;
            timer.reset();
        } else if (isInitialAutonomousDelayOver && numberOfFrisbeesFired < Mappings.FRISBEES_TO_FIRE) {
            // Once the delay per shot has been reached, fire the next frisbee.
            if (timer.get() >= Mappings.DELAY_BETWEEN_SHOTS && isFrisbeeLoaded) {
                // Increment the frisbee count, retract the piston, and reset the timer.
                numberOfFrisbeesFired++;
                setPistonExtended(false);
                timer.reset();
            }
        }
        driveInAutonomous();
        handleIndexer();
        handleSolenoids();
        handleConsoleOutputAndMotorBooleans();
    }

    private void handleIndexer() {
        // If 0.4 s has passed since the piston retracted, we can index the next frisbee, if there is still one left.
        // If there are no frisbees left, we do not attempt to index another, but rather reset and stop the the timer.
        if (timer.get() > Mappings.DELAY_TO_PISTON_RETRACTION + 0.4 && isInitialAutonomousDelayOver && !isFrisbeeLoaded && isAutonomous() && numberOfFrisbeesFired != Mappings.FRISBEES_TO_FIRE) {
            isIndexerMotorRunning = true;
        }
        if (indexerLimitSwitch.get()) { // If frisbee hits limitswitch
            isIndexerMotorRunning = false;
            isFrisbeeLoaded = true;
            if (isOperatorControl()) {
                timer.reset();
                timer.stop();
            }
        } else if (timer.get() >= Mappings.INDEXER_TIMEOUT && isOperatorControl()) {
            isIndexerMotorRunning = false;
            timer.reset();
            timer.stop();
        }
    }

    private void driveInAutonomous() {
        if (numberOfFrisbeesFired == Mappings.FRISBEES_TO_FIRE) {
            autonomousDriveTimer.start();
            timer.reset();
            timer.stop();
        }
        if (!driveBack && !turn180 && autonomousDriveTimer.get() > 0.15 && autonomousDriveTimer.get() < 0.45) {
            driveBack = true;
            autonomousDriveTimer.reset();
        }
        if (driveBack && autonomousDriveTimer.get() <= Mappings.REVERSE_DURATION) {
            robotDrive.arcadeDrive(1.0, 0.0);
        } else if (driveBack && autonomousDriveTimer.get() > Mappings.REVERSE_DURATION) {
            robotDrive.arcadeDrive(0.0, 0.0);
            autonomousDriveTimer.reset();
            driveBack = false;
            turn180 = true;
        } else if (turn180 && autonomousDriveTimer.get() >= 0.1 && autonomousDriveTimer.get() <= Mappings.ROTATE_DURATION) {
            robotDrive.tankDrive(-1.0, 1.0);
        } else if (turn180 && autonomousDriveTimer.get() > Mappings.ROTATE_DURATION + 0.5) {
            robotDrive.arcadeDrive(0.0, 0.0);
            autonomousDriveTimer.reset();
            autonomousDriveTimer.stop();
            turn180 = false;
        }
    }

    private void handleConsoleOutputAndMotorBooleans() {
        if (!isFrisbeeStuck) {
            indexerMotor.set(isIndexerMotorRunning ? Relay.Value.kForward : Relay.Value.kOff);
        }
        launcherMotor.set(isLauncherMotorRunning ? launcherSpeed : 0);
        // Update the output screen.
        updateLCD();
    }

    private void handleHopperAndLauncherControls() {
        // If the trigger is pressed.
        if (joystickSystem.isButtonPressed(1, Mappings.INDEX_AND_FIRE)) {
            timer.start();
            if (isFrisbeeLoaded) {
                // If there is a frisbee in the launcher, then it launches it.
                setPistonExtended(false);
                launcherSpeed = 0.6;
            } else {
                isIndexerMotorRunning = true;
            }
        } else if (joystickSystem.getButton(1, Mappings.EJECT_STUCK_FRISBEE)) {
            isFrisbeeStuck = true;
            indexerMotor.set(Relay.Value.kReverse);
            indexerReverseDelayTimer.start();
        } else if (isFrisbeeStuck && indexerReverseDelayTimer.get() >= 1 && isFrisbeeStuck) {
            indexerMotor.set(Relay.Value.kOff);
            isFrisbeeStuck = false;
            isIndexerMotorRunning = false;
            indexerReverseDelayTimer.reset();
            indexerReverseDelayTimer.stop();
        }
    }

    private void handleLauncherMotorSettings() {
        // Check if the motor is being run.
        if (joystickSystem.getButton(1, Mappings.LAUNCHER_MOTOR_ENABLE) || joystickSystem.getButton(2, Mappings.LAUNCHER_MOTOR_ENABLE)) {
            isLauncherMotorRunning = true;
        } else if (joystickSystem.getButton(1, Mappings.LAUNCHER_MOTOR_DISABLE) || joystickSystem.getButton(2, Mappings.LAUNCHER_MOTOR_DISABLE)) {
            isLauncherMotorRunning = false;
        } else if (joystickSystem.isButtonPressed(1, Mappings.MANUAL_EJECT)) { // Manually eject the frisbee
            setPistonExtended(false);
            timer.start();
        } else if (joystickSystem.isButtonPressed(1, Mappings.LAUNCHER_SPEED_INCREASE)) { // Handle the speed change.
            launcherSpeed += 0.01;
        } else if (joystickSystem.isButtonPressed(1, Mappings.LAUNCHER_SPEED_DECREASE)) {
            launcherSpeed -= 0.01;
        }
    }

    private void handleSolenoids() {
        // If the piston retraction delay has passed, begin to retract the piston.
        if (timer.get() > Mappings.DELAY_TO_PISTON_RETRACTION && timer.get() < (Mappings.DELAY_TO_PISTON_RETRACTION + 0.2)) {
            setPistonExtended(true);
            if (isOperatorControl()) {
                launcherSpeed = 0.4;
            }
            isFrisbeeLoaded = false;
        }
        if (climbTimer.get() < 0) {
            climbTimer.stop();
        }
        if (joystickSystem.getButton(1, Mappings.L3)) {
            setPistonExtended(false);
        } else if (joystickSystem.getButton(1, Mappings.R3)) {
            setPistonExtended(true);
        }

        climbingHandler();
    }

    private void setPistonExtended(boolean extended) {
        solenoids[0].set(extended);
        solenoids[1].set(!extended);
    }

    private void climbingHandler() {
        if (joystickSystem.getButton(1, Mappings.EXTEND_CLIMBING_PISTONS)) {
            climbingPiston.extend();
        } else if (joystickSystem.getButton(1, Mappings.RETRACT_CLIMBING_PISTONS)) {
            climbingPiston.retract();
        }
    }

    private void updateLCD() {
        // Clears driverStation text.
        logger.clearWindow();
        // Prints State of Frisbee
        logger.printLine(Line.kUser1, "Ready To Fire: " + (isFrisbeeLoaded ? "YES" : "NO"));
        // Print the speed, and launcher status.
        logger.printLine(Line.kUser2, "Launcher: " + (isLauncherMotorRunning ? "ON, " : "OFF, ") + (launcherSpeed * 100));
        // Prints State of Launcher Motor
        logger.printLine(Line.kUser3, "Indexer: " + (isIndexerMotorRunning ? "ON" : "OFF"));
        // Print the tank pressurization state.
        logger.printLine(Line.kUser4, "Tanks Full? " + (compressor.getPressureSwitchValue() ? "YES" : "NO"));
        // Auto hang timeout
        logger.printLine(Line.kUser5, "AutoHang: " + (climbTimer.get() >= 0 ? 120 - climbTimer.get() : 0));
        // Updates the output window.
        logger.updateLCD();
    }
}
