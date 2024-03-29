package org.usfirst.frc.team4648.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4648.robot.commands.StationTwoToSwitch;
import org.usfirst.frc.team4648.robot.commands.AutoCrossLine;
import org.usfirst.frc.team4648.robot.commands.StationOneToScale;
import org.usfirst.frc.team4648.robot.commands.StationOneToSwitch;
import org.usfirst.frc.team4648.robot.commands.StationThreeToScale;
import org.usfirst.frc.team4648.robot.commands.StationThreeToSwitch;
import org.usfirst.frc.team4648.robot.commands.DummyChooserCommand;
import org.usfirst.frc.team4648.robot.subsystems.ClawSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.GearShiftSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.LiftActuatorSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.LiftSubsystem;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static OI m_oi;

	public static DriveSubsystem driveSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static ClawSubsystem clawSubsystem;
	public static LiftActuatorSubsystem liftActuatorSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static GearShiftSubsystem gearShiftSubsystem;
	public static ClimberSubsystem climberSubsystem;

	public static String gameData;
	// public static String ourSwitchAssignment;
	// public static String ourScaleAssignment;
	// public static String oppositionSwitchAssignment;

	Thread m_visionThread;
	Command m_autonomousCommand;
	Command driveCommand;

	SendableChooser<Command> autonomousChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		driveSubsystem = new DriveSubsystem();
		liftSubsystem = new LiftSubsystem();
		clawSubsystem = new ClawSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		liftActuatorSubsystem = new LiftActuatorSubsystem();
		gearShiftSubsystem = new GearShiftSubsystem();
		climberSubsystem = new ClimberSubsystem();
		RobotMap.imu.reset();
		m_oi = new OI();

		// Init of subsystems
		liftActuatorSubsystem.stateFalse();
		// puts the robot into first gear upon startup
		Robot.gearShiftSubsystem.gearShiftOne();

		// Activates the lift actuator
		// Robot.liftActuatorSubsystem.stateTrue(); // now in autonomous

		// Autonomous Options
		autonomousChooser.addDefault("Cross Auto Line", new DummyChooserCommand(0, "LINE"));
		autonomousChooser.addObject("Station 1 to Switch", new DummyChooserCommand(1, "SWITCH"));
		autonomousChooser.addObject("Station 1 to Scale", new DummyChooserCommand(1, "SCALE"));
		autonomousChooser.addObject("Station 2 to Switch", new DummyChooserCommand(2, "SWITCH"));
		autonomousChooser.addObject("Station 3 to Switch", new DummyChooserCommand(3, "SWITCH"));
		autonomousChooser.addObject("Station 3 to Scale", new DummyChooserCommand(3, "SCALE"));
		SmartDashboard.putData("Autonomous Mode Chooser", autonomousChooser);

		m_visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);
			camera.setFPS(5);
			camera.setExposureManual(50);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat. If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		m_visionThread.setDaemon(true);
		m_visionThread.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() { RobotMap.leftEncoder.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. I You can add additional auto modes
	 * by adding additional commands to the chooser code above (like the commented
	 * example) or additional comparisons to t,he switch structure below with
	 * additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// checks which autonomous program is selected to run
		RobotMap.imu.reset();
		m_autonomousCommand = new AutoCrossLine();
//		
//		DummyChooserCommand selectedCommand = (DummyChooserCommand) autonomousChooser.getSelected();
//		String gameData = DriverStation.getInstance().getGameSpecificMessage();
//		if (gameData == null) {
//			gameData = "";
//		}
//		int retries = 100;
//		while (gameData.length() < 2 && retries > 0) {
//			retries--;
//			try {
//				Thread.sleep(5);
//			} catch (Exception e) {
//				// Just ignore interrupted exception
//			}
//			gameData = DriverStation.getInstance().getGameSpecificMessage();
//		}
//		if (gameData.length() >= 2) {
//			SmartDashboard.putString("Selector Is Working", " ");
//			
//			RobotMap.gameData = gameData;
//			RobotMap.switchGoal = new String(gameData.substring(0, 1));
//			RobotMap.scaleGoal = new String(gameData.substring(1, 2));
//			// Switch & Scale field position assignments
//			if (selectedCommand.station == 1 && selectedCommand.destination == "SCALE") {
//				m_autonomousCommand = new StationOneToScale();
//			} else if (selectedCommand.station == 1 && selectedCommand.destination == "SWITCH") {
//				m_autonomousCommand = new StationOneToSwitch();
//			} else if (selectedCommand.station == 2 && selectedCommand.destination == "SWITCH") {
//				m_autonomousCommand = new StationTwoToSwitch();
//			} else if (selectedCommand.station == 3 && selectedCommand.destination == "SWITCH") {
//				m_autonomousCommand = new StationThreeToSwitch();
//			} else if (selectedCommand.station == 3 && selectedCommand.destination == "SCALE") {
//				m_autonomousCommand = new StationThreeToScale();
//			} else {
//				m_autonomousCommand = new AutoCrossLine();
//			}
//		} else {
//			m_autonomousCommand = new AutoCrossLine();
//		}

		// schedule the autonomous command
		if (m_autonomousCommand != null) {
//			SmartDashboard.putString("auto selector command", m_autonomousCommand.getClass().getName());
//			SmartDashboard.putString("gamedata", gameData);
//			SmartDashboard.putString("switch goal", "#"+RobotMap.switchGoal+ "#");
			m_autonomousCommand.start();
		}
		else { 
			SmartDashboard.putString("Did not recieve auto", " ");
		}
	}
	

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void log() {
		driveSubsystem.log();
		liftActuatorSubsystem.log();
		liftSubsystem.log();
		intakeSubsystem.log();
		SmartDashboard.putNumber("climber motor", RobotMap.climbMotorController.get());
		SmartDashboard.putNumber("Climber controller input", Robot.m_oi.controller1.getPOV());
		SmartDashboard.putNumber("lift encoder", RobotMap.outerLiftEncoder.get());
		SmartDashboard.putNumber("outer Lift Throttle", RobotMap.outerLiftMotorController.get());
		SmartDashboard.putNumber("turn", RobotMap.leftDriveMotorController.get());
		SmartDashboard.putNumber("Gyro-Z", RobotMap.imu.getAngleZ());
		SmartDashboard.putNumber("Angle", RobotMap.imu.getAngle());
	}
}
