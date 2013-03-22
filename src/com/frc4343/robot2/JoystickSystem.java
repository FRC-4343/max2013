package com.frc4343.robot2;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickSystem extends System {

    final static byte JOYSTICK_COUNT = 2;
    Joystick[] joysticks = new Joystick[JOYSTICK_COUNT];

    public JoystickSystem(RobotTemplate robot) {
        super(robot);

        for (int i = 0; i < JOYSTICK_COUNT; i++) {
            joysticks[i] = new Joystick(i);
        }
    }

    public Joystick getJoystick(int joystickNumber) {
        if (joysticks.length < joystickNumber && joysticks[joystickNumber] != null) {
            return joysticks[joystickNumber - 1];
        } else {
            return null;
        }
    }
}
