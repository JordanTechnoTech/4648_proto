/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot;

import org.usfirst.frc.team4648.robot.commands.PathfinderCommand;
import org.usfirst.frc.team4648.robot.commands.SetLiftStateCommand;
import org.usfirst.frc.team4648.robot.position.WayPointChooser;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	XboxController controller1 = new XboxController(1);

	//// CREATING BUTTONS - THIS IS UNUSED
	// You create one by telling it which joystick it's on and which button
	// number it is.
	Button leftBumperButton = new XBoxButton(controller1, XBoxButton.kBumperLeft);
	Button rightBumperButton = new XBoxButton(controller1, XBoxButton.kBumperRight);
	Button aButton = new XBoxButton(controller1, XBoxButton.kA);
	Button bButton = new XBoxButton(controller1, XBoxButton.kB);
	Button xButton = new XBoxButton(controller1, XBoxButton.kX);
	Button yButton = new XBoxButton(controller1, XBoxButton.kY);
	Button backButton = new XBoxButton(controller1, XBoxButton.kBack);
	Button startButton = new XBoxButton(controller1, XBoxButton.kStart);
	Button stickLeft = new XBoxButton(controller1, XBoxButton.kStickLeft);
	Button stickRight = new XBoxButton(controller1, XBoxButton.kStickRight);

	public OI() {
		//// TRIGGERING COMMANDS WITH BUTTONS
		// Once you have a button, it's trivial to bind it to a button in one of
		// three ways:

		// Start the command when the button is pressed and let it run the command
		// until it is finished as determined by it's isFinished method.
		// stickLeft.whileHeld(new DriveCommand());

		// Run the command while the button is being held down and interrupt it once
		// the button is released.
		yButton.whileHeld(new PathfinderCommand(WayPointChooser.getStops(1, "L").get(0)));

		// Start the command when the button is released and let it run the command
		// until it is finished as determined by it's isFinished method.
		bButton.whenPressed(new SetLiftStateCommand(0));
		xButton.whenPressed(new SetLiftStateCommand(1));
	}

	public double getArcardeSpeed() { // DriveCommand
		return controller1.getY(GenericHID.Hand.kLeft);
	}

	public double getRotationSpeed() { // DriveCommand
		return controller1.getX(GenericHID.Hand.kLeft);
	}

	public boolean getGearShiftOnePressed() { // DriveCommand
		return controller1.getBumper(GenericHID.Hand.kLeft);
	}

	public boolean getGearShiftTwoPressed() { // DriveCommand
		return controller1.getBumper(GenericHID.Hand.kRight);
	}

	public double getLiftSpeed() { // LiftCommand
		return controller1.getY(GenericHID.Hand.kRight);
	}

	public boolean getClawToggle() { // ToggleClawCommand
		return controller1.getAButton();
	}
	
	public boolean getActuatorToggle() { // LiftActutatorCommand
		return controller1.getStartButton();
	}

	public boolean getSingleLiftToggle() { // ToggleLiftState - not needed if SetLiftStateCommand implementation works
		return controller1.getBButton();
	}

	public boolean getDoubleLiftToggle() { // ToggleLiftState - not needed if SetLiftStateCommand implementation works
		return controller1.getXButton();
	}

	public double climbStatus = controller1.getPOV(); // for the climber
	
	// public boolean getPassiveIntakeToggle() { //IntakeCommand // used on in
	// Prototype
	// return controller1.getBButton();
	// }

	public double getIntakeSpeed() { // IntakeCommand
		return controller1.getTriggerAxis(GenericHID.Hand.kRight);
	}

	public double getRejectSpeed() { // IntakeCommand
		return controller1.getTriggerAxis(GenericHID.Hand.kLeft);
	}
}
