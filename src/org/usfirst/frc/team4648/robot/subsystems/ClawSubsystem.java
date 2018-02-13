package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.ToggleClawCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	Solenoid clawSolenoid = RobotMap.clawActuate;

	public ClawSubsystem() {
		super();
		addChild(clawSolenoid);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ToggleClawCommand());
	}

	public void toggleClaw() {
		if (clawSolenoid.get()) {
			clawSolenoid.set(false);
		} else {
			clawSolenoid.set(true);
		}
	}

	public void openClaw() {

		clawSolenoid.set(true);

	}

	public void closeClaw() {

		clawSolenoid.set(false);

	}

}
