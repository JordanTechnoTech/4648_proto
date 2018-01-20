/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot;

import org.usfirst.frc.team4648.robot.commands.DriveCommand;
import org.usfirst.frc.team4648.robot.commands.ToggleClawCommand;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	XboxController controller = new XboxController(1);
	
	//// CREATING BUTTONS
	// You create one by telling it which joystick it's on and which button
	// number it is.
	Button leftBumperButton = new XBoxButton(controller, XBoxButton.kBumperLeft);
	Button rightBumperButton = new XBoxButton(controller, XBoxButton.kBumperRight);
	Button aButton = new XBoxButton(controller, XBoxButton.kA);
	Button bButton = new XBoxButton(controller, XBoxButton.kB);
	Button xButton = new XBoxButton(controller, XBoxButton.kX);
	Button yButton = new XBoxButton(controller, XBoxButton.kY);
	Button backButton = new XBoxButton(controller, XBoxButton.kBack);
	Button startButton = new XBoxButton(controller, XBoxButton.kStart);
	Button stickLeft = new XBoxButton(controller, XBoxButton.kStickLeft);
	Button stickRight = new XBoxButton(controller, XBoxButton.kStickRight);
	
	public OI() {
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
//		stickLeft.whileHeld(new DriveCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
		aButton.whenPressed(new ToggleClawCommand());
	 }
	
	public double getSpeedLeft() {
		return  controller.getY(Hand.kLeft);
	}
	
	public double getSpeedRight() {
		return controller.getY(Hand.kRight);
	}
	
	public double getArcardeSpeed() {
		return controller.getY(GenericHID.Hand.kLeft);
	}
	
	public double getRotationSpeed() {
		return controller.getX(GenericHID.Hand.kLeft);
	}
	
	public double getLiftSpeed() {
		return controller.getY(GenericHID.Hand.kRight);
	}
}
