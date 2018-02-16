package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleActuatorCommand extends Command {

	public ToggleActuatorCommand() {
		super();
		requires(Robot.liftActuatorSubsystem);
	}
	
	@Override
	protected void execute() {
		if(Robot.m_oi.getActuatorToggle()) {
			Robot.liftActuatorSubsystem.stateTrue();
//		} else {
//			Robot.liftActuatorSubsystem.stateFalse();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
