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
private double error, leftSpeed, rightSpeed;
private double kSetSpeed = .6; 
private double intialEncoderValue = 0.0;


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
    	
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.error = RobotMap.imu.getAccelZ() / 20;
    	leftSpeed = (kSetSpeed - error);
    	rightSpeed =  -(kSetSpeed + error);
    	
    	RobotMap.leftDriveMotorController.set(kSetSpeed);
    	RobotMap.rightDriveMotorController.set(-kSetSpeed);
    	SmartDashboard.putString("AUTO_COMMAND", "Moving forward to "+encoderTicks+ " From:"+intialEncoderValue);
    	
//    RobotMap.leftDriveMotorController.set(rotationSpeed);
//    RobotMap.rightDriveMotorController.set(rotationSpeed);
    	
   
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return RobotMap.leftEncoder.get() > (intialEncoderValue+encoderTicks);
	}

	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		intialEncoderValue = RobotMap.leftEncoder.get();
    	//RobotMap.imu.reset();

	}
}
