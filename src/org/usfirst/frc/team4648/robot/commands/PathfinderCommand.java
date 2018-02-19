package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathfinderCommand extends Command {
	private static final double INCHES_PER_METER = 0.0254;
	private static final double WHEELBASE_WIDTH = 24 * INCHES_PER_METER; // INCHES
	private static final double WHEEL_DIAMETER = 6 * INCHES_PER_METER; // INCHES

	private static final int ENCODER_TICKS_PER_REVOLUTION = 3;
	private static final int MOTION_PROFILE_MAX_VELOCITY = 9;

	private static final double KP = 0.5; // The proportional term. This is usually quite high (0.8 - 1.0 are common
										// values)
	private static final double KI = 0.0; // The integral term. Currently unused.
	private static final double KD = 0.0; // The derivative term. Adjust this if you are unhappy with the tracking of the
										// follower. 0.0 is the default
	private static final double KV = 1 / MOTION_PROFILE_MAX_VELOCITY; // The velocity ratio. This should be 1 over your maximum velocity @ 100%
										// throttle.
										// This converts m/s given by the algorithm to a scale of -1..1 to be used by
										// your
										// motor controllers
	public static final double KA = 0.8; // The acceleration term. Adjust this if you want to reach higher or lower
										// speeds faster. 0.0 is the default

	private EncoderFollower left;
	private EncoderFollower right;
	private Trajectory trajectory;

	public PathfinderCommand(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public synchronized void start() {
		SmartDashboard.putBoolean("Autonomous Running", true);
		super.start();
	}

	@Override
	protected void initialize() {
		TankModifier modifier = new TankModifier(trajectory).modify(WHEELBASE_WIDTH);
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());

		left.configureEncoder(RobotMap.leftEncoder.get(), ENCODER_TICKS_PER_REVOLUTION, WHEEL_DIAMETER);
		right.configureEncoder(RobotMap.rightEncoder.get(), ENCODER_TICKS_PER_REVOLUTION, WHEEL_DIAMETER);

		left.configurePIDVA(KP, KI, KD, KV, KA);
		right.configurePIDVA(KP, KI, KD, KV, KA);
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();

		double l = left.calculate(RobotMap.leftEncoder.get());
		double r = right.calculate(RobotMap.rightEncoder.get());

		double gyro_heading = RobotMap.imu.getAngle();
		double desired_heading = Pathfinder.r2d(left.getHeading()); // Should also be in degrees

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

		Robot.driveSubsystem.tankDrive(l + turn, r - turn);
		log();
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.tankDrive(0.0, 0.0);
		// TODO Auto-generated method stub
		super.end();
	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putBoolean("Autonomous Running", (left.isFinished() && right.isFinished()));
		return left.isFinished() && right.isFinished();
	}

	public void log() {
		SmartDashboard.putNumber("Current left segment position", left.getSegment().position);
		SmartDashboard.putNumber("Current left segment heading", left.getSegment().heading);
		SmartDashboard.putNumber("Current left segment acceleration", left.getSegment().acceleration);
		SmartDashboard.putNumber("Current left segment velocity", left.getSegment().velocity);
		SmartDashboard.putNumber("Current left segment x", left.getSegment().x);
		SmartDashboard.putNumber("Current left segment y", left.getSegment().y);
		SmartDashboard.putNumber("Current left segment jerk", left.getSegment().jerk);

		SmartDashboard.putNumber("Current right segment position", right.getSegment().position);
		SmartDashboard.putNumber("Current right segment heading", right.getSegment().heading);
		SmartDashboard.putNumber("Current right segment acceleration", right.getSegment().acceleration);
		SmartDashboard.putNumber("Current right segment velocity", right.getSegment().velocity);
		SmartDashboard.putNumber("Current right segment x", right.getSegment().x);
		SmartDashboard.putNumber("Current right segment y", right.getSegment().y);
		SmartDashboard.putNumber("Current right segment jerk", right.getSegment().jerk);
	}

}
