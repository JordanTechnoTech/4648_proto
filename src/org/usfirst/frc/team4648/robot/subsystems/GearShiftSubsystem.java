package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.GearShiftOneCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearShiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid gearShiftLeft = RobotMap.gearShiftLeft;
	Solenoid gearShiftRight = RobotMap.gearShiftRight;

	public void gearShiftOne() {
		
	}
	
    public void initDefaultCommand() {
    	

    	
        setDefaultCommand(new GearShiftOneCommand());
    }
}