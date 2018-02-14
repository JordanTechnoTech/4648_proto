package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftStateCommand extends Command {
	int m_liftState = 0;

	public SetLiftStateCommand(int liftState) {
		m_liftState = liftState;
		requires(Robot.liftSubsystem);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}
	
	@Override
	protected void execute() {
		Robot.liftSubsystem.setLiftState(m_liftState);
		// I think above is a better implementation - but need to test to verify
//		if(Robot.m_oi.getSingleLiftToggle()) {
//			Robot.liftSubsystem.setLiftState(Robot.liftSubsystem.singleLiftState);
//		} else if(Robot.m_oi.getDoubleLiftToggle()) {
//			Robot.liftSubsystem.setLiftState(Robot.liftSubsystem.doubleLiftState);
//		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		Robot.liftSubsystem.liftSystemControl(0);
		// TODO Auto-generated method stub
		super.end();
	}
	
}
