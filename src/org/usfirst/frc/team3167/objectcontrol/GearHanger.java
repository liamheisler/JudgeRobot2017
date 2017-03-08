package org.usfirst.frc.team3167.objectcontrol;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearHanger {
	
	private Jaguar gearMotor;
	private Joystick stick1, stick2; 
	private DigitalInput limitSwitchHigh, limitSwitchLow; 
	private boolean checkSwitchPosition;
	
	public GearHanger(Joystick stick1, Joystick stick2, int motorPort, int limitHighPort, int limitLowPort) {
		this.stick1 = stick1;
		this.stick2 = stick2;
		gearMotor = new Jaguar(motorPort); 		
		limitSwitchHigh = new DigitalInput(limitHighPort); 
		limitSwitchLow = new DigitalInput(limitLowPort); 
		checkSwitchPosition = false; 
	}
	
	public void hangGear(double hookSpeed) {
		double liftSpeed = -hookSpeed;
		double lowerSpeed = hookSpeed; 
		double stop = 0.0; 
		
		int forwardPos = 0;
		int reversePos = 180; 
		
		//used for diagnostic 	
		String hookMsg = "";
		String switchMsg = "";
		String povMsg = ""; 
		
		if(stick1.getPOV() == forwardPos || stick2.getPOV() == forwardPos) {
			gearMotor.set(liftSpeed);
			hookMsg = "Lifting hook";
			povMsg = "Forward";
			checkSwitchPosition = false; 
			
			if(!limitSwitchHigh.get()) {
				gearMotor.set(stop);
				switchMsg = "High pressed";
				
				if(stick1.getPOV() == reversePos || stick2.getPOV() == reversePos) {
					gearMotor.set(lowerSpeed);
					hookMsg = "Lowering hook";
					checkSwitchPosition = true; 
					povMsg = "Reverse";
				}
			}
		}
		else if(stick1.getPOV() == reversePos || stick2.getPOV() == reversePos) {
			gearMotor.set(lowerSpeed);
			hookMsg = "Lowering hook";
			povMsg = "Reverse";
			checkSwitchPosition = true; 
			
			if(!limitSwitchLow.get()) {
				gearMotor.set(stop);
				switchMsg = "Low pressed";
				
				if(stick1.getPOV() == forwardPos || stick2.getPOV() == forwardPos) {
					gearMotor.set(liftSpeed);
					hookMsg = "Lifting hook";
					povMsg = "Forward";
					checkSwitchPosition = false; 
				}
			}
		} else {
			gearMotor.set(stop);
			hookMsg = "Neutral position";
			povMsg = "Centered";
		}
		
		SmartDashboard.putString("Gear function: ", hookMsg);
		SmartDashboard.putString("Microswitch Function: ", switchMsg);
		SmartDashboard.putString("POV Position: ", povMsg);
		SmartDashboard.putBoolean("gearDown ", checkSwitchPosition);
	}

}