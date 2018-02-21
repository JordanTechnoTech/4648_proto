package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLine extends CommandGroup {

	public AutoCrossLine() { // Research how to add 2 parameters
		// here, is it based on extended Command
		// TODO: Sleep added for pause after intake for cube initiated. Java required.
		// Options?

		// Turn intake on to hold cube in space
		
		addSequential(new AutoIntakeCommand(1, .25, true));
		addSequential(new AutoLiftAcuatorCommand());
		addSequential(new DriveStraight(596));

	}

	// Eject the cube

}