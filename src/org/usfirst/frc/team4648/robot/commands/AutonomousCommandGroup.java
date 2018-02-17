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
		//addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory()));
		RobotMap.rightLiftActuate.set(true);
		RobotMap.leftLiftActuate.set(true);
	}
}