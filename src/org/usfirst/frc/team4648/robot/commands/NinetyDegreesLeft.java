package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NinetyDegreesLeft extends Command {
	public double timeout = 4;
	public double D = 1;
	public double I = .002;
	public double P = .004;
	public double integral, error, rotationSpeed = 0;
	public double setpoint = -90;

	public NinetyDegreesLeft() {

		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);

	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		RobotMap.imu.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		error = setpoint - RobotMap.imu.getAngleZ();
		rotationSpeed = P * error + I * integral;
		integral = integral + error * .02;
		Robot.driveSubsystem.arcadeDrive(0, rotationSpeed);
		// RobotMap.leftDriveMotorController.set(rotationSpeed);
		// RobotMap.rightDriveMotorController.set(rotationSpeed);

	}

	// Called once after timeout
	protected void end() {
		Robot.driveSubsystem.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return (RobotMap.imu.getAngleZ() <= -90);
	}
}
