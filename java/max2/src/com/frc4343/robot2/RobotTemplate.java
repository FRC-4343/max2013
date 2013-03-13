package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotTemplate extends IterativeRobot {

    Logger logger = new Logger();
    Timer indexingTimer = new Timer();
    Timer loadingDelayTimer = new Timer();
    Timer accelerationTimer = new Timer();
    Joystick joystick = new Joystick(1);
    Joystick joystick2 = new Joystick(2);
    Victor launcher = new Victor(3);
    Relay indexer = new Relay(2);
    RobotDrive robotDrive = new RobotDrive(1, 2);
    Piston firingPiston = new Piston((byte) 1, (byte) 2, true);
    Piston climbingPiston = new Piston((byte) 3, (byte) 4, true);
    Compressor compressor = new Compressor(1, 1);
    DigitalInput indexerLimitSwitch = new DigitalInput(2);

    // The default speed for the launch motor to start at.
    double launcherSpeed = 0.4;
    double axisCompensation = 0.8;
    double indexerTimeoutInSeconds = 1.5;
    double loadingDelay = 0.15;
    double accelerationDelay = 0.1;

    // Motor Booleans
    boolean launcherMotor = false;
    boolean indexerMotor = false;

    // Auto-Fire Booleans
    boolean isIndexing = false;
    boolean frisbeeLoaded = false;

    // Button mappings
    final byte TRIGGER = 1;
    final byte SPEED_DECREASE = 4;
    final byte SPEED_INCREASE = 5;
    final byte LAUNCHER_MOTOR_ENABLE = 6;
    final byte LAUNCHER_MOTOR_DISABLE = 7;
    final byte EXTEND_CLIMBING_PISTONS = 3;
    final byte RETRACT_CLIMBING_PISTONS = 2;
    // Button Checks
    boolean triggerHeld = false;
    boolean adjustedSpeed = false;

    // This section is relevant only to autonomous.
    boolean initialAutonomousDelayOver = false;
    byte numberOfFrisbeesFiredInAutonomous = 0;
    byte maxFrisbeesToFireInAutonomous = 3;

    final double autonomousDelayBeforeFirstShot = 4;
    final double autonomousDelayBetweenEachShot = 3;
    final double launcherSpeedAtPyramidBack = 0.4;


    private void resetRobot() {
        compressor.start();
        // Reset the timer.
        loadingDelayTimer.reset();
        loadingDelayTimer.stop();
        // Reset the pistons to their default positions.
        firingPiston.extend();
        climbingPiston.extend();
        // Reset the number of fired frisbees in autonomous to zero and reset the timer delay to allow for the re-enabling of autonomous.
        numberOfFrisbeesFiredInAutonomous = 0;
        // Launcher motor will be enabled in case the drivers forget.
        launcherMotor = true;
    }

    public void teleopInit() {
        // Initialize the compressor, reset the values, disable the motors.
        resetRobot();
        indexerMotor = false;
    }

    public void teleopPeriodic() {
        /*// This combines the axes in order to allow for both joysticks to control the robot's movement.
        // One of the joysticks will be made less sensitive to allow for precision control.
        double sumXAxes = joystick2.getAxis(Joystick.AxisType.kY) + (joystick.getAxis(Joystick.AxisType.kY) * 0.5);
        double sumYAxes = -joystick2.getAxis(Joystick.AxisType.kX) * axisCompensation + ((-joystick.getAxis(Joystick.AxisType.kX) * axisCompensation) * 0.4);

        // Floor the values of the combined joysticks in case they are above 1 or below -1.
        sumXAxes = sumXAxes > 1 ? 1 : sumXAxes;
        sumXAxes = sumXAxes < -1 ? -1 : sumXAxes;
        sumYAxes = sumYAxes > 1 ? 1 : sumYAxes;
        sumYAxes = sumYAxes < -1 ? -1 : sumYAxes;

        robotDrive.arcadeDrive(sumXAxes, sumYAxes);*/

        // The previous code *allegedly* did not allow for the y axis on the second joystick to function.
        // This new code will arcade drive twice (once for each joystick) to allow for precision control.
        robotDrive.arcadeDrive(joystick.getAxis(Joystick.AxisType.kX), joystick.getAxis(Joystick.AxisType.kY) * axisCompensation);
        robotDrive.arcadeDrive(joystick2.getAxis(Joystick.AxisType.kX) * 0.5, joystick2.getAxis(Joystick.AxisType.kY) * 0.5);

        firingHandler();
        launcherMotorHandler();
        climbingHandler();

        handleConsoleOutputAndMotorBooleans();
    }

    public void autonomousInit() {
        resetRobot();
        // The delay which occurs at the beginning of autonomous must be reset.
        initialAutonomousDelayOver = false;
        launcherSpeed = launcherSpeedAtPyramidBack;

        indexerMotor = true;
-       loadingDelayTimer.start();
    }

    public void autonomousPeriodic() {
        frisbeeLoaded = loadingDelayTimer.get() >= loadingDelay;
        // Disable the indexer motor if a frisbee triggers the limit switch.
        if (indexerLimitSwitch.get()) {
            isIndexing = true;
            indexerMotor = false;
        }

        // If there is no frisbee in the chamber (i.e. one has just been fired), we extend the piston to its initial state.
        if (!frisbeeLoaded) {
            firingPiston.extend();
        }

        // If the autonomous delay has not finished previously and the delay is now over, set the boolean and reset the timer.
        if (loadingDelayTimer.get() >= autonomousDelayBeforeFirstShot && !initialAutonomousDelayOver) {
            initialAutonomousDelayOver = true;
            loadingDelayTimer.reset();
        }

        // Once the initial delay in autonomous has passed, we can begin firing frisbees.
        if (initialAutonomousDelayOver) {
            // If the number of frisbees already fired does not exceed the number of frisbees we want to fire during autonomous, we attempt to fire another one.
            if (numberOfFrisbeesFiredInAutonomous <= maxFrisbeesToFireInAutonomous) {
                // If there is a frisbee in the chamber, ready to be fired, and the the delay between each shot has been passed, we fire the frisbee.
                if (frisbeeLoaded && loadingDelayTimer.get() >= autonomousDelayBetweenEachShot) {
                    // We increment the number of frisbees fired, set the launcher speed to 100% temporarily (to reduce wheel spin-up time), and begin the acceleration timer.
                    numberOfFrisbeesFiredInAutonomous++;
                    launcherSpeed = 1;
                    accelerationTimer.start();
                }
            }
        }

        // If the acceleration delay has been completed, we release the frisbee.
        if (accelerationTimer.get() >= accelerationDelay) {
            // Retract the piston to release the frisbee, stop the indexer motor, register the chamber as frisbee-less, reset the loading timer, return the launcher speed to its original state, and stop and reset the acceleration timer.
            firingPiston.retract();
            isIndexing = false;
            frisbeeLoaded = false;
            loadingDelayTimer.reset();
            launcherSpeed = 0.4;
            accelerationTimer.stop();
            accelerationTimer.reset();
        }

        // If we have passed the initial autonomous delay, there is no frisbee in the loader, and the indexer motor is not running, we turn on the indexer motor.
        if (initialAutonomousDelayOver && !frisbeeLoaded && !isIndexing) {
            indexerMotor = true;
        }

        handleConsoleOutputAndMotorBooleans();
    }

    private void handleConsoleOutputAndMotorBooleans() {
        // Set the state of the motors based on the values of the booleans controlling them.
        indexer.set(indexerMotor ? Relay.Value.kForward : Relay.Value.kOff);
        launcher.set(launcherMotor ? launcherSpeed : 0);

        // Print the debug output the the DriverStation console.
        printConsoleOutput();
    }

    private void launcherMotorHandler() {
        // MManually handle the state of the launcher motor. (Not intended to be used in competition)
        if (joystick.getRawButton(LAUNCHER_MOTOR_ENABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_ENABLE)) {
            launcherMotor = true;
        } else if (joystick.getRawButton(LAUNCHER_MOTOR_DISABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_DISABLE)) {
            launcherMotor = false;
        }

        // If the buttons are not being held down or pressed together, increase or decrease the speed of the launcher motor.
        if ((joystick.getRawButton(SPEED_INCREASE) ^ joystick.getRawButton(SPEED_DECREASE) && !adjustedSpeed) {
            if (joystick.getRawButton(SPEED_INCREASE)) {
                launcherSpeed += 0.001;
            }
            if (joystick.getRawButton(SPEED_DECREASE)) {
                launcherSpeed -= 0.001;
            }
        }

        // Store the state of whether or not the buttons have been pressed, to know if they are being held down in the next iteration.
        adjustedSpeed = joystick.getRawButton(SPEED_INCREASE) ^ joystick.getRawButton(SPEED_DECREASE);
    }

    private void firingHandler() {
        frisbeeLoaded = loadingDelayTimer.get() >= loadingDelay;

        if (frisbeeLoaded) {
            loadingDelayTimer.stop();
            loadingDelayTimer.reset();
        } else if (!frisbeeLoaded) {
            firingPiston.extend();
        }

        if (indexerLimitSwitch.get() || indexingTimer.get() >= indexerTimeoutInSeconds) {
            indexerMotor = false;
            indexingTimer.stop();
            indexingTimer.reset();
            if (indexerLimitSwitch.get() && !(indexingTimer.get() >= indexerTimeoutInSeconds)) {
                isIndexing = true;
                loadingDelayTimer.start();
            }
        }

        if (joystick.getRawButton(TRIGGER)) {
            if (!triggerHeld) {
                if (frisbeeLoaded) {
                    launcherSpeed = 1;
                    accelerationTimer.start();
                } else if (!frisbeeLoaded && !isIndexing) {
                    indexerMotor = true;
                    indexingTimer.start();
                }
            }
            triggerHeld = joystick.getRawButton(TRIGGER);
        }
        if (accelerationTimer.get() >= accelerationDelay) {
            firingPiston.retract();
            isIndexing = false;
            frisbeeLoaded = false;
            launcherSpeed = 0.4;
            accelerationTimer.stop();
            accelerationTimer.reset();
        }
        // Just in case, keeping manual eject :P
        if (joystick.getRawButton(9)) {
            firingPiston.retract();
            frisbeeLoaded = false;
        }
    }

    private void climbingHandler() {
        if (joystick.getRawButton(EXTEND_CLIMBING_PISTONS)) {
            climbingPiston.extend();
        } else if (joystick.getRawButton(RETRACT_CLIMBING_PISTONS)) {
            climbingPiston.retract();
        }
    }

    private void printConsoleOutput() {
        // Clears driverStation text.
        logger.clearWindow();
        // Prints State of Frisbee
        logger.printLine(Line.kUser1, frisbeeLoaded ? "Frisbee Loaded: YES" : "Frisbee Loaded: NO");
        // Print the speed.
        logger.printLine(Line.kUser2, "Launcher Speed: " + (byte)(launcherSpeed * 100) + "%");
        // Prints State of Launcher Motor
        logger.printLine(Line.kUser3, launcherMotor ? "Launcher Motor: ON" : "Launcher Motor: OFF");
        // Prints State of Launcher Motor
        logger.printLine(Line.kUser4, indexerMotor ? "Indexer Motor: ON" : "Indexer Motor: OFF");
        // Print the tank pressurization state.
        logger.printLine(Line.kUser5, compressor.getPressureSwitchValue() ? "Tanks Full: YES" : "Tanks Full: NO");
        // Updates the output window.
        logger.updateLCD();
    }
}
