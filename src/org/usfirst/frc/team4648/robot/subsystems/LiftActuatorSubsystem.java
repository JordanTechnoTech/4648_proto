package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.ToggleActuatorCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftActuatorSubsystem extends Subsystem {
	Solenoid leftLiftActuator = RobotMap.leftLiftActuate;
	Solenoid rightLiftActuator = RobotMap.rightLiftActuate;

	public LiftActuatorSubsystem() {
		super();
		addChild(leftLiftActuator);
		addChild(rightLiftActuator);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ToggleActuatorCommand());
	}

	public void stateTrue() {
		leftLiftActuator.set(true);
		rightLiftActuator.set(true);
	}

	public void stateFalse() {
		leftLiftActuator.set(false);
		rightLiftActuator.set(false);

	}
}
