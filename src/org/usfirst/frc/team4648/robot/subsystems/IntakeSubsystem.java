package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.IntakeCommand;


import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSubsystem extends Subsystem {
	Talon intakeWheelRight = RobotMap.intakeWheelRight;
	Talon intakeWheelLeft = RobotMap.intakeWheelLeft ;
	public final double PASSIVE_INTAKE_SPEED = 0.25;
	public double PASSIVE_INTAKE_STATUS = 0;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	
	public void passiveIntake(boolean passiveToggle) { //only for Prototype robot
		if (PASSIVE_INTAKE_STATUS == 0) {
			//intakeWheelRight.set(PASSIVE_INTAKE_SPEED);
			//intakeWheelLeft.set(PASSIVE_INTAKE_SPEED);
			PASSIVE_INTAKE_STATUS = 1;
		} else if (PASSIVE_INTAKE_STATUS == 1) {
			intakeWheelRight.set(0);
			intakeWheelLeft.set(0);
			PASSIVE_INTAKE_STATUS = 0;
		}
	}
	
	public void manualIntake(double IntakeSpeed) {
		intakeWheelRight.set(IntakeSpeed);
		intakeWheelLeft.set(IntakeSpeed);
	}
	
	public void manualReject(double IntakeOutputSpeed) {
		intakeWheelRight.set(-IntakeOutputSpeed);
		intakeWheelLeft.set(-IntakeOutputSpeed);
	}
	
	public void log() {
		SmartDashboard.putNumber("Intake Wheels Right", intakeWheelRight.get());
		SmartDashboard.putNumber("Intake Wheels Left",intakeWheelLeft.get());
	}
}
