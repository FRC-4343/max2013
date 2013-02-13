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
    JoystickButton SOLENOID_ONE_TOGGLE = new JoystickButton(getJoystick(), Mappings.SOLENOID_ONE_BUTTON);
    JoystickButton SOLENOID_TWO_TOGGLE = new JoystickButton(getJoystick(), Mappings.SOLENOID_TWO_BUTTON);
    JoystickButton TRIGGER_RELEASE = new JoystickButton(getJoystick(), Joystick.ButtonType.kTrigger.value);
    JoystickButton CAMERA_LIGHT = new JoystickButton(getJoystick(), Mappings.CAMERA_LIGHT_BUTTON);
    JoystickButton CAMERA_TARGET = new JoystickButton(getJoystick(), Mappings.CAMERA_TARGET_BUTTON);
    JoystickButton INCREASE_LAUNCHER_SPEED = new JoystickButton(getJoystick(Mappings.JOYSTICK_PORT_B), Mappings.LAUNCHER_SPEED_INCREASE_BUTTON);
    JoystickButton DECREASE_LAUNCHER_SPEED = new JoystickButton(getJoystick(Mappings.JOYSTICK_PORT_B), Mappings.LAUNCHER_SPEED_DECREASE_BUTTON);

    public OI() {
        SOLENOID_ONE_TOGGLE.whenPressed(new ToggleSolenoid(Mappings.SOLENOID_ONE));
        SOLENOID_TWO_TOGGLE.whenPressed(new ToggleSolenoid(Mappings.SOLENOID_TWO));
        TRIGGER_RELEASE.whenPressed(new LauncherFire());
        CAMERA_LIGHT.whenPressed(new ToggleLight());
        CAMERA_TARGET.whileHeld(new TargetWithCamera());
        INCREASE_LAUNCHER_SPEED.whenReleased(new LauncherCommandGroup(0.05));
        DECREASE_LAUNCHER_SPEED.whenReleased(new LauncherCommandGroup(-0.05));
    }

    public Joystick getJoystick() {
        return new Joystick(Mappings.JOYSTICK_PORT_A);
    }

    // Used to get the current joystick.
    public Joystick getJoystick(int port) {
        return new Joystick(port);
    }
}
