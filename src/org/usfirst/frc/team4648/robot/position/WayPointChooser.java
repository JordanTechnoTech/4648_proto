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
				trajectoryList.add(generateTrajectory(starting1ToLeftFront));
			} else {
				firstEndStop = "RB";
				trajectoryList.add(generateTrajectory(starting1ToRightBack));
			}
			break;
		case 2:
			if (autonomousCode.substring(0, 0).equals("L")) {
				firstEndStop = "LF";
				trajectoryList.add(generateTrajectory(starting2ToLeftFront));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(generateTrajectory(starting2ToRightFront));
			}
			break;
		case 3:
			if (autonomousCode.substring(0, 0).equals("L")) {
				firstEndStop = "LB";
				trajectoryList.add(generateTrajectory(starting3ToLeftBack));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(generateTrajectory(starting3ToRightFront));
			}
			break;
		}

		// GET TO SCALE
		List<Waypoint> leftFrontSwitchToLeftScale = new ArrayList<>();
		leftFrontSwitchToLeftScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		leftFrontSwitchToLeftScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		leftFrontSwitchToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftFrontSwitchToRightScale = new ArrayList<>();
		leftFrontSwitchToRightScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		leftFrontSwitchToRightScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		leftFrontSwitchToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftBackSwitchToRightScale = new ArrayList<>();
		leftBackSwitchToRightScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		leftBackSwitchToRightScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		leftBackSwitchToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> leftBackSwitchToLeftScale = new ArrayList<>();
		leftBackSwitchToLeftScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		leftBackSwitchToLeftScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		leftBackSwitchToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		List<Waypoint> rightFrontSwitchToLeftScale = new ArrayList<>();
		rightFrontSwitchToLeftScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		rightFrontSwitchToLeftScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		rightFrontSwitchToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightFrontSwitchToRightScale = new ArrayList<>();
		rightFrontSwitchToRightScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		rightFrontSwitchToRightScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		rightFrontSwitchToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightBackSwitchToRightScale = new ArrayList<>();
		rightBackSwitchToRightScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		rightBackSwitchToRightScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		rightBackSwitchToRightScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> rightBackSwitchToLeftScale = new ArrayList<>();
		rightBackSwitchToLeftScale.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		rightBackSwitchToLeftScale.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		rightBackSwitchToLeftScale.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));
		
		switch (firstEndStop) {
		case "LF":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(generateTrajectory(leftFrontSwitchToLeftScale));
			} else {
				trajectoryList.add(generateTrajectory(leftFrontSwitchToRightScale));
			}
			break;
		case "LB":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(generateTrajectory(leftBackSwitchToLeftScale));
			} else {
				trajectoryList.add(generateTrajectory(leftBackSwitchToRightScale));
			}
			break;
		case "RF":
			if (autonomousCode.substring(1, 1).equals("L")) {
				trajectoryList.add(generateTrajectory(rightFrontSwitchToLeftScale));
			} else {
				trajectoryList.add(generateTrajectory(rightFrontSwitchToRightScale));
			}
			break;
		case "RB":
			if (autonomousCode.substring(0, 0).equals("L")) {
				trajectoryList.add(generateTrajectory(rightBackSwitchToLeftScale));
			} else {
				firstEndStop = "RF";
				trajectoryList.add(generateTrajectory(rightBackSwitchToRightScale));
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
	
	public static Trajectory generateTrajectory(List<Waypoint> wayPoints) {
		return Pathfinder.generate(wayPoints.toArray(new Waypoint[wayPoints.size()]),trajectoryConfig);
	}

}
