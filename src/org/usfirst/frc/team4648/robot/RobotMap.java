/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static int leftDriveMotor = 0;
	public static int rightDriveMotor = 1;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	public static int rangefinderPort = 1;
	public static int rangefinderModule = 1;

	public static SpeedController leftDriveSpeedController;
	public static SpeedController rightDriveSpeedController;
	public static DifferentialDrive drivetrain;

	public static void init() {
		leftDriveSpeedController = new Talon(leftDriveMotor);
		rightDriveSpeedController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveSpeedController, rightDriveSpeedController);
	}
}