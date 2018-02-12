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

	static Trajectory.Config trajectoryConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);

	public static List<Trajectory> getStops(int startingPoint, String code) {
		List<Trajectory> trajectoryList = new ArrayList<>();

		List<Waypoint> startToLeftFront = new ArrayList<>();
		startToLeftFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		startToLeftFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		startToLeftFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> startToLeftBack = new ArrayList<>();
		startToLeftBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		startToLeftBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		startToLeftBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> startToRightFront = new ArrayList<>();
		startToRightFront.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		startToRightFront.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		startToRightFront.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		List<Waypoint> startToRightBack = new ArrayList<>();
		startToRightBack.add(new Waypoint(-4, -1, Pathfinder.d2r(-45)));
		startToRightBack.add(new Waypoint(-2, -1, Pathfinder.d2r(-45)));
		startToRightBack.add(new Waypoint(0, 0, Pathfinder.d2r(-45)));

		// GET TO SWITCH
		switch (startingPoint) {
		case 1:
			if (code.substring(0, 0).equals("L"))
				trajectoryList.add(Pathfinder.generate(startToLeftFront.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			else
				trajectoryList.add(Pathfinder.generate(startToRightBack.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			break;
		case 2:
			if (code.substring(0, 0).equals("L"))
				trajectoryList.add(Pathfinder.generate(startToLeftFront.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			else
				trajectoryList.add(Pathfinder.generate(startToRightFront.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			break;
		case 3:
			if (code.substring(0, 0).equals("L"))
				trajectoryList.add(Pathfinder.generate(startToLeftBack.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			else
				trajectoryList.add(Pathfinder.generate(startToRightFront.toArray(new Waypoint[startToLeftFront.size()]),trajectoryConfig));
			break;
		}
		return trajectoryList;
	}
	
	public static void followPath(Trajectory trajectory) {
		TankModifier tank = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_WIDTH);

        EncoderFollower left = new EncoderFollower(tank.getLeftTrajectory());
        EncoderFollower right = new EncoderFollower(tank.getRightTrajectory());
        
        
        //Initialize 
        left.configureEncoder(RobotMap.leftEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
        left.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, 0);

        right.configureEncoder(RobotMap.rightEncoder.get(), RobotMap.ENCODER_TICKS_PER_REVOLUTION, RobotMap.WHEEL_DIAMETER);
        right.configurePIDVA(1, 0, 0, 1 / RobotMap.MOTION_PROFILE_MAX_VELOCITY, 0);
	}
	
	public static void useTrajectory(Trajectory trajectory) {
		for (int i = 0; i < trajectory.length(); i++) {
		    Trajectory.Segment seg = trajectory.get(i);
		    
		    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
		        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
		            seg.acceleration, seg.jerk, seg.heading);
		}
	}
}
