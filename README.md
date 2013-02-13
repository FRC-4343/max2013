The MaxTech FRC4343 Code for the 2013 Ultimate Ascent Robot
===

The 2013 source code for the FRC Team 4343 Robot

Overview
---
The code functions based on the command-subsystem control method.
This means that there is are subsystem files which represent singlular components of the robot (such as Chassis for the chassis).
Every function which can be performed by the robot is handled a command (such as DriveStraight for driving straight ahead).

OI
---
OI handles any button-based events which are passed into the system.
You can also retrieve Joystick instances from OI to process input from them.

Main
---
Main is the core of the code. All modes of the robot execute any code defined in main before, during, and after they function.
For example, the robotInit() method in Main is called when the robot initially starts up.