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
		this.siirtaja.setSpeed(80);
		while(!Button.ENTER.isPressed()) {
			siirra();
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	private void siirra() {
		tutkiVasen();
		tutkiOikea();
		tutkiVasen();
	}
	
	private void tutkiOikea() {
		this.siirtaja.rotate(50);
		if(this.sensori.laukaistaanko()) {
			this.siirtaja.rotate(-10);
			this.laukaisija.laukaise();
			this.siirtaja.rotate(-40);
		} else {
			this.siirtaja.rotate(-50);
		}
	}
	
	private void tutkiVasen() {
		if(this.sensori.laukaistaanko()) {
			this.siirtaja.rotate(-20);
			this.laukaisija.laukaise();
			this.siirtaja.rotate(20);
		}
	}
	
}