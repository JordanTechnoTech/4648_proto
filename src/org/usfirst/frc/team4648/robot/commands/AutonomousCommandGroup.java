package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.position.WayPointChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
	
	public AutonomousCommandGroup(int autoChosen) { // Research how to add 2 parameters here, is it based on extended Command 
		// Turn intake on to hold cube in space
		addSequential(new AutoIntakeCommand(.25, .25, true));
		// Set lift from starting position (back) to running position (upright)
		addSequential(new AutoLiftAcuatorCommand()); 
		
		// Get trajectory and drive
		// Test Code: Get and follow simple trajectory
		addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory())); 
//		// Match Play Code: Get and follow trajectory - Needs to be field tested
//		// Based on autonomous program chosen and switch/scale assignments from GameSpecificMessage
//		if (autoChosen <= 3){
//			addSequential(new PathfinderCommand(WayPointChooser.getPathToSwitch(autoChosen, Robot.gameData)));
//		} 
//		else {
//			addSequential(new PathfinderCommand(WayPointChooser.getPathToScale(autoChosen - 3, Robot.gameData)));
//		}
		
		// Set lift height to switch or scale height
		// int switchHeight = ??;
		// int scaleHeight = ??;
		// chosen based on program selection (1 = to switch, 2 = to scale)
		//addSequential(new SetLiftHeightCommand()); 
		
		// Eject the cube 
		addSequential(new AutoIntakeCommand(2.0, .5, false));
		
	}
}