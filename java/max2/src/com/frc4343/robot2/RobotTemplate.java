package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotTemplate extends IterativeRobot {
    DriverStationLCD dsLCD = DriverStationLCD.getInstance();
    Timer timer = new Timer();
    Joystick joystick = new Joystick(1);
    Joystick joystick2 = new Joystick(2);
    Victor launcherMotor = new Victor(3);
    Relay indexerMotor = new Relay(2);
    RobotDrive robotDrive = new RobotDrive(1, 2);
    Solenoid[] solenoids = new Solenoid[4];
    Compressor compressor = new Compressor(1, 1);
    DigitalInput indexerLimitSwitch = new DigitalInput(2);

    // The default speed for the launch motor to start at.
    double speed = 0.32;
    double axisCompensation = 0.55;
    double indexerTimeoutInSeconds = 1.5;

    // Whether or not the launch speed launcherMotor buttons are being pressed.
    //boolean previouslyChangedSpeeds = false;
    boolean isLauncherMotorRunning = false;
    boolean isIndexerMotorRunning = false;
    boolean isFrisbeeLoaded = false;
    boolean isTriggerHeld = false;
    boolean adjustSpeed = false;
    boolean previouslyChangedSpeeds = false;

    // Button mappings
    final byte TRIGGER = 1;
    final byte SPEED_DECREASE = 4;
    final byte SPEED_INCREASE = 5;
    final byte LAUNCHER_MOTOR_ENABLE = 6;
    final byte LAUNCHER_MOTOR_DISABLE = 7;
    final byte EXTEND_CLIMBING_PISTONS = 3;
    final byte RETRACT_CLIMBING_PISTONS = 2;

    // This section is relevant only to autonomous.
    byte numberOfFrisbeesFiredInAutonomous = 0;
    byte maximumFrisbeesToFireInAutonomous = 3;
    final double autonomousDelayBetweenEachShot = 3;
    final double autonomousDelayBeforeFirstShot = 4;
    boolean isInitialAutonomousDelayOver = false;
    final byte autonomousFireType = 0;
    boolean readyToIndexNextFrisbee = false;
    final double delayToPistonRetraction = 0.1;

    private void resetRobot() {
        timer.reset();
        timer.stop();
        setPistonExtended(true);
        numberOfFrisbeesFiredInAutonomous = 0;
        isInitialAutonomousDelayOver = false;
    }

    public void robotInit() {
        // Initialize solenoids
        for (int i = 0; i < solenoids.length; i++) {
            solenoids[i] = new Solenoid(i + 1);
        }

        compressor.start();
    }

    public void teleopInit() {
        compressor.start();
        resetRobot();
        isLauncherMotorRunning = false;
        isIndexerMotorRunning = false;
    }

    public void teleopPeriodic() {
        // Activate Arcade Drive
        double sumXAxes = joystick2.getAxis(Joystick.AxisType.kY) + (joystick.getAxis(Joystick.AxisType.kY) * 0.5);
        double sumYAxes = -joystick2.getAxis(Joystick.AxisType.kX) * axisCompensation + ((-joystick.getAxis(Joystick.AxisType.kX) * axisCompensation) * 0.9);

        // Floor the values of the combined joysticks in case they are above 1 or below -1.
        sumXAxes = sumXAxes > 1 ? 1 : sumXAxes;
        sumXAxes = sumXAxes < -1 ? -1 : sumXAxes;
        sumYAxes = sumYAxes > 1 ? 1 : sumYAxes;
        sumYAxes = sumYAxes < -1 ? -1 : sumYAxes;

        robotDrive.arcadeDrive(sumXAxes, sumYAxes);

        handleTimerAndFrisbeeLoadedState();
        handleLauncherMotor();
        solenoidHandler();

        handleConsoleOutputAndMotorBooleans();
    }

    public void autonomousInit() {
        compressor.stop();
        resetRobot();
        isLauncherMotorRunning = true;
        isIndexerMotorRunning = true;
        if (autonomousFireType == 0) {
            speed = 0.32;
            maximumFrisbeesToFireInAutonomous = 3;
        } else if (autonomousFireType == 1) {
            speed = 0.38;
            maximumFrisbeesToFireInAutonomous = 2;
        }
        timer.start();
    }

    public void autonomousPeriodic() {
        if (indexerLimitSwitch.get()) {
            isIndexerMotorRunning = false;
        }

        if (timer.get() >= autonomousDelayBeforeFirstShot && !isInitialAutonomousDelayOver) {
            isInitialAutonomousDelayOver = true;
            timer.reset();
        }

        if (autonomousFireType == 0) {
            if (isInitialAutonomousDelayOver) {
                if (numberOfFrisbeesFiredInAutonomous <= maximumFrisbeesToFireInAutonomous) {
                    if (numberOfFrisbeesFiredInAutonomous < maximumFrisbeesToFireInAutonomous) {
                        if (timer.get() > autonomousDelayBetweenEachShot) {
                            numberOfFrisbeesFiredInAutonomous += 1;
                            setPistonExtended(false);
                            timer.reset();

                            if (numberOfFrisbeesFiredInAutonomous != maximumFrisbeesToFireInAutonomous)
                                readyToIndexNextFrisbee = true;
                        }
                    }
                    if (timer.get() > delayToPistonRetraction && timer.get() < 1.5) {
                        setPistonExtended(true);
                    }
                    if (timer.get() > delayToPistonRetraction + 0.5) {
                        if (readyToIndexNextFrisbee) {
                            isIndexerMotorRunning = true;
                            readyToIndexNextFrisbee = false;
                        }
                    }
                }
            }
        } else if (autonomousFireType == 1) {
            setClimbingPistonExtended(false);
            if (isInitialAutonomousDelayOver) {
                if (numberOfFrisbeesFiredInAutonomous <= maximumFrisbeesToFireInAutonomous) {
                    if (numberOfFrisbeesFiredInAutonomous < maximumFrisbeesToFireInAutonomous) {
                        if (timer.get() > autonomousDelayBetweenEachShot) {
                            numberOfFrisbeesFiredInAutonomous += 1;
                            setPistonExtended(false);
                            timer.reset();

                            if (numberOfFrisbeesFiredInAutonomous != maximumFrisbeesToFireInAutonomous)
                                readyToIndexNextFrisbee = true;
                        }
                    }
                    if (timer.get() > delayToPistonRetraction && timer.get() < 1.5) {
                        setPistonExtended(true);
                    }
                    if (timer.get() > delayToPistonRetraction + 0.5) {
                        if (readyToIndexNextFrisbee) {
                            isIndexerMotorRunning = true;
                            readyToIndexNextFrisbee = false;
                        }
                    }
                }
            }
        }

        handleConsoleOutputAndMotorBooleans();
    }

    private void handleConsoleOutputAndMotorBooleans() {
        if (timer.get() > delayToPistonRetraction && timer.get() < 0.5) {
            setPistonExtended(true);
        }
        if (timer.get() > delayToPistonRetraction + 0.5 && readyToIndexNextFrisbee) {
            isIndexerMotorRunning = true;
            readyToIndexNextFrisbee = false;
        }

        indexerMotor.set(isIndexerMotorRunning ? Relay.Value.kForward : Relay.Value.kOff);
        launcherMotor.set(isLauncherMotorRunning ? speed : 0);

        // Clears driverStation text.
        clearWindow();
        dsLCD.println(Line.kUser1, 1, "Z Axis: " + (-joystick.getZ()));
        // Prints State of Frisbee
        dsLCD.println(Line.kUser2, 1, isFrisbeeLoaded ? "Frisbee Loaded: True" : "Frisbee Loaded: False");
        // Print the speed.
        dsLCD.println(Line.kUser3, 1, "Launcher Speed: " + speed * 100 + "%");
        // Prints State of Launcher Motor
        dsLCD.println(Line.kUser4, 1, isLauncherMotorRunning ? "Launcher Motor: True" : "Launcher Motor: False");
        // Prints State of Launcher Motor
        dsLCD.println(Line.kUser5, 1, isIndexerMotorRunning ? "Indexer Motor: True" : "Indexer Motor: False");
        // Print the tank pressurization state.
        dsLCD.println(Line.kUser6, 1, compressor.getPressureSwitchValue() ? "Tanks Full: YES" : "Tanks Full: NO");
        // Updates the output window.
        dsLCD.updateLCD();
    }

    private void handleTimerAndFrisbeeLoadedState() {
        // If the frisbee hits limit switch, registers it as loaded and turns off the loader motor.
        if (indexerLimitSwitch.get() || timer.get() >= indexerTimeoutInSeconds) {
            // Disables the indexer motor if a frisbee is detected or if the timer runs out, also resets the timer and stops it.
            isIndexerMotorRunning = false;
            timer.stop();
            timer.reset();

            if (indexerLimitSwitch.get()) {
                // If the indexer limit switch is being pressed by a frisbee, we enable the launcher motor, and set the frisbee loaded state to true.
                //isLauncherMotorRunning = true;
                isFrisbeeLoaded = true;
                readyToIndexNextFrisbee = false;
            } else {
                // If the indexer limit switch is not being pressed by a frisbee, we disable the launcher motor, and set the frisbee loaded state to false.
                //isLauncherMotorRunning = false;
                isFrisbeeLoaded = false;
                readyToIndexNextFrisbee = true;
            }
        }
    }

    private void handleLauncherMotor() {
        // Check if the motor is being run.
        if (joystick.getRawButton(LAUNCHER_MOTOR_ENABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_ENABLE)) {
            isLauncherMotorRunning = true;
        } else if (joystick.getRawButton(LAUNCHER_MOTOR_DISABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_DISABLE)) {
            isLauncherMotorRunning = false;
        }

        boolean speedUpLauncherMotor = joystick.getRawButton(SPEED_INCREASE);
        boolean slowDownLauncherMotor = joystick.getRawButton(SPEED_DECREASE);

        if (joystick.getRawButton(10)) {
            speed = 0.32;
        } else if (joystick.getRawButton(11)) {
            speed = 0.4;
        }
        // Manually eject the frisbee
        if (joystick.getRawButton(9)) {
            setPistonExtended(false);
            isFrisbeeLoaded = false;
            timer.start();
            //isIndexerMotorRunning = true;
            readyToIndexNextFrisbee = true;
        }

        // Checks to see if either the speed increase or decrease buttons are pressed.
        if (speedUpLauncherMotor ^ slowDownLauncherMotor) {
            // If the buttons have not been pressed previously.
            if (!previouslyChangedSpeeds) {
                // Handle the speed change.
                if (speedUpLauncherMotor) {
                    speed += 0.001;
                }
                if (slowDownLauncherMotor) {
                    speed -= 0.001;
                }
            }

            // Set the button state to indicate the buttons have been pressed.
            previouslyChangedSpeeds = true;
        } else {
            previouslyChangedSpeeds = false;
        }
    }

    private void solenoidHandler() {
        if (joystick.getRawButton(EXTEND_CLIMBING_PISTONS)) {
            setClimbingPistonExtended(true);
        } else if (joystick.getRawButton(RETRACT_CLIMBING_PISTONS)) {
            setClimbingPistonExtended(false);
        }

        // If the trigger is pressed.
        if (joystick.getRawButton(TRIGGER)) {
            if (!isTriggerHeld) {
                // If there is no frisbee in the launcher, turns on the motor to load a new one.
                if (isFrisbeeLoaded) {
                    // If there is a frisbee in the launcher, then it launches it.
                    setPistonExtended(false);
                    isFrisbeeLoaded = false;
                }
                timer.start();
                //isIndexerMotorRunning = true;
                readyToIndexNextFrisbee = true;
            }

            isTriggerHeld = true;
        } else {
            // If the trigger is not pressed, returns the piston to the original state, and attempts to load the next frisbee.
            //setPistonExtended(true);
            isTriggerHeld = false;
        }
    }

    private void setPistonExtended(boolean extended) {
        solenoids[0].set(extended);
        solenoids[1].set(!extended);
    }

    private void setClimbingPistonExtended(boolean extended) {
        solenoids[2].set(extended);
        solenoids[3].set(!extended);

        isLauncherMotorRunning = !extended;
    }

    private void clearLine(Line line) {
        dsLCD.println(line, 1, "                                     ");
    }

    private void clearWindow() {
        clearLine(Line.kUser1);
        clearLine(Line.kUser2);
        clearLine(Line.kUser3);
        clearLine(Line.kUser4);
        clearLine(Line.kUser5);
        clearLine(Line.kUser6);
    }
}
