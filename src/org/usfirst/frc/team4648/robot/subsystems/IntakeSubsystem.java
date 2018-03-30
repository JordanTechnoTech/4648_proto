package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.IntakeCommand;


import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSubsystem extends Subsystem {
	Talon intakeWheelRight = RobotMap.intakeWheelRight;
	Talon intakeWheelLeft = RobotMap.intakeWheelLeft ;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	
	public void manualIntake(double IntakeSpeed) {
		intakeWheelRight.set(IntakeSpeed); // wires crossed in final robot, so inverse speed
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
