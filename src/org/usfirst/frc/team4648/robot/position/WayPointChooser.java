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
			    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
			    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
			    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
			};
		return Pathfinder.generate(points,trajectoryConfig);
	}

	public static List<Trajectory> getStops(int startingPoint, String autonomousCode) {
		String firstEndStop = "";
		List<Trajectory> trajectoryList = new ArrayList<>();

		List<Waypoint> starting1ToLeftFront = new ArrayList<>();
		starting1ToLeftFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		List<Waypoint> starting1ToRightBack = new ArrayList<>();
		starting1ToRightBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		List<Waypoint> starting2ToLeftFront = new ArrayList<>();
		starting2ToLeftFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting2ToLeftFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting2ToLeftFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		List<Waypoint> starting2ToRightFront = new ArrayList<>();
		starting2ToRightFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting2ToRightFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting2ToRightFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> starting3ToLeftBack = new ArrayList<>();
		starting3ToLeftBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> starting3ToRightFront = new ArrayList<>();
		starting3ToRightFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		// GET TO SWITCH
		switch (startingPoint) {
		case 1:
			if (autonomousCode.substring(0, 0).equals("L")) {
				firstEndStop = "LF";
				trajectoryList.add(Pathfinder.generate(starting1ToLeftFront.toArray(new Waypoint[starting1ToLeftFront.size()]),
						trajectoryConfig));
			} else {
				firstEndStop = "RB";
				trajectoryList.add(Pathfinder.generate(starting1ToRightBack.toArray(new Waypoint[starting1ToRightBack.size()]),
						trajectoryConfig));
			}
			break;
		case 2:
			if (autonomousCode.substring(0, 0).equals("L")) {
				firstEndStop = "LF";
				trajectoryList.add(Pathfinder.generate(starting2ToLeftFront.toArray(new Waypoint[starting2ToLeftFront.size()]),
						trajectoryConfig));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(Pathfinder.generate(starting2ToRightFront.toArray(new Waypoint[starting2ToRightFront.size()]),
						trajectoryConfig));
			}
			break;
		case 3:
			if (autonomousCode.substring(0, 0).equals("L")) {
				firstEndStop = "LB";
				trajectoryList.add(Pathfinder.generate(starting3ToLeftBack.toArray(new Waypoint[starting1ToLeftFront.size()]),
						trajectoryConfig));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(Pathfinder.generate(starting3ToRightFront.toArray(new Waypoint[starting1ToLeftFront.size()]),
						trajectoryConfig));
			}
			break;
		}

		// GET TO SCALE
		List<Waypoint> leftFrontSwitchToLeftScale = new ArrayList<>();
		starting1ToLeftFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftFrontSwitchToRightScale = new ArrayList<>();
		starting3ToLeftBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftBackSwitchToRightScale = new ArrayList<>();
		starting3ToRightFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftBackSwitchToLeftScale = new ArrayList<>();
		starting1ToRightBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		List<Waypoint> rightFrontSwitchToLeftScale = new ArrayList<>();
		starting1ToLeftFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToLeftFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightFrontSwitchToRightScale = new ArrayList<>();
		starting3ToLeftBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToLeftBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightBackSwitchToRightScale = new ArrayList<>();
		starting3ToRightFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting3ToRightFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightBackSwitchToLeftScale = new ArrayList<>();
		starting1ToRightBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		starting1ToRightBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		switch (firstEndStop) {
		case "LF":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(Pathfinder.generate(leftFrontSwitchToLeftScale.toArray(new Waypoint[leftFrontSwitchToLeftScale.size()]),
						trajectoryConfig));
			} else {
				trajectoryList.add(Pathfinder.generate(leftFrontSwitchToRightScale.toArray(new Waypoint[leftFrontSwitchToRightScale.size()]),
						trajectoryConfig));
			}
			break;
		case "LB":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(Pathfinder.generate(leftBackSwitchToLeftScale.toArray(new Waypoint[leftBackSwitchToLeftScale.size()]),
						trajectoryConfig));
			} else {
				trajectoryList.add(Pathfinder.generate(leftBackSwitchToRightScale.toArray(new Waypoint[leftBackSwitchToRightScale.size()]),
						trajectoryConfig));
			}
			break;
		case "RF":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(Pathfinder.generate(rightFrontSwitchToLeftScale.toArray(new Waypoint[rightFrontSwitchToLeftScale.size()]),
						trajectoryConfig));
			} else {
				trajectoryList.add(Pathfinder.generate(rightFrontSwitchToRightScale.toArray(new Waypoint[rightFrontSwitchToRightScale.size()]),
						trajectoryConfig));
			}
			break;
		case "RB":
			if (autonomousCode.substring(0, 0).equals("L")) {
				trajectoryList.add(Pathfinder.generate(rightBackSwitchToLeftScale.toArray(new Waypoint[rightBackSwitchToLeftScale.size()]),
						trajectoryConfig));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(Pathfinder.generate(rightBackSwitchToRightScale.toArray(new Waypoint[rightBackSwitchToRightScale.size()]),
						trajectoryConfig));
			}
			break;
		}

		return trajectoryList;
	}

	public static void followPath(Trajectory trajectory) {
		TankModifier tank = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_WIDTH);

		EncoderFollower left = new EncoderFollower(tank.getLeftTrajectory());
		EncoderFollower right = new EncoderFollower(tank.getRightTrajectory());

		// Initialize
		left.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER);
		left.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, 0);

		right.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER);
		right.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, 0);
	}
	
	public static void main(String[] args) {
		WayPointChooser.getStops(1, "LL");
	}

}
