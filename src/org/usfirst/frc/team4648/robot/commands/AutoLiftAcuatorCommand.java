package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLiftAcuatorCommand extends Command {

	public AutoLiftAcuatorCommand() {
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
		return true;
	}

	@Override
	protected void end() {
		super.end();
	}

}
