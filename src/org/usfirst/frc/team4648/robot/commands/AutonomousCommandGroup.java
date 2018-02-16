package org.usfirst.frc.team4648.robot.commands;

import java.util.List;

import org.usfirst.frc.team4648.robot.position.WayPointChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Trajectory;

public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
//		List<Trajectory> trajectories = WayPointChooser.getStops(1, "L");
//		addSequential(new PathfinderCommand(trajectories.get(0)));
//		addSequential(new PathfinderCommand(trajectories.get(1)));
//		addSequential(new PathfinderCommand(trajectories.get(2)));
		addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory()));
	}
}