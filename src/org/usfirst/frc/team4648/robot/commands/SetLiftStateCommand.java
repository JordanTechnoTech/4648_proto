package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftStateCommand extends Command {
	int m_liftState = 0;
	boolean switched = false;

	public SetLiftStateCommand(int liftState) {
		m_liftState = liftState;
		requires(Robot.liftSubsystem);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	}
	
	@Override
	protected void execute() {
		Robot.liftSubsystem.setLiftState(m_liftState);
		switched = true;
	}
	
	@Override
	protected boolean isFinished() {
		return switched;
	}
	
	@Override
	protected void end() {
		switched = false;
	//	Robot.liftSubsystem.liftSystemControl(0);
		// TODO Auto-generated method stub
		super.end();
	}
	
}
