package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	Talon intakeWheels = RobotMap.intakeWheels;
	public final double PASSIVE_INTAKE_SPEED = 0.25;
	public double PASSIVE_INTAKE_STATUS = 0;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new IntakeCommand());
	}
	
	public void passiveIntake(boolean passiveToggle) {
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
	
	public void manualIntakeOutput(double IntakeOutputSpeed) {
		intakeWheels.set(-IntakeOutputSpeed);
	}
}
