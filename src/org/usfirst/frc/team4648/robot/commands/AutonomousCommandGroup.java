package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
	public AutonomousCommandGroup() {
		addSequential(new PathfinderCommand());

	}
}