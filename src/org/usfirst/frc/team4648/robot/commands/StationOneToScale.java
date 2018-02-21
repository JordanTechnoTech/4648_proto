package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StationOneToScale extends CommandGroup {

	public StationOneToScale() { // Research how to add 2 parameters
		// here, is it based on extended Command
		// TODO: Sleep added for pause after intake for cube initiated. Java required.
		// Options?

		// Turn intake on to hold cube in space

		if (RobotMap.switchGoal.equals("L")) {
			addSequential(new AutoIntakeCommand(1, .25, true));
			addSequential(new AutoLiftAcuatorCommand());
			addSequential(new DriveStraight(1880));
			addSequential(new AutoLiftCommand());
			addSequential(new NinetyDegreesRight());
			addSequential(new AutoIntakeCommand(2.0, .4, false));
		} else if (RobotMap.switchGoal.equals("R")) {
			addSequential(new AutoIntakeCommand(1, .25, true));
			addSequential(new AutoLiftAcuatorCommand());
			addSequential(new DriveStraight(1340));
			addSequential(new NinetyDegreesRight());
			addSequential(new DriveStraight(1490));
			addSequential(new NinetyDegreesLeft());
			addSequential(new DriveStraight(460));
			addSequential(new AutoLiftCommand());
			addSequential(new NinetyDegreesLeft());
			addSequential(new AutoIntakeCommand(2.0, .4, false));
		}
	}
}