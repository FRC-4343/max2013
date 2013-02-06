package com.frc4343.robot;

import com.frc4343.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    // Sets stick to a new joystick at JOYSTICK_PORT
    Joystick stick = new Joystick(Mappings.JOYSTICK_PORT); // Instance of the current joystick.
    JoystickButton SOLENOID_ONE_TOGGLE = new JoystickButton(stick, Mappings.SOLENOID_ONE_BUTTON);
    JoystickButton SOLENOID_TWO_TOGGLE = new JoystickButton(stick, Mappings.SOLENOID_TWO_BUTTON);
    JoystickButton TRIGGER_RELEASE = new JoystickButton(stick, Joystick.ButtonType.kTrigger.value);
    JoystickButton CAMERA_LIGHT = new JoystickButton(stick, Mappings.CAMERA_LIGHT_BUTTON);
    JoystickButton CAMERA_TARGET = new JoystickButton(stick, Mappings.CAMERA_TARGET_BUTTON);

    public OI() {
        SOLENOID_ONE_TOGGLE.whenPressed(new ToggleSolenoid(Mappings.SOLENOID_ONE));
        SOLENOID_TWO_TOGGLE.whenPressed(new ToggleSolenoid(Mappings.SOLENOID_TWO));
        TRIGGER_RELEASE.whenPressed(new LauncherFire());
        CAMERA_LIGHT.whenPressed(new ToggleLight());
        CAMERA_TARGET.whileHeld(new TargetWithCamera());
    }

    // Used to get the current joystick.
    public Joystick getJoystick() {
        return stick;
    }
}
