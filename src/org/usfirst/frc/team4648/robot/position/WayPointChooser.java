package org.usfirst.frc.team4648.robot.position;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4648.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class WayPointChooser {

	static Trajectory.Config trajectoryConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
			Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
	
	public static Trajectory getSimpleTrajectory() {
		Waypoint[] points = new Waypoint[] {
			    new Waypoint(4, 4, Pathfinder.d2r(45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(2, 2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
			};
		return Pathfinder.generate(points,trajectoryConfig);
	}

	// Distances in inches
	// Rotation assumed at beginning of waypoint (move up 1 waypoint level if at end)
	public static Trajectory getPathToSwitch(int startingPoint, String autonomousCode) {
		List<Waypoint> starting1ToLeftSwitch = new ArrayList<>();
		starting1ToLeftSwitch.add(new Waypoint(132, 0, 0));
		starting1ToLeftSwitch.add(new Waypoint(0, 12, Pathfinder.d2r(90)));
		starting1ToLeftSwitch.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting1ToRightSwitch = new ArrayList<>();
		starting1ToRightSwitch.add(new Waypoint(196, 0, 0));
		starting1ToRightSwitch.add(new Waypoint(0, 228, Pathfinder.d2r(90)));
		starting1ToRightSwitch.add(new Waypoint(-64, 0, Pathfinder.d2r(90)));
		starting1ToRightSwitch.add(new Waypoint(0, -12, Pathfinder.d2r(90)));
		starting1ToRightSwitch.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting2ToLeftSwitch = new ArrayList<>();
		starting2ToLeftSwitch.add(new Waypoint(28, 0, 0));
		starting2ToLeftSwitch.add(new Waypoint(0, -156, Pathfinder.d2r(270)));
		starting2ToLeftSwitch.add(new Waypoint(104, 0, Pathfinder.d2r(90)));
		starting2ToLeftSwitch.add(new Waypoint(0, 12, Pathfinder.d2r(90)));
		starting2ToLeftSwitch.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting2ToRightSwitch = new ArrayList<>();
		starting2ToRightSwitch.add(new Waypoint(28, 0, 0));
		starting2ToRightSwitch.add(new Waypoint(0, 72, Pathfinder.d2r(90)));
		starting2ToRightSwitch.add(new Waypoint(104, 0, Pathfinder.d2r(270)));
		starting2ToRightSwitch.add(new Waypoint(0, -12, Pathfinder.d2r(270)));
		starting2ToRightSwitch.add(new Waypoint(0, 0, 0));

		List<Waypoint> starting3ToLeftSwitch = new ArrayList<>();
		starting3ToLeftSwitch.add(new Waypoint(196, 0, 0));
		starting3ToLeftSwitch.add(new Waypoint(0, -228, Pathfinder.d2r(270)));
		starting3ToLeftSwitch.add(new Waypoint(-64, 0, Pathfinder.d2r(90)));
		starting3ToLeftSwitch.add(new Waypoint(0, 12, Pathfinder.d2r(90)));
		starting3ToLeftSwitch.add(new Waypoint(0, 0, 0));

		List<Waypoint> starting3ToRightSwitch = new ArrayList<>();
		starting3ToRightSwitch.add(new Waypoint(132, 0, 0));
		starting3ToRightSwitch.add(new Waypoint(0, -12, Pathfinder.d2r(270)));
		starting3ToRightSwitch.add(new Waypoint(0, 0, 0));

		// GET TO SWITCH
		switch (startingPoint) {
		case 1:
			if (autonomousCode.substring(0, 0).equals("L")) {
				return generateTrajectory(starting1ToLeftSwitch);
			} else {
				return generateTrajectory(starting1ToRightSwitch);
			}
		case 2:
			if (autonomousCode.substring(0, 0).equals("L")) {
				return generateTrajectory(starting2ToLeftSwitch);
			} else {
				return generateTrajectory(starting2ToRightSwitch);
			}
		case 3:
			if (autonomousCode.substring(0, 0).equals("L")) {
				return generateTrajectory(starting3ToLeftSwitch);
			} else {
				return generateTrajectory(starting3ToRightSwitch);
			}
		}
		return null;
	}
	
	public static Trajectory getPathToScale(int startingPoint,String autonomousCode) {
		List<Waypoint> starting1ToLeftScale = new ArrayList<>();
		starting1ToLeftScale.add(new Waypoint(288, 0, 0));
		starting1ToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(90)));
		starting1ToLeftScale.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting1ToRightScale = new ArrayList<>();
		starting1ToRightScale.add(new Waypoint(196, 0, 0));
		starting1ToRightScale.add(new Waypoint(0, 228, Pathfinder.d2r(90)));
		starting1ToRightScale.add(new Waypoint(92, 0, Pathfinder.d2r(90)));
		starting1ToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(90)));
		starting1ToRightScale.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting2ToLeftScale = new ArrayList<>();
		starting2ToLeftScale.add(new Waypoint(28, 0, 0));
		starting2ToLeftScale.add(new Waypoint(0, -156, Pathfinder.d2r(270)));
		starting2ToLeftScale.add(new Waypoint(260, 0, Pathfinder.d2r(90)));
		starting2ToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(90)));
		starting2ToLeftScale.add(new Waypoint(0, 0, 0));
		
		List<Waypoint> starting2ToRightScale = new ArrayList<>();
		starting2ToRightScale.add(new Waypoint(28, 0, 0));
		starting2ToRightScale.add(new Waypoint(0, 72, Pathfinder.d2r(90)));
		starting2ToRightScale.add(new Waypoint(260, 0, Pathfinder.d2r(270)));
		starting2ToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(270)));
		starting2ToRightScale.add(new Waypoint(0, 0, 0));

		List<Waypoint> starting3ToLeftScale = new ArrayList<>();
		starting3ToLeftScale.add(new Waypoint(196, 0, 0));
		starting3ToLeftScale.add(new Waypoint(0, -228, Pathfinder.d2r(270)));
		starting3ToLeftScale.add(new Waypoint(92, 0, Pathfinder.d2r(90)));
		starting3ToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(90)));
		starting3ToLeftScale.add(new Waypoint(0, 0, 0));

		List<Waypoint> starting3ToRightScale = new ArrayList<>();
		starting3ToRightScale.add(new Waypoint(228, 0, 0));
		starting3ToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(270)));
		starting3ToRightScale.add(new Waypoint(0, 0, 0));

		// GET TO SWITCH
		switch (startingPoint) {
		case 1:
			if (autonomousCode.substring(1, 1).equals("L")) {
				return generateTrajectory(starting1ToLeftScale);
			} else {
				return generateTrajectory(starting1ToRightScale);
			}
		case 2:
			if (autonomousCode.substring(1, 1).equals("L")) {
				return generateTrajectory(starting2ToLeftScale);
			} else {
				return generateTrajectory(starting2ToRightScale);
			}
		case 3:
			if (autonomousCode.substring(1, 1).equals("L")) {
				return generateTrajectory(starting3ToLeftScale);
			} else {
				return generateTrajectory(starting3ToRightScale);
			}
		}
		return null;
	}

	public static void followPath(Trajectory trajectory) {
		TankModifier tank = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_WIDTH);

		EncoderFollower left = new EncoderFollower(tank.getLeftTrajectory());
		EncoderFollower right = new EncoderFollower(tank.getRightTrajectory());

		// Initialize
		left.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER);
		left.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, .1);

		right.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER);
		right.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, .1);
	}
	
	public static void main(String[] args) {
		WayPointChooser.getSimpleTrajectory();
		System.out.println("DONE");
	}
	
	public static Trajectory generateTrajectory(List<Waypoint> wayPoints) {
		return Pathfinder.generate(wayPoints.toArray(new Waypoint[wayPoints.size()]),trajectoryConfig);
	}

}
