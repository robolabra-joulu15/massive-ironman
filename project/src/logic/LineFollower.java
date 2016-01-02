package logic;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import ui.ConfiguratorUI;
import util.Configuration;

public class LineFollower {

	public void start() {
		
		Configuration config = new Configuration();
		ConfiguratorUI configUI = new ConfiguratorUI(config);
		LightSensor light = new LightSensor(SensorPort.S1, false);
		PIDController pid = new PIDController(config, light);
		
		while(true) {
			if (!configUI.start()) break;
			LCD.clear();
			
			pid.start();
		}
		
		Sound.beep();
		
	}
	
}
