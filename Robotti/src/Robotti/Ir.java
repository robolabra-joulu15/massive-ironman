package Robotti;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Ir {
	private Brick brick;
	private Port port;
	private SensorModes sensor;
	private SampleProvider distance;
	private float[] sample;
	
	
	public Ir(){
		brick = BrickFinder.getDefault();
		port = brick.getPort("S1");
		sensor = new EV3IRSensor(port);
		distance = sensor.getMode("Distance");
		sample = new float[distance.sampleSize()];
	}
	
	public int Sample(){
		distance.fetchSample(sample, 0);
		
		return (int)sample[0];
	}

}
