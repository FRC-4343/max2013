package com.frc4343.robot.subsystems;

import com.frc4343.robot.Mappings;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MagneticEncoder extends Subsystem {
    Encoder magneticEncoder;

    public MagneticEncoder() {
        super("Encoder");

        System.out.println("Initializing magnetic encoder.");

        magneticEncoder = new Encoder(Mappings.ENCODER_PORT_A, Mappings.ENCODER_PORT_B);
    }

    protected void initDefaultCommand() {
    }

    public Encoder getEncoder() {
        return magneticEncoder;
    }
}
