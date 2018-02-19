package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLiftCommand extends Command {
	
	public static final double AUTO_OUTER_LIFT_SPEED = .10;
	public static final int MAX_OUTER_ENCODER_VALUE = 10;

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
		RobotMap.outerLiftMotorController.set(AUTO_OUTER_LIFT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.outerLiftEncoder.get() >= MAX_OUTER_ENCODER_VALUE;
	}

	@Override
	protected void end() {
		RobotMap.outerLiftMotorController.set(0.0);
		super.end();
	}

}
