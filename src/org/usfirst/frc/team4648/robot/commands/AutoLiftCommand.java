package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLiftCommand extends Command {

	public AutoLiftCommand() {
    		requires(Robot.liftActuatorSubsystem);
    }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		Robot.liftActuatorSubsystem.stateTrue();
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
