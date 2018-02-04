/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4648.robot.subsystems;

import org.usfirst.frc.team4648.robot.RobotMap;
import org.usfirst.frc.team4648.robot.commands.DriveCommand;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final Talon speedController1 = RobotMap.leftDriveMotorController;
	private final Talon speedController2 = RobotMap.rightDriveMotorController;
	private final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;

	private final ADIS16448_IMU imu = RobotMap.imu;
	public DriveSubsystem() {
		addChild("Left CIM", (Talon) speedController1);
		addChild("Right CIM", (Talon) speedController2);
		addChild("IMU",imu);
		differentialDrive1.setSafetyEnabled(false);
		differentialDrive1.setExpiration(0.1);
		differentialDrive1.setMaxOutput(1.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(-forwardSpeed, rotationSpeed);
	}
	
	public void log() {
		SmartDashboard.putNumber("DB/String 0", imu.getAngle());
		SmartDashboard.putString("DB/String 1", "X:"+imu.getAccelX()+ " Y:"+imu.getAccelY()  + "Z:"+imu.getAccelZ()  );
		SmartDashboard.putString("DB/String 2", "X:"+imu.getAccelX()+ " Y:"+imu.getAccelY()  + "Z:"+imu.getAccelZ()  );
		
		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
	    SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
	    SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
	}
	
	
}
