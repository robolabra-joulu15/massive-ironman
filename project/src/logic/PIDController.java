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
		float kp = 10;
		//Integral constant
		float ki = 1;
		//Derivative constant
		float kd = 100;
		
		//motors
		NXTRegulatedMotor leftMotor = this.config.getLeftMotor();
		NXTRegulatedMotor rightMotor = this.config.getRightMotor();
		
		//create other variables
		int lightValue;
		float error, turn, powerLeft, powerRight;
		float lastError = 0, integral = 0, derivative = 0;
		
		this.light.setFloodlight(true);
		
		while(!Button.ESCAPE.isPressed()) {
			lightValue = this.light.readValue();
			
			//calculate error value
			error = lightValue - offset;
			
			//add error to integral
			integral += error;
			
			//calculate derivative
			derivative = error - lastError;
			
			//calculate turn value
			turn = kp * error + ki * integral + kd * derivative;
			
			//calculate motor powers
			powerLeft = targetPower - turn;
			powerRight = targetPower + turn;
			
			//set new motor speeds
			if (powerLeft > 0) {
				leftMotor.setSpeed(powerLeft);
				leftMotor.forward();
			}else {
				leftMotor.setSpeed(-powerLeft);
				leftMotor.backward();
			}
			
			if (powerRight > 0) {
				rightMotor.setSpeed(powerRight);
				rightMotor.forward();
			}else {
				rightMotor.setSpeed(-powerRight);
				rightMotor.backward();
			}
			
			lastError = error;
		}
		
		leftMotor.stop();
		rightMotor.stop();
		this.light.setFloodlight(false);
	}
	
}
