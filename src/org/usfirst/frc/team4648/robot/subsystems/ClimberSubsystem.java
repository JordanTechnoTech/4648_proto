package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.ClimberCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	Talon climbMotorController = RobotMap.climbMotorController;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void climb() {
		if (-1 < Robot.m_oi.climbStatus() && Robot.m_oi.climbStatus() < 15) {
	    	climbMotorController.set(1);
	    }
	    else if (135 < Robot.m_oi.climbStatus() && Robot.m_oi.climbStatus() < 225)  {
	    	climbMotorController.set(-1);
	    }
	    else {
	    	climbMotorController.set(0);
	    }
	}
	

   public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberCommand());
    }

}

