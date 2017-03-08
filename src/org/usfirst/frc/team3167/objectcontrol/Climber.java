package org.usfirst.frc.team3167.objectcontrol;

import org.usfirst.frc.team3167.robot.RobotConfiguration;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {
	private final Jaguar climberMotor;
	private final Joystick stick1, stick2; 
	
	RobotConfiguration robotConfig = new RobotConfiguration(); 
	
	public Climber(Joystick stick1, Joystick stick2, int climberMotorChanel) {
		climberMotor = new Jaguar(climberMotorChanel);
		this.stick1 = stick1;
		this.stick2 = stick2; 
	}
	
	//modify motor speeds
	public void slowSpin() {
		climberMotor.set(-robotConfig.climberSlowSpeed);
	}
	public void slowSpinReverse() {
		climberMotor.set(robotConfig.climberSlowSpeed);
	}
	public void mediumSpin() {
		climberMotor.set(-robotConfig.climberMediumSpeed);
	}
	public void mediumSpinReverse() {
		climberMotor.set(robotConfig.climberMediumSpeed);
	}
	public void fullSpin() {
		climberMotor.set(-robotConfig.climberTopSpeed);
	}
	public void fullSpinReverse() {
		climberMotor.set(robotConfig.climberTopSpeed);
	}
	public void haltMotor() {
		climberMotor.set(0.0);
	}
	
	//accessors
	public double getSlowSpeed() {
		return robotConfig.climberSlowSpeed;
	}
	public double getMediumSpeed() {
		return robotConfig.climberMediumSpeed;
	}
	public double getFullSpeed() {
		return robotConfig.climberTopSpeed;
	}
	
	public void operate() {
		String msg = "";
    	if(stick1.getRawButton(1) || stick2.getRawButton(1)) {
    		slowSpin();
    		msg = "Slow spin";
    	}
    	//BUTTON TWO NOW USED FOR SLIDE LOCK
    	/*else if(stick.getRawButton(2) || stick2.getRawButton(2)) {
    		slowSpinReverse();
    		msg = "Slow spin (R)";
    	}*/
    	else if(stick1.getRawButton(5) || stick2.getRawButton(5)) {
    		mediumSpin();
    		msg = "Medium spin";
    	}
    	else if(stick1.getRawButton(3) || stick2.getRawButton(3)) {
    		mediumSpinReverse();
    		msg = "Medium spin (R)";
    	}
    	else if(stick1.getRawButton(6) || stick2.getRawButton(6)) {
    		fullSpin();
    		msg = "Full spin";
    	}
    	else if(stick1.getRawButton(4) || stick2.getRawButton(4)) {
    		fullSpinReverse();
    		msg = "Full spin (R)";
    	} else {
    		haltMotor();
    		msg = "Halted";
    	}
    	SmartDashboard.putString("Climber function: ", msg);	
	}
}