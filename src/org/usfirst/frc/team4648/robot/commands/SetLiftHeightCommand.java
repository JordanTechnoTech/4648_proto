package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeightCommand extends Command {

	double m_liftHeight = 0;

	public SetLiftHeightCommand(double liftHeight) {
		m_liftHeight = liftHeight;
		requires(Robot.liftSubsystem);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		//TODO: Set up limit switches - First Choice per Noah, design to be finalized
		//TODO: Set up encoders - Second Choice per Noah, design to be finalized
		// Below is code example for other encoders...
//		inner = new EncoderFollower(modifier.getLeftTrajectory());
//		outer = new EncoderFollower(modifier.getRightTrajectory());
//		
//		inner.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
//		outer.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
	}
	
	@Override
	protected void execute() {
		Robot.liftSubsystem.setLiftHeight(m_liftHeight);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}
}
