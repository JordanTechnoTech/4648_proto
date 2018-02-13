package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class LiftSubsystem extends Subsystem {

	private final Spark outerLiftMotorController = RobotMap.outerLiftMotorController;
	private final Talon innerLiftMotorController = RobotMap.innerLiftMotorController;
	public static final double LOWER_SPEED_RATE = 0.3;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new LiftCommand());
	}
	
	public void innerLiftControl(double liftSpeed) {
		if (liftSpeed > 0) {
			innerLiftMotorController.set(liftSpeed);
		}
		else {
			innerLiftMotorController.set(liftSpeed * LOWER_SPEED_RATE);
		}
	}
	
	public void outerLiftControl(double liftSpeed) {
		if (liftSpeed > 0) {
			outerLiftMotorController.set(liftSpeed);
		}
		else {
			outerLiftMotorController.set(liftSpeed * LOWER_SPEED_RATE);
		}
	}
	
}
