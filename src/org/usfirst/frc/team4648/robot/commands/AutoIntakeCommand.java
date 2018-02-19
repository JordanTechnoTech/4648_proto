package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoIntakeCommand extends TimedCommand {
	private boolean intake;
	private double speed;

	public AutoIntakeCommand(double a_timeout, double a_speed, boolean a_intake) {
		super(a_timeout);
		intake = a_intake;
		speed = a_speed;
		requires(Robot.intakeSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (intake == false) {
			Robot.intakeSubsystem.manualReject(speed);
		} else {
			Robot.intakeSubsystem.manualIntake(speed);
		}
	}

	// Called once after timeout
	protected void end() {
		Robot.intakeSubsystem.manualIntake(.25);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
