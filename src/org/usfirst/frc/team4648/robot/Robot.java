/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot;

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

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4648.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team4648.robot.subsystems.ClawSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.LiftActuatorSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team4648.robot.subsystems.GearShiftSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	public static DriveSubsystem driveSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static ClawSubsystem clawSubsystem;
	public static LiftActuatorSubsystem liftActuatorSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static OI m_oi;
	public static GearShiftSubsystem gearShiftSubsystem;
	public static ClimberSubsystem climberSubsystem;

	Thread m_visionThread;
	Command m_autonomousCommand;

	Command driveCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		driveSubsystem = new DriveSubsystem();
		liftSubsystem = new LiftSubsystem();
		clawSubsystem = new ClawSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		liftActuatorSubsystem =  new LiftActuatorSubsystem();
		m_oi = new OI();
		gearShiftSubsystem = new GearShiftSubsystem();
		climberSubsystem = new ClimberSubsystem();
		
		// puts the robot into first gear upon startup
		Robot.gearShiftSubsystem.gearShiftOne();
		
		// Autonomous Versions
		autoChooser.addDefault("Default Program", new AutonomousCommandGroup());
		SmartDashboard.putData("Autonomous Mode Selection", autoChooser);
		
//		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", autoChooser);
		
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
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
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
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		// Switch & Lever field positions
		String gameData = new String(DriverStation.getInstance().getGameSpecificMessage());
		String ourSwitch = gameData.substring(0, 0);
		String Scale = gameData.substring(1, 1);
		String opositionSwitich = gameData.substring(2, 2);

		// checks which autonomous program is selected to run
		m_autonomousCommand = autoChooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
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
	}
}
