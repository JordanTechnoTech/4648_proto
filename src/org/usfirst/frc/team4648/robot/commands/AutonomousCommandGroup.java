package org.usfirst.frc.team4648.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandGroup extends CommandGroup {
  @Override
  protected void initialize() {
    super.initialize();
  }

  public AutonomousCommandGroup(int entryPoint, char scaleSide, char switchSide) {
    // TODO: Sleep added for pause after intake for cube initiated. Java required. Options?

    // Turn intake on to hold cube in space
    addSequential(new AutoIntakeCommand(1, .25, true));
    //addSequential(new AutoLiftAcuatorCommand());
    //TODO BASE THE NEXT SET OF COMMANDS BASED ON THE CONSTUCTOR ARGUMENTS(entryPoint,scaleSide,switchSide)
    addSequential(new DriveStraight(878));
    //TODO uncomment to lift cube before making the last turn
    //addSequential(new AutoLiftCommand());
    addSequential(new NinetyDegreesLeft());
    //FINISHED WAYPOINTS

    //EJECT CUBE
    addSequential(new AutoIntakeCommand(2.0, .4, false));
    //RESET PASSIVE INTAKE MOTORS
    addSequential(new AutoIntakeCommand(1.0, .25, false));
  }


}