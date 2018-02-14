package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.GearShiftTwoCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearShiftTwoSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid gearShift = RobotMap.gearShift;

	public void gearShiftTwo() {

		gearShift.set(true);

	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new GearShiftTwoCommand());
    }
}