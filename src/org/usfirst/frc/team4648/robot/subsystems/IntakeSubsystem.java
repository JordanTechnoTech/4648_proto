package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	Spark intakeWheels = RobotMap.intakeWheels;
	public final double PASSIVE_INTAKE_SPEED = 0.25;
	public double PASSIVE_INTAKE_STATUS = 0;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	
	public void passiveIntake(boolean passiveToggle) { //only for Prototype robot
		if (PASSIVE_INTAKE_STATUS == 0) {
			intakeWheels.set(PASSIVE_INTAKE_SPEED);
			PASSIVE_INTAKE_STATUS = 1;
		} else if (PASSIVE_INTAKE_STATUS == 1) {
			intakeWheels.set(0);
			PASSIVE_INTAKE_STATUS = 0;
		}
	}
	
	public void manualIntake(double IntakeSpeed) {
		intakeWheels.set(IntakeSpeed);
	}
	
	public void manualReject(double IntakeOutputSpeed) {
		intakeWheels.set(-IntakeOutputSpeed);
	}
}
