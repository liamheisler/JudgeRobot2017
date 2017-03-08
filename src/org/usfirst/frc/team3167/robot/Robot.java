
package org.usfirst.frc.team3167.robot;

import org.usfirst.frc.team3167.autonomous.Autonomous;
import org.usfirst.frc.team3167.drive.SimpleMecanumDrive;
import org.usfirst.frc.team3167.objectcontrol.Climber;
import org.usfirst.frc.team3167.objectcontrol.GearHanger;
import org.usfirst.frc.team3167.visionstreaming.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {   
    private Joystick stick1, stick2;
    private Talon motor1, motor2, motor3, motor4; 
    
    /*private final HardNumTestDrive drive 
    	= new HardNumTestDrive(1, 2, 3, 4, 10, 11, 12, 13
    			, 14, 15, 16, 17); //incorrect positions */
    private SimpleMecanumDrive drive;
    private boolean slideLocked = false; 
    
    /*private EncodingType encodingType;
    private Encoder encoder1;
    private Encoder encoder2;
    private Encoder encoder3;
    private Encoder encoder4; */
   
    private Climber climber;
    private GearHanger gearHanger;
    
    private double autoSpeed;
    private double driveTime;
    private Autonomous auto;
    
    private Vision vision; 
    private String cameraLocation; 
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	stick1 = new Joystick(1); 
    	stick2 = new Joystick(2); 
    	motor1 = new Talon(1);
    	motor2 = new Talon(2); 
    	motor3 = new Talon(3);
    	motor4 = new Talon(4); 
    	
    	//drive = new HardNumTestDrive(1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 17); 
    	drive = new SimpleMecanumDrive(motor1, motor2, motor3, motor4);
    	slideLocked = false;
    	
    	/*encodingType = EncodingType.k4X; 
    	encoder1 = new Encoder(10, 11, false, encodingType);
    	encoder2 = new Encoder(12, 13, false, encodingType);
    	encoder3 = new Encoder(14, 15, false, encodingType); 
    	encoder4 = new Encoder(15, 16, false, encodingType); */
    	
    	climber = new Climber(stick1, stick2, 5); 
    	gearHanger = new GearHanger(stick1, stick2, 6, 1, 0); 
    	
    	autoSpeed = 0.75;
    	driveTime = 3.0;
    	auto = new Autonomous(drive, autoSpeed, driveTime); 
    	
    	vision = new Vision();
    	cameraLocation = "cam0";
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
    	auto.run();   	
    }
    
    public void teleopInit() {
    	vision.enable(cameraLocation);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {  
    	if(stick1.getRawButton(2))
    		slideLocked = true; 
    	else
    		slideLocked = false; 
    	   	
    	//handle all aspects of the robot
    	drive.Drive(-stick1.getX(), stick1.getY(), -stick1.getTwist(), slideLocked);
    	climber.operate();
    	gearHanger.hangGear(0.7);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void disabledInit() {
    	System.out.println("The robot is ready to rock and roll!");
    }
    
}
