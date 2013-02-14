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
    // Joysticks
    Joystick joystick = new Joystick(1);
    // Victor Motors
    Victor launcherMotor = new Victor(3);
    Relay indexerMotor = new Relay(2);
    // Drive motors
    RobotDrive robotDrive = new RobotDrive(1, 2);
    // Solenoid launcherMotor array
    Solenoid[] solenoids = new Solenoid[2];
    // Compressors
    Compressor compressor = new Compressor(1, 1);
    // Limit Switches
    DigitalInput frisbeeIndexerLimitSwitch = new DigitalInput(2);
    // The default speed for the launch motor to start at.
    double speed = 0.60;
    // Timer in miliseconds
    int indexerTimeoutInMilliseconds = 1000;
    // Whether or not the launch speed launcherMotor buttons are being pressed.
    boolean previouslyChangedSpeeds = false;
    boolean isMotorRunning = false;
    boolean isFrisbeeLoaded = false;
    boolean isTriggerHeld = false;
    boolean isButtonHeld = false;
    // Button mappings
    final int SPEED_DECREASE_BUTTON = 2;
    final int SPEED_INCREASE_BUTTON = 3;
    final int LAUNCHER_MOTOR_ENABLE_BUTTON = 6;
    final int LAUNCHER_MOTOR_DISABLE_BUTTON = 7;

    public void robotInit() {
        // Initialize the solenoids.
        solenoids[0] = new Solenoid(1);
        solenoids[1] = new Solenoid(2);
        // Initialize the compressor.
        compressor.start();
        // Set the direction of the indexer motor.
        indexerMotor.setDirection(Relay.Direction.kBoth);
    }

    public void teleopPeriodic() {
        // Clears the output window of text.
        clearWindow();

        robotDrive.arcadeDrive(joystick.getAxis(Joystick.AxisType.kY), -joystick.getAxis(Joystick.AxisType.kX));

        // If the frisbee has passed the limit switch, registers it as loaded and turns off the loader motor.
        if (frisbeeIndexerLimitSwitch.get()) {
            indexerMotor.set(Relay.Value.kOff);
            isFrisbeeLoaded = true;
        }
        if (joystick.getRawButton(1) && isMotorRunning) {
            timer.start();
            if (timer.get() >= indexerTimeoutInMilliseconds) {
                isFrisbeeLoaded = false;
                indexerMotor.set(Relay.Value.kOff);
                timer.stop();
                timer.reset();
            }
        }
        dsLCD.println(Line.kUser1, 1, isFrisbeeLoaded ? "Frisbee Loaded: True" : "Frisbee Loaded: False");

        handleLauncherMotor();
        handleSolenoid();

        // Print the speed.
        dsLCD.println(Line.kUser2, 1, "Launcher Speed: " + (byte) (speed * 100) + "%");

        // Updates the output window.
        dsLCD.updateLCD();
    }

    private void handleLauncherMotor() {
        // Enable or disable the firing motor.
        if (joystick.getRawButton(LAUNCHER_MOTOR_ENABLE_BUTTON)) {
            isMotorRunning = true;
            launcherMotor.set(speed);
        } else if (joystick.getRawButton(LAUNCHER_MOTOR_DISABLE_BUTTON)) {
            isMotorRunning = false;
            launcherMotor.set(0.0);
        }

        // Checks to see if either the speed increase or decrease buttons are pressed.
        if (joystick.getRawButton(SPEED_DECREASE_BUTTON) || joystick.getRawButton(SPEED_INCREASE_BUTTON)) {
            // If the buttons have not been pressed previously.
            if (!previouslyChangedSpeeds) {
                // Handle the speed change.
                if (joystick.getRawButton(SPEED_INCREASE_BUTTON)) {
                    speed += 0.01;
                }
                if (joystick.getRawButton(SPEED_DECREASE_BUTTON)) {
                    speed -= 0.01;
                }

                if (isMotorRunning) {
                    launcherMotor.set(speed);
                }
            }

            // Set the button state to indicate the buttons have been pressed.
            previouslyChangedSpeeds = true;
        } else {
            previouslyChangedSpeeds = false;
        }
    }

    private void handleSolenoid() {
        // If the trigger is pressed.
        if (joystick.getRawButton(1) && !isTriggerHeld) {
            // If there is no frisbee in the launcher, turns on the motor to load a new one.
            if (!isFrisbeeLoaded) {
                indexerMotor.set(Relay.Value.kForward);
            } else {
                // If there is a frisbee in the launcher, then it launches it.
                fire();
            }

            isTriggerHeld = true;
        } else {
            // If the trigger is not pressed, returns the piston to the original state, and sets the motor to it's original speed.
            setPistonExtended(false);
            if (isMotorRunning) {
                launcherMotor.set(speed);
            }

            isTriggerHeld = false;
        }
    }

    private boolean isButtonPressed(byte buttonVal) {
        boolean isButtonPressed = joystick.getRawButton(buttonVal);

        if (isButtonPressed) {
            if (!isButtonHeld) {
                isButtonHeld = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void clearLine(Line line) {
        DriverStationLCD.getInstance().println(line, 1, "                                     ");
    }

    private void clearWindow() {
        clearLine(Line.kUser1);
        clearLine(Line.kUser2);
        clearLine(Line.kUser3);
        clearLine(Line.kUser4);
        clearLine(Line.kUser5);
        clearLine(Line.kUser6);
    }

    private void setPistonExtended(boolean extended) {
        solenoids[0].set(!extended);
        solenoids[1].set(extended);
    }

    private void fire() {
        isFrisbeeLoaded = false;
        setPistonExtended(true);
        if (isMotorRunning) {
            launcherMotor.set(1.0);
            isFrisbeeLoaded = false;
        }
        indexerMotor.set(Relay.Value.kOn);
    }
}
