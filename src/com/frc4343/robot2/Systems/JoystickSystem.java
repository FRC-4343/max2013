package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickSystem extends System {

    Joystick[] joysticks = new Joystick[Mappings.JOYSTICK_COUNT];
    boolean[] buttonHeld = new boolean[17];

    public JoystickSystem(RobotTemplate robot) {
        super(robot);

        for (byte i = 0; i < Mappings.JOYSTICK_COUNT; i++) {
            joysticks[i] = new Joystick(i + 1);
        }
    }
    public boolean isButtonPressed(byte joystickNumber, byte y) {
        if (joysticks[joystickNumber - 1].getRawButton(y) && !buttonHeld[y]) {
            buttonHeld[y] = joysticks[joystickNumber - 1].getRawButton(y);
            return true;
        } else {
            buttonHeld[y] = joysticks[joystickNumber - 1].getRawButton(y);
            return false;
        }
    }
    public Joystick getJoystick(int joystickNumber) {
        return joysticks[joystickNumber - 1];
    }
}
