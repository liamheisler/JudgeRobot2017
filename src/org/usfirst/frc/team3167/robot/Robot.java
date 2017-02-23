
package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    private final Joystick stick = new Joystick(1);
    /*private final Talon motor1 = new Talon(1); 
    private final Talon motor2 = new Talon(2); 
    private final Talon motor3 = new Talon(3); 
    private final Talon motor4 = new Talon(4);*/
    
    private final Jaguar climberMotor = new Jaguar(5);
    private final Jaguar gearMotor = new Jaguar(6);
    private final RobotDrive drive = new RobotDrive(1, 2, 3, 4); //DET. POSITIONS 
    
    //corresponds to motor numbers
    private final Encoder encoder1 = new Encoder(10, 11, false, EncodingType.k4X);
    private final Encoder encoder2 = new Encoder(12, 13, false, EncodingType.k4X);
    private final Encoder encoder3 = new Encoder(14, 15, false, EncodingType.k4X); 
    private final Encoder encoder4 = new Encoder(15, 16, false, EncodingType.k4X); 
    
    private Climber climber = new Climber(5); 
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
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
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("encoder1: ", encoder1.getRate());
    	SmartDashboard.putNumber("encoder2: ", encoder2.getRate());
    	SmartDashboard.putNumber("encoder3: ", encoder3.getRate());
    	SmartDashboard.putNumber("encoder4: ", encoder4.getRate());
    	
    	//feed in hard numbers to determine encoder functionality
    	drive.mecanumDrive_Cartesian(0.6, 0.6, 0.6, 0);
    	
    	//handle climber (with multiple speeds)
    	if(stick.getRawButton(1))
    		climber.slowSpin();
    	else if(stick.getRawButton(2))
    		climber.slowSpinReverse();
    	else if(stick.getRawButton(5))
    		climber.mediumSpin();
    	else if(stick.getRawButton(3))
    		climber.mediumSpinReverse();
    	else if(stick.getRawButton(6))
    		climber.fullSpin();
    	else if(stick.getRawButton(4))
    		climber.fullSpinReverse();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
