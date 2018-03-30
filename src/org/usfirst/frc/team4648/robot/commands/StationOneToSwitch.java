package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StationOneToSwitch extends CommandGroup {

	public StationOneToSwitch() { // Research how to add 2 parameters
		// here, is it based on extended Command
		// TODO: Sleep added for pause after intake for cube initiated. Java required.
		// Options?

		// Turn intake on to hold cube in space

		if (RobotMap.switchGoal.equals("L")) {
			addSequential(new AutoIntakeCommand(1, .25, true));
			addSequential(new AutoLiftAcuatorCommand());
			addSequential(new DriveStraight(878));
				addParallel( new AutoLiftToLimitSwitch());
			addSequential(new NinetyDegreesRight());
			//addSequential(new DriveStraight(190));
			addSequential(new AutoIntakeCommand(2.0, .6, false));
		} else if (RobotMap.switchGoal.equals("R")) {
			addSequential(new AutoIntakeCommand(1, .25, true));
			addSequential(new AutoLiftAcuatorCommand());
			addSequential(new DriveStraight(1370));
				addParallel( new AutoLiftToLimitSwitch());
			addSequential(new NinetyDegreesRight());
			addSequential(new DriveStraight(948));
			addSequential(new NinetyDegreesRight());
			addSequential(new AutoIntakeCommand(2.0, .6, false));
		}
	}

	// Eject the cube

}