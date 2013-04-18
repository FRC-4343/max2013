package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

public class JoystickSystem extends System {

    Joystick[] joysticks = new Joystick[Mappings.JOYSTICK_COUNT];
    boolean[] buttonHeld = new boolean[17];

    public JoystickSystem(RobotTemplate robot) {
        super(robot);

        for (byte i = 0; i < Mappings.JOYSTICK_COUNT; i++) {
            joysticks[i] = new Joystick(i + 1);
        }
    }

    private Joystick getJoystick(int joystickNumber) {
        return joysticks[joystickNumber - 1];
    }

    public boolean getButton(int joystickNumber, int buttonNumber) {
        return getJoystick(joystickNumber).getRawButton(buttonNumber);
    }

    public double getAxis(int joystickNumber, AxisType axis) {
        return getJoystick(joystickNumber).getRawAxis(axis);
    }

    public boolean isButtonPressed(int joystickNumber, int buttonNumber) {
        if (getButton(joystickNumber, buttonNumber) && !buttonHeld[buttonNumber]) {
            buttonHeld[buttonNumber] = getButton(joystickNumber, buttonNumber);
            return true;
        } else {
            buttonHeld[buttonNumber] = getButton(joystickNumber, buttonNumber);
            return false;
        }
    }
}
