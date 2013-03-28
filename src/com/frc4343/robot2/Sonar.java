package com.frc4343.robot2;

import edu.wpi.first.wpilibj.AnalogChannel;

public class Sonar {

    AnalogChannel rangeSensor;

    public Sonar(int moduleNumber, int channel) {
        rangeSensor = new AnalogChannel(moduleNumber, channel);
    }

    public float getDistanceInInches() {
        float volt;
        volt = rangeSensor.getVoltage();

        float inches;
        inches = volt * 100;

        return inches;
    }
}
