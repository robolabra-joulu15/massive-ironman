package logic;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
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
		int targetPower = this.config.getMovementSpeed();
		//Proportional constant
		int kp = targetPower / (this.config.getBackgroundColor() - offset);
		
		this.light.setFloodlight(true);
		
		while(!Button.ESCAPE.isPressed()) {
			int lightValue = this.light.readValue();
		}
		
		this.light.setFloodlight(false);
	}
	
}
