package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4648.robot.Robot;
import org.usfirst.frc.team4648.robot.position.WayPointChooser;

public class AutonomousCommandGroup extends CommandGroup {

  public AutonomousCommandGroup(int autoChosen) { // Research how to add 2 parameters
    // here, is it based on extended Command
    // TODO: Sleep added for pause after intake for cube initiated. Java required. Options?

    // Turn intake on to hold cube in space
    addSequential(new AutoIntakeCommand(1, .25, true));
	addSequential( new DriveStraight(878));
	addSequential( new NinetyDegreesLeft());
	addSequential(new AutoIntakeCommand(2.0, .4, false));
	
    // Pause for 1 seconds - Build team recommendation to ensure holding cube in

    // Set lift from starting position (back) to running position (upright)
    //addSequential(new AutoLiftAcuatorCommand());
//	  addSequential(new PathfinderCommand(WayPointChooser.getSimpleTrajectory()));

    // Set lift position to height of field switch
//     addSequential(new SetLiftHeightCommand(Robot.liftSubsystem.switchHeight)); //
    // TODO: Update to Limit Switch

    // Get trajectory and drive
    // Test Code: Get and follow simple trajectory
   
    // // Match Play Code: Get and follow trajectory - Needs to be field tested
    // // Based on autonomous program chosen and switch/scale assignments from
    // GameSpecificMessage
/*     if (autoChosen <= 3){
     addSequential(new
     PathfinderCommand(WayPointChooser.getPathToSwitch(autoChosen,
     Robot.gameData)));
     }
     else {
     addSequential(new PathfinderCommand(WayPointChooser.getPathToScale(autoChosen
     - 3, Robot.gameData)));
     }*/

    // Set lift height to switch or scale height
    // chosen based on program selection (1 = to switch, 2 = to scale)
    //if (autoChosen == 2) {
      // TODO: Given initial set to switch position, only setting to scale position
      // may be needed
      // addSequential(new SetLiftHeightCommand(Robot.liftSubsystem.switchHeight));
      // }
      // else {mand(Robot.liftSubsystem.scaleHeight)); // TODO: Limit Switch?
//      addParallel(new AutoLiftCommand());
   addSequential(new AutoIntakeCommand(2.0, .25, false));
    }

    // Eject the cube
   
 
}