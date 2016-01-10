package Robotti;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Colour {
	private Brick brick;
	private Port port;
	private SensorModes sensor;
	private SampleProvider colour;
	private float[] sample;
	
	public Colour(){
		brick = BrickFinder.getDefault();
		port = brick.getPort("S2");
		sensor = new EV3ColorSensor(port);
		colour = sensor.getMode("ColorID");
		sample = new float[colour.sampleSize()];
	}
	
	public int Sample(){
		colour.fetchSample(sample, 0);
		return (int)sample[0];
	}
	

}
