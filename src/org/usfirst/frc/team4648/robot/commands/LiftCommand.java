package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {

	public LiftCommand() {
    		requires(Robot.liftSubsystem);// drivetrain is an instance of our Drivetrain subsystem
    }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		Robot.liftSubsystem.liftControl(Robot.m_oi.getLiftSpeed());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.liftSubsystem.liftControl(0);
		// TODO Auto-generated method stub
		super.end();
	}

}
