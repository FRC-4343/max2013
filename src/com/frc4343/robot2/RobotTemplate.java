package edu.wpi.first.wpilibj.templates;

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
    // Timers
    Timer timer = new Timer();
    Timer climbTimer = new Timer();
    Timer indexerReverseDelayTimer = new Timer();
    Timer autonomousDriveTimer = new Timer();
    // Joystick Objects
    Joystick joystick = new Joystick(1);
    Joystick joystick2 = new Joystick(2);
    // Non-Drive Motors
    Victor launcherMotor = new Victor(3);
    Relay indexerMotor = new Relay(2);
    // Drive Motors
    RobotDrive robotDrive = new RobotDrive(1, 2);
    // Solenoids
    Solenoid[] solenoids = new Solenoid[4];
    // Compressor
    Compressor compressor = new Compressor(1, 1);
    // DigitalInput - Limit Switches
    DigitalInput indexerLimitSwitch = new DigitalInput(2);
    // The default speed for the launch motor to start at.
    double launcherSpeed = 0.4;
    double axisCompensation = 0.8;
    double indexerTimeoutInSeconds = 1.5;
    final double AUTO_CLIMB_AT_END_OF_TELEOP = 119.9;
    // Whether or not the launch speed launcherMotor buttons are being pressed.
    boolean isLauncherMotorRunning = false;
    boolean isIndexerMotorRunning = false;
    boolean isFrisbeeLoaded = false;
    boolean[] buttonHeld = new boolean[17];
    boolean isFrisbeeStuck = false;
    boolean driveBack = false;
    boolean turn180 = false;
    // Playstation Controller Mappings
    final byte TRIANGLE = 13;
    final byte CIRCLE = 14;
    final byte CROSS = 15;
    final byte SQUARE = 16;
    final byte L1 = 11;
    final byte R1 = 12;
    final byte L2 = 9;
    final byte R2 = 10;
    final byte SELECT = 1;
    final byte L3 = 2;
    final byte R3 = 3;
    final byte START = 4;
    final byte DPAD_UP = 5;
    final byte DPAD_DOWN = 7;
    final byte PSBUTTON = 17;
    // Button mappings
    final byte INDEX_AND_FIRE = R1;
    final byte FRISBEE_MANUAL_EJECT = R2;
    final byte LAUNCHER_SPEED_DECREASE = L2;
    final byte LAUNCHER_SPEED_INCREASE = L1;
    final byte LAUNCHER_MOTOR_ENABLE = START;
    final byte LAUNCHER_MOTOR_DISABLE = SELECT;
    final byte EXTEND_CLIMBING_PISTONS = DPAD_UP;
    final byte RETRACT_CLIMBING_PISTONS = DPAD_DOWN;
    final byte FRISBEE_STUCK_EJECT = SQUARE;
    // This section is relevant only to autonomous.
    boolean isInitialAutonomousDelayOver = false;
    boolean readyToIndexNextFrisbee = false;
    byte numberOfFrisbeesFired = 0;
    final byte FRISBEES_TO_FIRE_IN_AUTONOMOUS = 3;
    final double autonomousDelayBetweenEachShot = 3;
    final double autonomousDelayBeforeFirstShot = 1;
    final double delayToPistonRetraction = 0.3; // can play around with this
    final double AUTONOMOUS_REVERSE_DURATION = 1.3;
    final double AUTONOMOUS_ROTATE_DURATION = 0.79;

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
        //double sumOfYAxes = joystick2.getAxis(Joystick.AxisType.kY) + (joystick.getAxis(Joystick.AxisType.kY) * 0.5);
        //double sumOfXAxes = -joystick2.getAxis(Joystick.AxisType.kX) * axisCompensation;
        double sumOfYAxes = joystick.getRawAxis(2) + (joystick2.getRawAxis(2) * 0.5);
        double sumOfXAxes = -joystick.getRawAxis(3) * axisCompensation;
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
        setClimbingPistonExtended(true); // Extend climbing pistons
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
        if (timer.get() >= autonomousDelayBeforeFirstShot && !isInitialAutonomousDelayOver) {
            launcherSpeed = 0.4;
            isInitialAutonomousDelayOver = true;
            timer.reset();
        } else if (isInitialAutonomousDelayOver && numberOfFrisbeesFired < FRISBEES_TO_FIRE_IN_AUTONOMOUS) {
            // Once the delay per shot has been reached, fire the next frisbee.
            if (timer.get() >= autonomousDelayBetweenEachShot && isFrisbeeLoaded) {
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
        if (timer.get() > delayToPistonRetraction + 0.4 && isInitialAutonomousDelayOver && !isFrisbeeLoaded && isAutonomous() && numberOfFrisbeesFired != FRISBEES_TO_FIRE_IN_AUTONOMOUS) {
            isIndexerMotorRunning = true;
        }
        if (indexerLimitSwitch.get()) { // If frisbee hits limitswitch
            isIndexerMotorRunning = false;
            isFrisbeeLoaded = true;
            if (isOperatorControl()) {
                timer.reset();
                timer.stop();
            }
        } else if (timer.get() >= indexerTimeoutInSeconds && isOperatorControl()) {
            isIndexerMotorRunning = false;
            timer.reset();
            timer.stop();
        }
    }

    private void driveInAutonomous() {
        if (numberOfFrisbeesFired == FRISBEES_TO_FIRE_IN_AUTONOMOUS) {
            autonomousDriveTimer.start();
            timer.reset();
            timer.stop();
        }
        if (!driveBack && !turn180 && autonomousDriveTimer.get() > 0.15 && autonomousDriveTimer.get() < 0.45) {
            driveBack = true;
            autonomousDriveTimer.reset();
        }
        if (driveBack && autonomousDriveTimer.get() <= AUTONOMOUS_REVERSE_DURATION) {
            robotDrive.arcadeDrive(1.0, 0.0);
        } else if (driveBack && autonomousDriveTimer.get() > AUTONOMOUS_REVERSE_DURATION) {
            robotDrive.arcadeDrive(0.0, 0.0);
            autonomousDriveTimer.reset();
            driveBack = false;
            turn180 = true;
        } else if (turn180 && autonomousDriveTimer.get() >= 0.1 && autonomousDriveTimer.get() <= AUTONOMOUS_ROTATE_DURATION) {
            robotDrive.tankDrive(-1.0, 1.0);
        } else if (turn180 && autonomousDriveTimer.get() > AUTONOMOUS_ROTATE_DURATION + 0.5) {
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
        if (isButtonPressed(joystick, INDEX_AND_FIRE)) {
            timer.start();
            if (isFrisbeeLoaded) {
                // If there is a frisbee in the launcher, then it launches it.
                setPistonExtended(false);
                launcherSpeed = 0.6;
            } else {
                isIndexerMotorRunning = true;
            }
        } else if (joystick.getRawButton(FRISBEE_STUCK_EJECT)) {
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
        if (joystick.getRawButton(LAUNCHER_MOTOR_ENABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_ENABLE)) {
            isLauncherMotorRunning = true;
        } else if (joystick.getRawButton(LAUNCHER_MOTOR_DISABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_DISABLE)) {
            isLauncherMotorRunning = false;
        } else if (isButtonPressed(joystick, FRISBEE_MANUAL_EJECT)) { // Manually eject the frisbee
            setPistonExtended(false);
            timer.start();
        } else if (isButtonPressed(joystick, LAUNCHER_SPEED_INCREASE)) { // Handle the speed change.
            launcherSpeed += 0.01;
        } else if (isButtonPressed(joystick, LAUNCHER_SPEED_DECREASE)) {
            launcherSpeed -= 0.01;
        }
    }

    private void handleSolenoids() {
        // If the piston retraction delay has passed, begin to retract the piston.
        if (timer.get() > delayToPistonRetraction && timer.get() < (delayToPistonRetraction + 0.2)) {
            setPistonExtended(true);
            if (isOperatorControl()) {
                launcherSpeed = 0.4;
            }
            isFrisbeeLoaded = false;
        }
        if (climbTimer.get() < 0) {
            climbTimer.stop();
        }
        if (joystick.getRawButton(L3)) {
            setPistonExtended(false);
        } else if (joystick.getRawButton(R3)) {
            setPistonExtended(true);
        } else if (joystick.getRawButton(EXTEND_CLIMBING_PISTONS)) {
            setClimbingPistonExtended(true);
        } else if (joystick.getRawButton(RETRACT_CLIMBING_PISTONS) || (climbTimer.get() >= AUTO_CLIMB_AT_END_OF_TELEOP && climbTimer.get() >= 0)) {
            setClimbingPistonExtended(false);
        }
    }

    private void setPistonExtended(boolean extended) {
        solenoids[0].set(extended);
        solenoids[1].set(!extended);
    }

    private void setClimbingPistonExtended(boolean extended) {
        solenoids[2].set(extended);
        solenoids[3].set(!extended);
    }

    private boolean isButtonPressed(Joystick x, byte y) {
        if (x.getRawButton(y) && !buttonHeld[y]) {
            buttonHeld[y] = x.getRawButton(y);
            return true;
        } else {
            buttonHeld[y] = x.getRawButton(y);
            return false;
        }
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

    private void updateLCD() {
        // Clears driverStation text.
        clearWindow();
        // Prints State of Frisbee
        dsLCD.println(Line.kUser1, 1, "Ready To Fire: " + (isFrisbeeLoaded ? "YES" : "NO"));
        // Print the speed, and launcher status.
        dsLCD.println(Line.kUser2, 1, "Launcher: " + (isLauncherMotorRunning ? "ON, " : "OFF, ") + (launcherSpeed * 100));
        // Prints State of Launcher Motor
        dsLCD.println(Line.kUser3, 1, "Indexer: " + (isIndexerMotorRunning ? "ON" : "OFF"));
        // Print the tank pressurization state.
        dsLCD.println(Line.kUser4, 1, "Tanks Full? " + (compressor.getPressureSwitchValue() ? "YES" : "NO"));
        // Auto hang timeout
        dsLCD.println(Line.kUser5, 1, "AutoHang: " + (climbTimer.get() >= 0 ? 120 - climbTimer.get() : 0));
        // Updates the output window.
        dsLCD.updateLCD();
    }
}
