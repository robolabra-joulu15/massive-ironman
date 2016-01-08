package org.lejos.example;

import lejos.nxt.*;


public class Pallonlaukaisija {
	private NXTRegulatedMotor laukaisija;
	
	public Pallonlaukaisija() {
		this.laukaisija = new NXTRegulatedMotor(MotorPort.B);
	}

	public void laukaise() {
		this.laukaisija.setSpeed(900);
		this.laukaisija.rotate(360);
	}
}
