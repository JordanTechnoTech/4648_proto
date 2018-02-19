package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathfinderCommand extends Command {
	
//	private PathfinderUtils pathfinderUtils;

	private EncoderFollower left;
	private EncoderFollower right;
	private Trajectory trajectory;


	public PathfinderCommand(Trajectory trajectory) {
		this.trajectory = trajectory;
	}
	

	@Override
	public synchronized void start() {
		SmartDashboard.putBoolean("Autonomous Running",true);
		super.start();
	}

	

	@Override
	protected void initialize() {
		TankModifier modifier = new TankModifier(trajectory).modify(0.5);
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
		
		left.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
		right.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
		
		left.configurePIDVA(0.5, 0, 0, 0.3, 0.08); 
		right.configurePIDVA(0.5, 0, 0, 0.3, 0.08);
		// TODO Auto-generated method stub
		super.initialize();
	}


	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
	    	double l = left.calculate(RobotMap.leftEncoder.get());
	    	double r = right.calculate(RobotMap.rightEncoder.get());
	    	
	    	double gyro_heading = RobotMap.imu.getAngleZ();
		double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
	
		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0/80.0) * angleDifference;
	    			
	
	    	Robot.driveSubsystem.tankDrive(l+turn, r-turn);
	    	log();
	}
	
	

	@Override
	protected void end() {
		Robot.driveSubsystem.tankDrive(0.0,0.0);
		// TODO Auto-generated method stub
		super.end();
	}


	@Override
	protected boolean isFinished() {
		SmartDashboard.putBoolean("Autonomous Running", (left.isFinished() && right.isFinished()));
		return left.isFinished() && right.isFinished();
	}
	
	public void log() {
		SmartDashboard.putNumber("Current left segment position", left.getSegment().position);
		SmartDashboard.putNumber("Current left segment heading", left.getSegment().heading);
		SmartDashboard.putNumber("Current left segment acceleration", left.getSegment().acceleration);
		SmartDashboard.putNumber("Current left segment velocity", left.getSegment().velocity);
		SmartDashboard.putNumber("Current left segment x", left.getSegment().x);
		SmartDashboard.putNumber("Current left segment y", left.getSegment().y);
		SmartDashboard.putNumber("Current left segment jerk", left.getSegment().jerk);
		
		SmartDashboard.putNumber("Current right segment position", right.getSegment().position);
		SmartDashboard.putNumber("Current right segment heading", right.getSegment().heading);
		SmartDashboard.putNumber("Current right segment acceleration", right.getSegment().acceleration);
		SmartDashboard.putNumber("Current right segment velocity", right.getSegment().velocity);
		SmartDashboard.putNumber("Current right segment x", right.getSegment().x);
		SmartDashboard.putNumber("Current right segment y", right.getSegment().y);
		SmartDashboard.putNumber("Current right segment jerk", right.getSegment().jerk);
	}

}
