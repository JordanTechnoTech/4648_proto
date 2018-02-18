package org.usfirst.frc.team4648.robot.commands;

import java.util.List;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.position.WayPointChooser;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Trajectory;

public class AutonomousCommandGroup extends CommandGroup {
	
	// Switch & Lever field positions
//	String gameData = new String(DriverStation.getInstance().getGameSpecificMessage());
//	String ourSwitch = gameData.substring(0, 0);
//	String Scale = gameData.substring(1, 1);
//	String opositionSwitich = gameData.substring(2, 2);
	
	public AutonomousCommandGroup() {
//		List<Trajectory> trajectories = WayPointChooser.getStops(1, "L");
//		addSequential(new PathfinderCommand(trajectories.get(0)));
//		addSequential(new PathfinderCommand(trajectories.get(1)));
//		addSequential(new PathfinderCommand(trajectories.get(2)));
		
		// Set lift from starting position (back) to running position (upright)
		addSequential(new AutoLiftCommand()); 
		
		// Get trajectory path
		// Based on :
		// (1) assigned alliance station 1, 2, or 3, input from driver station button
		// (2) program selection (1 = to switch, 2 = to scale), also input from driver station button
		// (3) GameSpecifcMessage sent at start for assigned switch and scale sides,
		//     autonomous only care about near switch
		//addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory())); // returns a trajectory to follow
		
		// Follow trajectory path
		//addSequential(new PathfinderCommand(WayPointChooser.followPath(autoTrajectory)));
		
		// Set lift height to switch or scale height
		// int switchHeight = ??;
		// int scaleHeight = ??;
		// chosen based on program selection (1 = to switch, 2 = to scale)
		//addSequential(new SetLiftHeightCommand()); 
		
		// Eject the cube
		//addSequential(new AutoEject());
		
	}
}