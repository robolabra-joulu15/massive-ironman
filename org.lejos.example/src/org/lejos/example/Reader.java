package org.lejos.example;

import lejos.nxt.LightSensor;

//luokka hoitaa viivan lukemisen ja auttaa pilottia kulkemaan
public class Reader {
	
	private LightSensor light;
	
	public Reader(LightSensor ls) {
		this.light = ls;
	}
	
	//sensorin arvon getteri
	public int getValue() {
		return this.light.getLightValue();
	}
	
    
}
