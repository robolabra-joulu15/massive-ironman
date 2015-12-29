package logic;

import lejos.nxt.LightSensor;
import util.Configuration;

public class PIDController {
	
	private Configuration config;
	private LightSensor light;
	
	public PIDController(Configuration config, LightSensor light) {
	    this.config = config;
	    this.light = light;
    }
	
}
