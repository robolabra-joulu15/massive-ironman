package ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightValueSelector {
	
	private LightSensor light;
	private String description;
	
	public LightValueSelector(String description) {
		this.light = new LightSensor(SensorPort.S1, false);
		this.description = description;
	}
	
	public int select() {
		this.light.setFloodlight(true);
		LCD.drawString("Press any key", 2, 1);
		LCD.drawString("to select light", 1, 2);
		LCD.drawString("value for", 4, 3);
		LCD.drawString("\"" + this.description + "\"", (16-this.description.length())/2, 5);
		Button.waitForPress();
		
		this.light.setFloodlight(false);
		return this.light.readValue();	
	}
	
}
