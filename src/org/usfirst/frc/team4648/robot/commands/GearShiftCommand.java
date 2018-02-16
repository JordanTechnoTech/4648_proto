package org.usfirst.frc.team4648.robot.commands;

import org.usfirst.frc.team4648.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearShiftCommand extends Command {

    public GearShiftCommand() {
    	super();
		requires(Robot.gearShiftSubsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.m_oi.getGearShiftOnePressed()) {
    		Robot.gearShiftSubsystem.gearShiftOne();
    	} else if (Robot.m_oi.getGearShiftTwoPressed()) {
        		Robot.gearShiftSubsystem.gearShiftTwo();
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
