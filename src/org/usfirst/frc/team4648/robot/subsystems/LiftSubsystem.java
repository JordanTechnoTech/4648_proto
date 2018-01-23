package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

public class LiftSubsystem extends Subsystem {

	private final Spark liftMotorController = RobotMap.liftMotorController;
	public static final double LOWER_SPEED_RATE = 0.3;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new LiftCommand());
	}
	
	public void liftControl(double liftSpeed) {
		if (liftSpeed > 0) {
			liftMotorController.set(liftSpeed);
		}
		else {
			liftMotorController.set(liftSpeed * LOWER_SPEED_RATE);
		}
	}
	
}
