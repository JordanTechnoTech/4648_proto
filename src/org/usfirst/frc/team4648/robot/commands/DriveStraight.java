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
	private long startTime;

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
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int seconds =  Math.max((int)(System.currentTimeMillis() - startTime)/1000,1);
		double percentOfFull = 0;
		if(seconds <= 1) {
			percentOfFull =.10;
		} else if(seconds <=2 ) {
			percentOfFull =.20;
		} else if(seconds <=3 ) {
			percentOfFull =.30;
		} else if(seconds <=4 ) {
			percentOfFull =.40;
		}else if(seconds <=5 ) {
			percentOfFull =.50;
		}else if(seconds <=7 ) {
			percentOfFull =.60;
		}else if(seconds <=8 ) {
			percentOfFull =.70;
		}else if(seconds <=9 ) {
			percentOfFull =.80;
		} else {
			percentOfFull = 1;
		}
		double difference = initialZValue - RobotMap.imu.getAngleZ();
		//examples
		// -  initalialZValue = 0 RioCurrentAngleZ = -4 ; in this case drifting to the left need less power to right 
		//    adjustment = -.1; rightDriveMotorController would be .set(-kSetSpeed * .75)
		// -  initalialZValue = 0 RioCurrentAngleZ = 4 ; in this case drifting to the right need more power to right 
		//    adjustment = .1; rightDriveMotorController would be .set(-kSetSpeed * .95)

		double adjustment = 1.0;
		if(difference < -.5) {//drifting left 
			adjustment = 1.20;
		} else if(difference > .5){//drifting right
			adjustment = .80;
		}
		SmartDashboard.putNumber("SECONDS",seconds);
		SmartDashboard.putNumber("RIGHT ADJUSTMENT",adjustment);
		RobotMap.leftDriveMotorController.set(kSetSpeed * percentOfFull);
		RobotMap.rightDriveMotorController.set(-(kSetSpeed* percentOfFull) * (adjustment));
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
//		return RobotMap.leftEncoder.get() > (intialEncoderValue + encoderTicks);
		return System.currentTimeMillis() > (startTime + 15000);
	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		intialEncoderValue = RobotMap.leftEncoder.get();
	}
}
