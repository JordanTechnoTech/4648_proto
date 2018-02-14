package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShiftTwoCommand extends Command {

    public GearShiftTwoCommand() {
    	super();
		requires(Robot.gearShiftTwoSubsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.m_oi.getGearShiftOnePressed()) {
    		Robot.gearShiftTwoSubsystem.gearShiftTwo();
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
