package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public final class Piston {
    // Initialize an array of two solenoids to handle the solenoids controlling the piston.
    Timer launcherPistonRetractionTimer = new Timer();
    Timer launcherPistonExtensionTimer = new Timer();
    Solenoid[] solenoids = new Solenoid[2];

    // The constructor which takes all the values required to define and operate a piston.
    public Piston(byte firstSolenoid, byte secondSolenoid, boolean isExtended) {
        solenoids[0] = new Solenoid(firstSolenoid);
        solenoids[1] = new Solenoid(secondSolenoid);

        setExtended(isExtended);
    }

    public boolean extend() {
        setExtended(true);
        launcherPistonExtensionTimer.reset();
        launcherPistonExtensionTimer.start();
        if (launcherPistonExtensionTimer.get() < Mappings.PISTON_EXTENSION_LATENCY) {
            return false;
        } else {
            launcherPistonExtensionTimer.stop();
            launcherPistonExtensionTimer.reset();
            return true;
        }
    }
    
    public boolean retract() {
        setExtended(false);
        launcherPistonRetractionTimer.reset();
        launcherPistonRetractionTimer.start();
        if (launcherPistonRetractionTimer.get() < Mappings.PISTON_RETRACTION_LATENCY) {
            return false;
        } else {
            launcherPistonRetractionTimer.stop();
            launcherPistonRetractionTimer.reset();
            return true;
        }
    }

    private void setExtended(boolean isExtended) {
        solenoids[0].set(isExtended);
        solenoids[1].set(!isExtended);
    }
}
