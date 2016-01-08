package org.lejos.example;

import lejos.nxt.*;


public class Sensori {
	private UltrasonicSensor sensori;
	private Pallonlaukaisija pallonlaukaisija;
	
	public Sensori(Pallonlaukaisija pallonlaukaisija) {
		this.sensori = new UltasonicSensor(SensorPort.S4);
		this.pallonlaukaisija = pallonlaukaisija;
	}
	
	public void start() {
		while(!Button.ENTER.isPressed()) {
			distance = this.sensori.getDistance();
			if(distance < 20) {
				this.pallonlaukaisija.shoot();
			}
			try {
				Thread.sleep()
			}
		}
	}

}
