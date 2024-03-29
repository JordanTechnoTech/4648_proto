package org.usfirst.frc.team4648.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
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

	// drive components
	public static String gameData = "RRR";
	public static String switchGoal = "RRR";
	public static String scaleGoal = "RRR";

	public static int rightDriveMotor = 0;
	public static int leftDriveMotor = 3;

	public static Spark leftDriveMotorController;
	public static Spark rightDriveMotorController;
	public static DifferentialDrive drivetrain;

	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static int leftEncoder1 = 0;
	public static int leftEncoder2 = 1;
	public static int rightEncoder1 = 2;
	public static int rightEncoder2 = 3;

	public static Solenoid gearShift;
	public static int gearShiftPort = 0;

	// robot lift components
	public static int innerLiftMotor = 2;
	public static int outerLiftMotor = 4;
	public static Spark outerLiftMotorController;
	public static Spark innerLiftMotorController;
	public static int outerEncoder1 = 4;
	public static int outerEncoder2 = 5;
	// TODO: Update with actual installed encoders
	public static Encoder outerLiftEncoder;

	// claw components
	public static int clawActuateMotor = 3;
	public static Solenoid clawActuate;

	// Intake components
	public static int intakeMotorRight = 1;
	public static Talon intakeWheelRight;

	public static int intakeMotorLeft = 6;
	public static Talon intakeWheelLeft;

	// climbing motor
	public static int climbMotorPort = 5;
	public static Spark climbMotorController;
	public int climingStatus = 0;

	// Camera components
	public static String cameraHost = "axis-camera.local";
	public static AxisCamera camera;

	// Sonar
	public static AnalogInput leftSonar;
	public static AnalogInput rightSonar;

	// Lift actuators
	public static int leftActuateChannel = 1;
	public static int rightActuateChannel = 4;
	public static Solenoid leftLiftActuate;
	public static Solenoid rightLiftActuate;

	// IMU(Inertial Measurement Unit) component contains a three axis gyroscope,
	// three axis accelerometer, three axis magnetometer, and a barometer
	public static ADIS16448_IMU imu;

	public static void init() {
		// drive initialization
		leftDriveMotorController = new Spark(leftDriveMotor);
		rightDriveMotorController = new Spark(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);

		leftEncoder = new Encoder(leftEncoder1, leftEncoder2);
		rightEncoder = new Encoder(rightEncoder1, rightEncoder2);

		gearShift = new Solenoid(gearShiftPort);

		// lift initialization
		outerLiftMotorController = new Spark(outerLiftMotor);
		innerLiftMotorController = new Spark(innerLiftMotor);

		outerLiftEncoder = new Encoder(outerEncoder1, outerEncoder2);

		// claw initialization
		clawActuate = new Solenoid(clawActuateMotor);

		// intake initialization
		intakeWheelRight = new Talon(intakeMotorRight);
		intakeWheelLeft = new Talon(intakeMotorLeft);

		// climber initialization
		climbMotorController = new Spark(climbMotorPort);

		// imu initialization
		imu = new ADIS16448_IMU();

		// Sonar Initialization

		// Lift actuator
		rightLiftActuate = new Solenoid(rightActuateChannel);
		leftLiftActuate = new Solenoid(leftActuateChannel);

	}
}
