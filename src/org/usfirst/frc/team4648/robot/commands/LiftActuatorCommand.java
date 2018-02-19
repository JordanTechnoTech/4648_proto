package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftActuatorCommand extends Command {

	public LiftActuatorCommand() {
    		requires(Robot.liftActuatorSubsystem);
    }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		if (Robot.m_oi.climbStatus() == 270) {
		Robot.liftActuatorSubsystem.stateTrue(); 
		}
		else if (Robot.m_oi.climbStatus() == 90) {
			Robot.liftActuatorSubsystem.stateFalse();
		}
	}
		
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		super.end();
	}

}
