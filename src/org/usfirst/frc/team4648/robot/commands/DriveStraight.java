package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {

	private int encoderTicks;
	private double kSetSpeed = .6;
	private double intialEncoderValue = 0.0;
	private double initialZValue = 0.0;

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
		initialZValue = RobotMap.imu.getAngleZ();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double difference = initialZValue - RobotMap.imu.getAngleZ();
		//examples
		// -  initalialZValue = 0 RioCurrentAngleZ = -4 ; in this case drifting to the left need less power to right 
		//    adjustment = -.1; rightDriveMotorController would be .set(-kSetSpeed * .75)
		// -  initalialZValue = 0 RioCurrentAngleZ = 4 ; in this case drifting to the right need more power to right 
		//    adjustment = .1; rightDriveMotorController would be .set(-kSetSpeed * .95)

		double adjustment = 0.0;
		if(difference < -1.0) {//drifting left 
			adjustment = -0.1;
		} else if(difference > 1.0){//drifting right
			adjustment = 0.1;
		}
		SmartDashboard.putNumber("RIGHT ADJUSTMENT",adjustment);
		RobotMap.leftDriveMotorController.set(kSetSpeed);
		RobotMap.rightDriveMotorController.set(-kSetSpeed * (0.85 + adjustment));
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
