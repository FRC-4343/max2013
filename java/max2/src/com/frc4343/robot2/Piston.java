package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston {
    // Initialize an array of two solenoids to handle the solenoids controlling the piston.
    Solenoid[] solenoids = new Solenoid[2];

    // The constructor which takes all the values required to define and operate a piston.
    Piston(int firstSolenoid, int secondSolenoid, boolean isExtended) {

    }

    public void extend() {
        setExtended(true);
    }

    public void retract() {
        setExtended(false);
    }

    private void setExtended(boolean isExtended) {

    }
}
