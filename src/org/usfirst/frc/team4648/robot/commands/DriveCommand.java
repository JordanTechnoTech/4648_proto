package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	public static final double SPEED_RATE = 0.7;
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
		Robot.driveSubsystem.arcadeDrive(Robot.m_oi.getArcardeSpeed() * SPEED_RATE, Robot.m_oi.getRotationSpeed() * SPEED_RATE);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.arcadeDrive(0, 0);
		// TODO Auto-generated method stub
		super.end();
	}
}
