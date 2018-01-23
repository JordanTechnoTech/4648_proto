package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClawCommand extends Command {

	public ToggleClawCommand() {
		super();
		requires(Robot.clawSubsystem);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void execute() {
		if(Robot.m_oi.getClawToggle()) {
			Robot.clawSubsystem.openClaw();
		} else {
			Robot.clawSubsystem.closeClaw();
		}
	}



	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
