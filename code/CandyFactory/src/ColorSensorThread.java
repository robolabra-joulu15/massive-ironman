import java.util.HashMap;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;



class ColorSensorThread extends Thread
{
	private RegulatedMotor arm;
	private RegulatedMotor belt;
	private static EV3ColorSensor colorSensor;
	private SortAction sorter;

	ColorSensorThread(RegulatedMotor m2, RegulatedMotor m)
	{
		this.arm = m2;
		this.belt = m;
		colorSensor = new EV3ColorSensor(SensorPort.S1);
		this.sorter = new SortAction(arm, belt);

	}

	public void run()
	{
		colorSensor.setCurrentMode("ColorID");
		colorSensor.setFloodlight(Color.WHITE);
		float[] sample = new float[10];

		while (true)
		{

			colorSensor.fetchSample(sample, 0);				
			int c = colorSensor.getColorID();				
			boolean isValidColor = sorter.run(c);
			if (isValidColor) {
				updateDisplay();
			}

		}

	}

	public HashMap<String, Integer> getColorStatistics() {
		return this.sorter.getColorCount();
	}

	public void updateDisplay() {
		int rivi = 0;
		GraphicsLCD g = LocalEV3.get().getGraphicsLCD();
		g.clear();
		HashMap<String,Integer> colours = this.getColorStatistics();
		for (String s:colours.keySet()) {
			g.drawString(s + " " + colours.get(s), 0, 20*rivi, 0);
			rivi++;
		}

	}


}
