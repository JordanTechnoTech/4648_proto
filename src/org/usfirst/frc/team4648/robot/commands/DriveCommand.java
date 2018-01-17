package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	public DriveCommand() {
    		requires(Robot.driveSubsystem);// drivetrain is an instance of our Drivetrain subsystem
    }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}


	@Override
	protected void execute() {
		Robot.driveSubsystem.arcadeDrive(Robot.m_oi.getSpeedLeft(), Robot.m_oi.getSpeedRight());
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
