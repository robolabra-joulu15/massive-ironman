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
		LightSensor light = new LightSensor(SensorPort.S1);
		PIDController pid = new PIDController(config, light);
		
		//DifferentialPilot pilot = new DifferentialPilot(5.6f, 12.0f, Motor.A, Motor.B);
		
		while(true) {
			if (!configUI.start()) break;
			LCD.clear();
			
			pid.start();
		}
		
		Sound.beep();
		
	}
	
}
