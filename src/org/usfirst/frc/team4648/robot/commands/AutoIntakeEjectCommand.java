package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoIntakeEjectCommand extends TimedCommand {
	public static final double AUTO_INTAKE_OUT_SPEED= .33;

    public AutoIntakeEjectCommand(double timeout) {
        super(timeout);
        requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.intakeSubsystem.manualReject(AUTO_INTAKE_OUT_SPEED);
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
