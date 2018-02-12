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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final double WHEELBASE_WIDTH = 10.3;
	public static final double WHEEL_DIAMETER = 0.6;
	public static final int ENCODER_TICKS_PER_REVOLUTION = 0;
	public static final int MOTION_PROFILE_MAX_VELOCITY = 0;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static int leftDriveMotor = 0;
	public static int rightDriveMotor = 1;

	public static SpeedController leftDriveMotorController;
	public static SpeedController rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	
	public static Encoder leftEncoder = new Encoder(1, 2);
	public static Encoder rightEncoder = new Encoder(3, 4);

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
	public static AnalogInput rightSonar;

	// IMU(Inertial Measurement Unit) component contains a three axis gyroscope,
	// three axis accelerometer, three axis magnetometer, and a barometer
	public static ADIS16448_IMU imu;

	public static void init() {
		// drive initialization
		leftDriveMotorController = new SpeedControllerGroup(new Spark(0), new Spark(1));
		rightDriveMotorController = new SpeedControllerGroup(new Spark(2), new Spark(3));
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
		rightSonar = new AnalogInput(1);
	}
}
