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
	
	public static final double WHEELBASE_WIDTH = 6; //INCHES
	public static final double WHEEL_DIAMETER = .62; //INCHES
	public static final int ENCODER_TICKS_PER_REVOLUTION = 1440;
	public static final int MOTION_PROFILE_MAX_VELOCITY = 0;
	
	// drive components
	public static int leftDriveMotor1 = 0;
	public static int leftDriveMotor2 = 1;
	public static int rightDriveMotor1 = 2;
	public static int rightDriveMotor2 = 3;

	public static SpeedController leftDriveMotorController;
	public static SpeedController rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static int leftEncoder1 = 1;
	public static int leftEncoder2 = 2;
	public static int rightEncoder1 = 3;
	public static int rightEncoder2 = 4;
	
	public static Solenoid gearShift;
	public static int leftGearShift = 5;
	public static int rightGearShift = 0;

	// robot lift components
	public static int innerLiftMotor = 5;
	public static int outerLiftMotor = 4;
	public static Spark outerLiftMotorController;
	public static Talon innerLiftMotorController;

	// claw components
	public static int clawActuateMotor = 3;
	public static Solenoid clawActuate;

	// Intake components
	public static int intakeMotor = 6;
	public static Talon intakeWheels;
	
	//climing motor
	public static int climbMotorPort = 7;
	public static Talon climbMotorController;
	public int climingStatus = 0;

	// Camera components
	public static String cameraHost = "axis-camera.local";
	public static AxisCamera camera;
	
	//Sonar
	public static AnalogInput leftSonar;
	public static AnalogInput rightSonar;
	
	//Lift actuators
	public static int leftActuateChannel = 1;
	public static int rightActuateChannel = 4;
	public static Solenoid leftLiftActuate;
	public static Solenoid rightLiftActuate;

	// IMU(Inertial Measurement Unit) component contains a three axis gyroscope,
	// three axis accelerometer, three axis magnetometer, and a barometer
	public static ADIS16448_IMU imu;

	public static void init() {
		// drive initialization
		leftDriveMotorController = new SpeedControllerGroup(new Spark(leftDriveMotor1), new Spark(leftDriveMotor2));
		rightDriveMotorController = new SpeedControllerGroup(new Spark(rightDriveMotor1), new Spark(rightDriveMotor2));
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
		
		leftEncoder = new Encoder(leftEncoder1, leftEncoder2);
		rightEncoder = new Encoder(rightEncoder1, rightEncoder2);
		
		gearShift = new Solenoid(leftGearShift, rightGearShift);

		// lift initialization
		outerLiftMotorController = new Spark(outerLiftMotor);
		innerLiftMotorController = new Talon(innerLiftMotor);

		// claw initialization
		clawActuate = new Solenoid(clawActuateMotor);

		// intake initialization
		intakeWheels = new Talon(intakeMotor);
		
		//climer initialization
		climbMotorController = new Talon(climbMotorPort);

		// imu initialization
		imu = new ADIS16448_IMU();
		
		//Sonar Initialization
		leftSonar = new AnalogInput(0);
		rightSonar = new AnalogInput(1);
		
		//Lift actuator
		rightLiftActuate = new Solenoid(rightActuateChannel);
		leftLiftActuate = new Solenoid(leftActuateChannel);
		
		
	}
}
