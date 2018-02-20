package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
  @Override
  protected void initialize() {
    super.initialize();
  }

  public AutonomousCommandGroup(int entryPoint, char scaleSide, char switchSide) { // Research how to add 2 parameters
    // here, is it based on extended Command
    // TODO: Sleep added for pause after intake for cube initiated. Java required. Options?

    // Turn intake on to hold cube in space
    addSequential(new AutoIntakeCommand(1, .25, true));
    //addSequential(new AutoLiftAcuatorCommand());
    //TODO BASE THE NEXT SET OF COMMANDS BASED ON THE CONSTUCTOR ARGUMENTS(entryPoint,scaleSide,switchSide)
    addSequential(new DriveStraight(878));
    addSequential(new NinetyDegreesLeft());
    //FINISHED WAYPOINTS

    addSequential(new AutoIntakeCommand(2.0, .4, false));
    addSequential(new AutoIntakeCommand(1.0, .25, false));
  }

  // Eject the cube


}