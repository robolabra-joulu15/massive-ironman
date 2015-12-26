package logic;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LCDUpdater implements SensorPortListener {

	private LightSensor light;
	
	public LCDUpdater(LightSensor light) {
		this.light = light;
    }
	
	@Override
    public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		LCD.clear();
		LCD.drawInt(this.light.readValue(), 1, 1);
    }

}
