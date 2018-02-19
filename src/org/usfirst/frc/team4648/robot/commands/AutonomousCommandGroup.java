package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.position.WayPointChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
	
	public AutonomousCommandGroup(int autoChosen) { // Research how to add 2 parameters
		// here, is it based on extended Command
		// TODO: Sleep added for pause after intake for cube initiated. Java required. Options?
	
		try {
			// Turn intake on to hold cube in space
			addSequential(new AutoIntakeCommand(.25, .25, true));
			// Pause for 1 seconds - Build team recommendation to ensure holding cube in
			Thread.sleep(1000); 

			// Set lift from starting position (back) to running position (upright)
			addSequential(new AutoLiftAcuatorCommand());

			// Set lift position to height of field switch
			// addSequential(new SetLiftHeightCommand(Robot.liftSubsystem.switchHeight)); //
			// TODO: Update to Limit Switch

			// Get trajectory and drive
			// Test Code: Get and follow simple trajectory
			addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory()));
			// // Match Play Code: Get and follow trajectory - Needs to be field tested
			// // Based on autonomous program chosen and switch/scale assignments from
			// GameSpecificMessage
			// if (autoChosen <= 3){
			// addSequential(new
			// PathfinderCommand(WayPointChooser.getPathToSwitch(autoChosen,
			// Robot.gameData)));
			// }
			// else {
			// addSequential(new PathfinderCommand(WayPointChooser.getPathToScale(autoChosen
			// - 3, Robot.gameData)));
			// }

			// Set lift height to switch or scale height
			// chosen based on program selection (1 = to switch, 2 = to scale)
			if (autoChosen > 3) {
				// TODO: Given initial set to switch position, only setting to scale position
				// may be needed
				// addSequential(new SetLiftHeightCommand(Robot.liftSubsystem.switchHeight));
				// }
				// else {
				// Move to scale height
				addSequential(new SetLiftHeightCommand(Robot.liftSubsystem.scaleHeight)); // TODO: Limit Switch?
			}

			// Eject the cube
			addSequential(new AutoIntakeCommand(2.0, .5, false));
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}