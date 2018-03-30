package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLiftCommand extends Command {
	
	public static double AUTO_OUTER_LIFT_SPEED = .10;
	public static final int MAX_OUTER_ENCODER_VALUE = 500;
	public static double MAX_OUTER_LIFT_SPEED = .5;

	public AutoLiftCommand() {
		requires(Robot.liftSubsystem);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	public synchronized void start() {
		RobotMap.outerLiftEncoder.reset();
		super.start();
	}

	@Override
	protected void execute() {
		
		AUTO_OUTER_LIFT_SPEED = Math.min(MAX_OUTER_LIFT_SPEED, (MAX_OUTER_ENCODER_VALUE - RobotMap.outerLiftEncoder.get()  ) * .005);
		RobotMap.outerLiftMotorController.set(AUTO_OUTER_LIFT_SPEED);

	}

	@Override
	protected boolean isFinished() {
		//return RobotMap.outerLiftEncoder.get() >= MAX_OUTER_ENCODER_VALUE;
		return false;
	}

	@Override
	protected void end() {
		
		super.end();
		//RobotMap.outerLiftMotorController.set(-.3);
	}

}
