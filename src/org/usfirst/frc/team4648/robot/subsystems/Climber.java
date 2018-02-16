package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	Talon climbMotorController = RobotMap.climbMotorController;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

   public void initDefaultCommand() {
    if (Robot.m_oi.climbStatus == 0 ) {
    	climbMotorController.set(.5);
    }
    else if ( Robot.m_oi.climbStatus == 180 )  {
    	climbMotorController.set(-.25);
    }
    else {
    	climbMotorController.set(0);
    }
    	 
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	
    }
}

