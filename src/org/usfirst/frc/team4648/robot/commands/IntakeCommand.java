package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {

	public IntakeCommand() {
		requires(Robot.intakeSubsystem);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		//passive intake was only needed for prototype robot, not used on final robot
		//Robot.intakeSubsystem.passiveIntake(Robot.m_oi.getPassiveIntakeToggle());
		if (Robot.m_oi.getIntakeSpeed() > 0) {
			Robot.intakeSubsystem.manualIntake(Robot.m_oi.getIntakeSpeed());
		} else if (Robot.m_oi.getRejectSpeed() > 0) {
			Robot.intakeSubsystem.manualReject(Robot.m_oi.getRejectSpeed());
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		//if (Robot.intakeSubsystem.PASSIVE_INTAKE_STATUS == 0) {
			RobotMap.intakeWheelRight.set(0);
			RobotMap.intakeWheelLeft.set(0);
		//} else if (Robot.intakeSubsystem.PASSIVE_INTAKE_STATUS == 1) {
		//	RobotMap.intakeWheels.set(Robot.intakeSubsystem.PASSIVE_INTAKE_SPEED);
		//}
		super.end();
	}
}
