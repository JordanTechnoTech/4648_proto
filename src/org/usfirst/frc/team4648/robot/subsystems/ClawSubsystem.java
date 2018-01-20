package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	Solenoid clawSolenoid = RobotMap.clawSolenoid;

	
	public ClawSubsystem() {
		super();
		addChild(clawSolenoid);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	public void toggleClaw() {
		if (clawSolenoid.get()) {
			clawSolenoid.set(false);
		}
		else {
			clawSolenoid.set(true);
		}
	}

}
