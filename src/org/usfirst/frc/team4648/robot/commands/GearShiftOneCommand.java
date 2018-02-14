package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShiftOneCommand extends Command {

    public GearShiftOneCommand() {
    	super();
		requires(Robot.gearShiftOneSubsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.m_oi.getGearShiftOnePressed()) {
    		Robot.gearShiftOneSubsystem.gearShiftOne();
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
