package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem {

	private final Spark outerLiftMotorController = RobotMap.outerLiftMotorController;
	private final Spark innerLiftMotorController = RobotMap.innerLiftMotorController;
	private static final double LOWER_SPEED_RATE = 0.5;
	
	public final int singleLiftState = 0;
	public final int doubleLiftState = 1;
	public int liftControlState = singleLiftState;
	public double liftControlHeight = 0;
	
	public final double switchHeight = 0;  //TODO: Determine height, Limit Switch?
	public final double scaleHeight = 0; //TODO: Determine height, Limit Switch?
	
	public LiftSubsystem() {
		//TODO: Update with actual installed encoders - Possibly change to just limit switch
		addChild("Inner Lift Encoder", RobotMap.innerLiftEncoder);
		addChild("Outer Lift Encoder", RobotMap.outerLiftEncoder);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new LiftCommand());
	}
	
	public void setLiftState(int liftState)
	{
		liftControlState = liftState;
	}
	
	public void setLiftHeight(double height)
	{
		// TODO: Need to set height based on encoders
		liftControlHeight = height;
	}
	
	public void liftSystemControl(double liftSpeed) {
		if (liftSpeed > 0) {
			innerLiftMotorController.set(liftSpeed ); // need encoder to control height
			if (liftControlState == doubleLiftState) {
				outerLiftMotorController.set(-liftSpeed); // need encoder to control height
			}
			else {
				outerLiftMotorController.set(0);
			}
		}
		else {
			innerLiftMotorController.set(liftSpeed * LOWER_SPEED_RATE ); // need encoder to control height
			if (liftControlState == doubleLiftState) {
				outerLiftMotorController.set(-liftSpeed * LOWER_SPEED_RATE); // need encoder to control height
			}
			else {
				outerLiftMotorController.set(0);
			}
		}
		
		
	}
	public void log() {
		SmartDashboard.putNumber("liftspeed", innerLiftMotorController.get());
		SmartDashboard.putNumber("lift Control Stat", liftControlState);
		SmartDashboard.putNumber("Inner Encoder", RobotMap.innerLiftEncoder.get());
		SmartDashboard.putNumber("Outer Encoder", RobotMap.outerLiftEncoder.get());
		
		}
//	public void outerLiftControl(double liftSpeed) {
//		if (liftSpeed > 0) {
//			outerLiftMotorController.set(liftSpeed);
//		}
//		else {
//			outerLiftMotorController.set(liftSpeed * LOWER_SPEED_RATE);
//		}
//	}
	
}
