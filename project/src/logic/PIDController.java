package logic;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import util.Configuration;

public class PIDController {
	
	private Configuration config;
	private LightSensor light;
	
	public PIDController(Configuration config, LightSensor light) {
	    this.config = config;
	    this.light = light;
    }
	
	public void start() {
		//the average of background and line light values
		int offset = (this.config.getBackgroundColor() - this.config.getLineColor()) / 2;
		//target power level (the speed of both motors when going straight forward)
		float targetPower = this.config.getMovementSpeed();
		//Proportional constant
		float kp = targetPower / (this.config.getBackgroundColor() - offset);
		
		//motors
		NXTRegulatedMotor leftMotor = this.config.getLeftMotor();
		NXTRegulatedMotor rightMotor = this.config.getRightMotor();
		
		//create other variables
		int lightValue;
		float error, turn, powerLeft, powerRight;
		
		this.light.setFloodlight(true);
		leftMotor.forward();
		rightMotor.forward();
		
		while(!Button.ESCAPE.isPressed()) {
			lightValue = this.light.readValue();
			
			//calculate error value
			error = lightValue - offset;
			//calculate turn value
			turn = kp * error;
			
			//calculate motor powers
			powerLeft = targetPower + turn;
			powerRight = targetPower - turn;
			
			leftMotor.setSpeed(powerLeft);
			rightMotor.setSpeed(powerRight);
		}
		
		this.light.setFloodlight(false);
	}
	
}
