package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathfinderCommand extends Command {
	
//	private PathfinderUtils pathfinderUtils;

	private EncoderFollower left;
	private EncoderFollower right;


	public PathfinderCommand(Trajectory trajectory) {
		
		TankModifier modifier = new TankModifier(trajectory).modify(0.5);
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
		
		left.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
		right.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
		
		left.configurePIDVA(0.1, 0.0, 0.0, 1 / 8.0, 0);
		right.configurePIDVA(0.1, 0.0, 0.0, 1 / 8.0, 0);
	}
	

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
		
	    	double l = left.calculate(RobotMap.leftEncoder.get());
	    	double r = right.calculate(RobotMap.rightEncoder.get());
	    	
	    	Robot.driveSubsystem.tankDrive(l, r);
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return left.isFinished() && right.isFinished();
	}

}
