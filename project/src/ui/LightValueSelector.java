package ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightValueSelector {
	
	private LightSensor light;
	private String description;
	
	public LightValueSelector(String description) {
		this.light = new LightSensor(SensorPort.S1, true);
		this.description = description;
	}
	
	public int select() {
		LCD.drawString("Press enter key", 1, 1);
		LCD.drawString("to select light", 1, 2);
		LCD.drawString("value for", 4, 3);
		LCD.drawString("\"" + this.description + "\"", 2, 5);
		Button.ENTER.waitForPress();
		
		return this.light.readValue();	
	}
	
}
