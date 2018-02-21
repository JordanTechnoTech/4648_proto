package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class DummyChooserCommand extends Command {
	public int station;
	public String destination;
	public DummyChooserCommand(int i, String string) {
		// TODO Auto-generated constructor stub
		station = i;
		destination = string;
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
