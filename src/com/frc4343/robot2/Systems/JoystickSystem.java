package com.frc4343.robot2.Systems;

import com.frc4343.robot2.Mappings;
import com.frc4343.robot2.RobotTemplate;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickSystem extends System {

    Joystick[] joysticks = new Joystick[Mappings.JOYSTICK_COUNT];

    public JoystickSystem(RobotTemplate robot) {
        super(robot);

        for (int i = 0; i < Mappings.JOYSTICK_COUNT; i++) {
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
