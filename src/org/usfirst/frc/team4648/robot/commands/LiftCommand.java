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
		Robot.liftSubsystem.innerLiftControl(Robot.m_oi.getInnerLiftSpeed());
		Robot.liftSubsystem.outerLiftControl(Robot.m_oi.getOuterLiftSpeed());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.liftSubsystem.outerLiftControl(0);
		Robot.liftSubsystem.innerLiftControl(0);
		// TODO Auto-generated method stub
		super.end();
	}

}
