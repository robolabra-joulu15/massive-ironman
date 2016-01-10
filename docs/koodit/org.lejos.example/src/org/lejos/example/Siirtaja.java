package org.lejos.example;

import lejos.nxt.*;


public class Siirtaja {
	private NXTRegulatedMotor siirtaja;
	private Sensori sensori;
	private Pallonlaukaisija laukaisija;
	
	public Siirtaja(Sensori sensori, Pallonlaukaisija pallonlaukaisija) {
		this.siirtaja = new NXTRegulatedMotor(MotorPort.C);
		this.sensori = sensori;
		this.laukaisija = pallonlaukaisija;
	}
	
	public void start() {
		this.siirtaja.setSpeed(130);
		this.siirtaja.rotate(-55);
		if(this.sensori.laukaistaanko()) {
			this.siirtaja.rotate(10);
			this.laukaisija.laukaise();
			this.siirtaja.rotate(45);
		} else {
			this.siirtaja.rotate(55);
		}
	}

}