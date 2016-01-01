package logic;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTMotor;
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
		int offset = (this.config.getBackgroundColor() + this.config.getLineColor()) / 2;
		//target power level (the speed of both motors when going straight forward)
		int targetPower = this.config.getMovementSpeed();
		//Proportional constant
		double kp = this.config.getPID_kp();
		//Integral constant
		double ki = this.config.getPID_ki();
		//Derivative constant
		double kd = this.config.getPID_kd();
		
		//motors
		NXTMotor leftMotor = new NXTMotor(this.config.getLeftMotorPort());
		NXTMotor rightMotor = new NXTMotor(this.config.getRightMotorPort());
		
		//create other variables
		int lightValue;
		double error, turn, powerLeft, powerRight;
		double lastError = 0, integral = 0, derivative = 0;
		
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
				leftMotor.setPower((int)powerLeft);
				leftMotor.forward();
			}else {
				leftMotor.setPower(-(int)powerLeft);
				leftMotor.backward();
			}
			
			if (powerRight > 0) {
				rightMotor.setPower((int)powerRight);
				rightMotor.forward();
			}else {
				rightMotor.setPower(-(int)powerRight);
				rightMotor.backward();
			}
			
			lastError = error;
		}
		
		leftMotor.stop();
		rightMotor.stop();
		this.light.setFloodlight(false);
	}
	
}
