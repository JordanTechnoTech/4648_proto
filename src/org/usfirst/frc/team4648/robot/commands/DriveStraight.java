package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private int encoderTicks;
	private double kSetSpeed = .5;
	private double intialEncoderValue = 0.0;
	private double initialHeading = 0.0;

	public DriveStraight(int encoderTicks) {
		super();
		this.encoderTicks = encoderTicks;

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		intialEncoderValue = RobotMap.leftEncoder.get();
		initialHeading = RobotMap.imu.getAngleZ();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double Kp = 1d / 45d;// 0.025;
		double leftPower;
		double rightPower;
		double angleOff = RobotMap.imu.getAngleZ() - initialHeading;
		double rotationPower = -angleOff * Kp;
		leftPower = kSetSpeed - rotationPower;
		rightPower = kSetSpeed + rotationPower;

		//RobotMap.leftDriveMotorController.set(leftPower);
		//RobotMap.rightDriveMotorController.set(-rightPower);

		RobotMap.leftDriveMotorController.set(kSetSpeed);
		RobotMap.rightDriveMotorController.set(-kSetSpeed*0.9);

	}

	// Called once after timeout
	protected void end() {
		RobotMap.leftDriveMotorController.set(0);
		RobotMap.rightDriveMotorController.set(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return RobotMap.leftEncoder.get() > (intialEncoderValue + encoderTicks);
	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		intialEncoderValue = RobotMap.leftEncoder.get();
	}
}
