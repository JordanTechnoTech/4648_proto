package org.usfirst.frc.team4648.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4648.robot.RobotMap;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private long grabFrameResponse = 0L;

	public CameraSubsystem() {
		super();
		addChild(camera);
		// Get a CvSink. This will capture Mats from the camera
		CvSink cvSink = CameraServer.getInstance().getVideo();
		mat = new Mat();
	}

	public void updateMat() {
		grabFrameResponse = cvSink.grabFrame(mat);
	}

	public void outputImageFromCamera() {
		// Puts frame into source
		if (grabFrameResponse != 0) {
			Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
			outputStream.putFrame(mat);
		} else {
			outputStream.notifyError(cvSink.getError());
		}
	}

	public void processImage() {
		// Puts frame into source
		if (grabFrameResponse != 0) {
			Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
		} else {
			outputStream.notifyError(cvSink.getError());
		}
	}
    //test
	private AxisCamera camera = RobotMap.camera;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}
