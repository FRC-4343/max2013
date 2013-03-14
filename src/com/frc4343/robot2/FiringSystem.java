package com.frc4343.robot2;

public final class FiringSystem {
    final RobotBase robot;
    Timer indexingTimer = new Timer();
    Timer loadingDelayTimer = new Timer();
    Timer launchTimer = new Timer();
    Victor launcherMotor = new Victor(3);
    Relay indexerMotor = new Relay(2);
    Piston firingPiston = new Piston((byte) 1, (byte) 2, true);
    DigitalInput indexerLimitSwitch = new DigitalInput(2);

    // The default speed for the launch motor to start at.
    double launcherMotorSpeed = 0.4;

    // Timeouts
    double indexerTimeoutInSeconds = 1.5;
    double loadingDelay = 0.15;
    double accelerationDelay = 0.1;

    // Motor Booleans
    boolean isLauncherMotorRunning = false;
    boolean isIndexerMotorRunning = false;

    // Button mappings
    final byte TRIGGER = 1;
    final byte SPEED_DECREASE = 4;
    final byte SPEED_INCREASE = 5;
    final byte LAUNCHER_MOTOR_ENABLE = 6;
    final byte LAUNCHER_MOTOR_DISABLE = 7;

    // Button Checks
    boolean triggerHeld = false;
    boolean adjustedSpeed = false;

    // Autonomous-only variables
    final byte maxFrisbeesToFireInAutonomous = 3;
    final double autonomousDelayBeforeFirstShot = 4;
    final double autonomousDelayBetweenEachShot = 3;
    final double launcherSpeedAtPyramidBack = 0.4;
    boolean initialAutonomousDelayOver = false;
    byte numberOfFrisbeesFiredInAutonomous = 0;

    // Represents the state of the FiringSystem.
    // IDLE indicates no activity,
    // INDEXING indicates an attempt to feed a frisbee from the hopper to the loader,
    // LOADING indicates that a frisbee is moving from the hopper to the chamber,
    // READY indicates that a frisbee is in the chamber and ready to fire,
    // FIRING indicates that a frisbee is now being expelled from the chamber,
    // RESETTING indicates that a frisbee has just been fired and the chamber is preparing to index another frisbee.
    State firingState = IDLE;
    enum State {
        IDLE,
        INDEXING,
        LOADING,
        READY,
        FIRING,
        RESETTING
    }

    // The constructor which takes all the values required to define and operate the FiringSystem.
    FiringSystem(RobotBase robot) {
        this.robot = robot;
    }

    public void switchMode() {
        // Reset the timers.
        loadingDelayTimer.reset();
        loadingDelayTimer.stop();
        indexingTimer.reset();
        indexingTimer.stop();
        launchTimer.reset();
        launchTimer.stop();

        // Reset the piston to its default position.
        firingPiston.extend();
        // Launcher motor will be enabled and reset to the default speed in case the drivers forget.
        isLauncherMotorRunning = true;
        launcherMotorSpeed = launcherSpeedAtPyramidBack;

        if (robot.isAutonomous()) {
            // Reset the number of fired frisbees in autonomous to zero.
            numberOfFrisbeesFiredInAutonomous = 0;
            // The delay which occurs at the beginning of autonomous must be reset.
            initialAutonomousDelayOver = false;
            // Enable the timer which will control the initial firing delay during autonomous.
            loadingDelayTimer.start();
        }

        state = IDLE;
    }

    public void run() {
        if (robot.isAutonomous()) {
            switch (firingState) {
                case IDLE:
                    // If the autonomous delay has not finished previously and the delay is now passed, set the boolean and reset the timer.
                    if (!initialAutonomousDelayOver) {
                        if (loadingDelayTimer.get() >= autonomousDelayBeforeFirstShot) {
                            loadingDelayTimer.reset();
                            initialAutonomousDelayOver = true;
                        }
                    } else {
                        // If the number of frisbees already fired does not exceed the number of frisbees we want to fire during autonomous, we attempt to load and fire another one.
                        if (numberOfFrisbeesFiredInAutonomous <= maxFrisbeesToFireInAutonomous) {
                            // If we have passed the delay between each shot, we begin indexing.
                            if (loadingDelayTimer.get() >= autonomousDelayBetweenEachShot) {
                                loadingDelayTimer.reset();
                                loadingDelayTimer.stop();
                                state = INDEXING;
                            }
                        }
                    }

                    break;
                case INDEXING:
                    index();
                    break;
                case LOADING:
                    load();
                    break;
                case READY:
                    // Sets the motor speed to 100% for a small amount of time so as to allow for the wheel to spin back up to speed for firing.
                    launcherMotorSpeed = 1;

                    ready();
                    break;
                case FIRING:
                    fire();
                    break;
                case RESETTING:
                    reset();
                    break;
                default:
                    break;
            }
        } else {
            switch (firingState) {
                case IDLE:
                    // If the trigger has been pressed and is not being held, we begin the firing cycle.
                    if (joystick.getRawButton(TRIGGER) && !triggerHeld) {
                        indexingTimer.reset();
                        indexingTimer.start();
                        state = INDEXING;
                    }

                    break;
                case INDEXING:
                    // If a frisbee is entering the loader, or if we have passed the indexer waiting time, we disable the indexer motor, and stop and reset the timer.
                    if (indexerLimitSwitch.get() || indexingTimer.get() >= indexerTimeoutInSeconds) {
                        if (indexingTimer.get() >= indexerTimeoutInSeconds) {
                            state = IDLE;
                        }

                        // Reset the indexingTimer as we no longer have to monitor the time a frisbee has been indexing for until we enter this stage again.
                        indexingTimer.reset();
                        indexingTimer.stop();
                    }

                    index();
                    break;
                case LOADING:
                    load();
                    break;
                case READY:
                    // If the trigger has been pressed and is not being held, we handle frisbee firing.
                    if (joystick.getRawButton(TRIGGER) && !triggerHeld) {
                        // Sets the motor speed to 100% for a small amount of time so as to allow for the wheel to spin back up to speed for firing.
                        launcherMotorSpeed = 1;
                        launchTimer.start();
                    }

                    ready();
                    break;
                case FIRING:
                    fire();
                    break;
                case RESETTING:
                    reset();
                    break;
                default:
                    break;
            }

            input();
        }

        // Store the state of whether or not the buttons have been pressed, to know if they are being held down in the next iteration.
        triggerHeld = joystick.getRawButton(TRIGGER);
        adjustedSpeed = joystick.getRawButton(SPEED_INCREASE) ^ joystick.getRawButton(SPEED_DECREASE);

        // Set the state of the motors based on the values of the booleans controlling them.
        indexerMotor.set(isIndexerMotorRunning ? Relay.Value.kForward : Relay.Value.kOff);
        launcherMotor.set(isLauncherMotorRunning ? launcherMotorSpeed : 0);
    }

    private void index() {
        isIndexerMotorRunning = true;

        // If a frisbee triggers the limit switch while indexing, we begin LOADING.
        if (indexerLimitSwitch.get()) {
            loadingDelayTimer.reset();
            loadingDelayTimer.start();
            state = LOADING;
        }
    }

    private void load() {
        isIndexerMotorRunning = false;

        // Assumes that once the loadingDelayTimer has reached the loadingDelay, there is a frisbee in the chamber.
        if (loadingDelayTimer.get() >= loadingDelay) {
            launchTimer.reset();
            launchTimer.stop();
            state = READY;
        }
    }

    private void ready() {
        if (launchTimer.get() >= accelerationDelay) {
            // Reset the speed of the launcher motor back to the target speed.
            launcherMotorSpeed = launcherSpeedAtPyramidBack;
            launchTimer.reset();
            state = FIRING;
        }
    }

    private void fire() {
        // Retract the piston to expel the frisbee.
        firingPiston.retract();

        if (launchTimer.get() >= accelerationDelay) {
            // Increment the number of frisbees fired.
            numberOfFrisbeesFiredInAutonomous++;
            launchTimer.reset();
            launchTimer.stop();
            state = RESETTING;
        }
    }

    private void reset() {
        // We extend the piston to its initial state, as there is no longer a frisbee in the chamber.
        firingPiston.extend();

        if (launchTimer.get() >= accelerationDelay) {
            // After giving the piston a small amount of time to retract, we are ready to commence the cycle once more.
            state = IDLE;
        }
    }

    private void input() {
        // Handle forced (manual) ejection of a loaded frisbee.
        if (joystick.getRawButton(9)) {
            state = FIRING;
        }

        // Manually control the state of the launcherMotor motor. (Not intended to be used in competition)
        if (joystick.getRawButton(LAUNCHER_MOTOR_ENABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_ENABLE)) {
            isLauncherMotorRunning = true;
        } else if (joystick.getRawButton(LAUNCHER_MOTOR_DISABLE) || joystick2.getRawButton(LAUNCHER_MOTOR_DISABLE)) {
            isLauncherMotorRunning = false;
        }

        // If the buttons are not being held down or pressed together, increase or decrease the speed of the launcherMotor motor.
        if (!adjustedSpeed) {
            if (joystick.getRawButton(SPEED_INCREASE)) {
                launcherMotorSpeed += 0.001;
            } else if (joystick.getRawButton(SPEED_DECREASE)) {
                launcherMotorSpeed -= 0.001;
            }
        }
    }
}
