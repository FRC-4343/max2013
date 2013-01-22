package com.frc4343.robot.subsystems;

import com.frc4343.robot.Constants;
import com.frc4343.robot.RobotMap;
import com.frc4343.robot.CommandBase;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MSKinect extends Subsystem {
    KinectStick leftArm;
    KinectStick rightArm;

    public MSKinect() {
        super("MSKinect");

        System.out.print("Initializing kinect.");

        leftArm = new KinectStick(RobotMap.LEFT_KINECT_ARM);
        rightArm = new KinectStick(RobotMap.RIGHT_KINECT_ARM);
    }

    public void initDefaultCommand() {
    }

    void moveWithKinect() {
        CommandBase.chassis.tankDrive(leftArm.getY() * 3, rightArm.getY() * 3);
    }
}
