package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.Jaguar;

public class Climber {
	private Jaguar motor;
	
	RobotConfiguration robotConfig = new RobotConfiguration(); 
	
	public Climber(int motorPort) {
		motor = new Jaguar(motorPort); 
	}
	
	public void slowSpin() {
		motor.set(-robotConfig.climberSlowSpeed);
	}
	public void slowSpinReverse() {
		motor.set(robotConfig.climberSlowSpeed);
	}
	public void mediumSpin() {
		motor.set(-robotConfig.climberMediumSpeed);
	}
	public void mediumSpinReverse() {
		motor.set(robotConfig.climberMediumSpeed);
	}
	public void fullSpin() {
		motor.set(-robotConfig.climberTopSpeed);
	}
	public void fullSpinReverse() {
		motor.set(robotConfig.climberTopSpeed);
	}
	
	public double getSlowSpeed() {
		return robotConfig.climberSlowSpeed;
	}
	public double getMediumSpeed() {
		return robotConfig.climberMediumSpeed;
	}
	public double getFullSpeed() {
		return robotConfig.climberTopSpeed;
	}
}
