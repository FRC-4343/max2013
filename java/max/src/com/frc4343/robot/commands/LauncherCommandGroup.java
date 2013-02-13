/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frc4343.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 * @author Maxim
 */
public class LauncherCommandGroup extends CommandGroup {
    public LauncherCommandGroup(double speedVal) {
        addSequential(new LauncherDoNothing());
        addSequential(new LauncherDriveWithJoystick(speedVal));
    }
}
