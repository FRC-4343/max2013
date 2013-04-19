package com.frc4343.robot2;

import com.frc4343.robot2.Systems.DriveSystem;
import com.frc4343.robot2.Systems.FiringSystem;
import com.frc4343.robot2.Systems.GyroSystem;
import com.frc4343.robot2.Systems.JoystickSystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotTemplate extends IterativeRobot {

    // Systems and subsystems
    Logger logger = new Logger();
    Piston climbingPiston = new Piston(Mappings.CLIMBING_PISTON_SOLENOID_ONE, Mappings.CLIMBING_PISTON_SOLENOID_TWO, Mappings.CLIMBING_PISTON_EXTENDED_BY_DEFAULT);
    Piston firingPiston = new Piston(Mappings.FIRING_PISTON_SOLENOID_ONE, Mappings.FIRING_PISTON_SOLENOID_TWO, Mappings.FIRING_PISTON_EXTENDED_BY_DEFAULT);
    public JoystickSystem joystickSystem = new JoystickSystem(this);
    public DriveSystem driveSystem = new DriveSystem(this);
    public FiringSystem firingSystem = new FiringSystem(this);
    public GyroSystem gyroSystem = new GyroSystem(this);
    // Compressor
    Compressor compressor = new Compressor(Mappings.COMPRESSOR_DIGITAL_IO, Mappings.COMPRESSOR_RELAY);

    // Timers
    Timer fireTimer = new Timer();
    Timer autonomousInitialDelayTimer = new Timer();
    Timer climbTimer = new Timer();
    Timer indexerReverseDelayTimer = new Timer();
    Timer autonomousDriveTimer = new Timer();

    // Drive motors
    RobotDrive robotDrive = new RobotDrive(1, 2);
    // Non-drive motors
    Victor launcherMotor = new Victor(Mappings.LAUNCH_MOTOR_RELAY_PORT);
    Relay indexerMotor = new Relay(Mappings.INDEX_MOTOR_RELAY_PORT);

    // Limit switches
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

    private void init() {
        compressor.start();
        firingPiston.extend();
        numberOfFrisbeesFired = 0;
        isFrisbeeLoaded = false; // BUG: This can probably cause double-indexing upon state switch.
        isLauncherMotorRunning = true;
        isInitialAutonomousDelayOver = false;
        launcherSpeed = Mappings.DEFAULT_LAUNCHER_SPEED;

        fireTimer.reset();
        fireTimer.stop();
        autonomousInitialDelayTimer.reset();
        autonomousInitialDelayTimer.stop();
        climbTimer.reset();
        climbTimer.stop();
        indexerReverseDelayTimer.reset();
        indexerReverseDelayTimer.stop();
        autonomousDriveTimer.reset();
        autonomousDriveTimer.stop();

        if (isAutonomous()) {
            isIndexerMotorRunning = true;
            autonomousInitialDelayTimer.start();
        } else if (isOperatorControl()) {
            isIndexerMotorRunning = false;
            climbTimer.start();
        }
    }

    public void autonomousInit() {
        init();
    }

    public void autonomousPeriodic() {
        if (!isInitialAutonomousDelayOver && autonomousInitialDelayTimer.get() >= Mappings.DELAY_BEFORE_FIRST_SHOT) {
            isInitialAutonomousDelayOver = true;

            autonomousInitialDelayTimer.reset();
            autonomousInitialDelayTimer.stop();
            fireTimer.start();
        } else if (isInitialAutonomousDelayOver && numberOfFrisbeesFired < Mappings.FRISBEES_TO_FIRE) {
            // Once the delay per shot has been reached, fire the next frisbee.
            if (fireTimer.get() >= Mappings.DELAY_BETWEEN_SHOTS && isFrisbeeLoaded) {
                // Increment the frisbee count, retract the piston, and reset the fireTimer.
                numberOfFrisbeesFired++;
                firingPiston.retract();
                fireTimer.reset();
            }
        }

        driveInAutonomous();

        handleIndexer();
        handleSolenoids();
        handleConsoleOutputAndMotorBooleans();
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
        handleLauncherMotorSettings();

        handleIndexer();
        handleSolenoids();
        handleConsoleOutputAndMotorBooleans();
    }

    private void handleIndexer() {
        // If 0.4 s has passed since the piston retracted, we can index the next frisbee, if there is still one left.
        // If there are no frisbees left, we do not attempt to index another, but rather reset and stop the the fireTimer.
        if (isInitialAutonomousDelayOver && fireTimer.get() > Mappings.DELAY_TO_PISTON_RETRACTION + Mappings.TIME_FOR_FRISBEE_TO_FALL && !isFrisbeeLoaded && isAutonomous()) {
            isIndexerMotorRunning = true;
        }
        if (indexerLimitSwitch.get()) {
            isIndexerMotorRunning = false;
            isFrisbeeLoaded = true;
            if (isOperatorControl()) {
                fireTimer.reset();
                fireTimer.stop();
            }
        } else if (fireTimer.get() >= Mappings.INDEXER_TIMEOUT && isOperatorControl()) {
            isIndexerMotorRunning = false;
            fireTimer.reset();
            fireTimer.stop();
        }
    }

    private void driveInAutonomous() {
        if (numberOfFrisbeesFired == Mappings.FRISBEES_TO_FIRE) {
            fireTimer.reset();
            fireTimer.stop();
            autonomousDriveTimer.start();
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
        if (joystickSystem.isButtonPressed(1, Mappings.INDEX_AND_FIRE)) {
            fireTimer.start();
            if (isFrisbeeLoaded) {
                firingPiston.retract();
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
        } else if (joystickSystem.isButtonPressed(1, Mappings.MANUAL_EJECT)) {
            firingPiston.retract();
            fireTimer.start();
        } else if (joystickSystem.isButtonPressed(1, Mappings.LAUNCHER_SPEED_INCREASE)) {
            launcherSpeed += 0.01;
        } else if (joystickSystem.isButtonPressed(1, Mappings.LAUNCHER_SPEED_DECREASE)) {
            launcherSpeed -= 0.01;
        }
    }

    private void handleSolenoids() {
        // If the piston retraction delay has passed, begin to retract the piston.
        if (fireTimer.get() > Mappings.DELAY_TO_PISTON_RETRACTION && fireTimer.get() < (Mappings.DELAY_TO_PISTON_RETRACTION + 0.2)) {
            firingPiston.extend();
            if (isOperatorControl()) {
                launcherSpeed = 0.4;
            }
            isFrisbeeLoaded = false;
        }
        if (climbTimer.get() < 0) {
            climbTimer.stop();
        }
        if (joystickSystem.getButton(1, Mappings.L3)) {
            firingPiston.retract();
        } else if (joystickSystem.getButton(1, Mappings.R3)) {
            firingPiston.extend();
        }

        climbingHandler();
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
