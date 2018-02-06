/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
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

	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drivetrain;

	// robot lift components
	public static int liftMotor = 2;
	public static Spark liftMotorController;

	// claw components
	public static int solenoidMotor = 0;
	public static Solenoid clawSolenoid;

	// Intake components
	public static int intakeMotor = 3;
	public static Spark intakeWheels;

	// Camera components
	public static String cameraHost = "axis-camera.local";
	public static AxisCamera camera;
	
	//Sonar
	public static AnalogInput leftSonar;

	// IMU(Inertial Measurement Unit) component contains a three axis gyroscope,
	// three axis accelerometer, three axis magnetometer, and a barometer
	public static ADIS16448_IMU imu;

	public static void init() {
		// drive initialization
		leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);

		// lift initialization
		liftMotorController = new Spark(liftMotor);

		// claw initialization
		clawSolenoid = new Solenoid(solenoidMotor);

		// intake initialization
		intakeWheels = new Spark(intakeMotor);

		// imu initialization
		imu = new ADIS16448_IMU();
		
		//Sonar Initialization
		leftSonar = new AnalogInput(0);
	}
}
