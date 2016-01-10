package org.lejos.example;

import lejos.nxt.*;

/**
 * Luokka Pallonlaukaisija.
 * @author janikakaariainen
 */
public class Pallonlaukaisija {
	private NXTRegulatedMotor laukaisija;
	
        /**
         * Asetetaan pallonlaukaisijan moottori porttiin B.
         */
	public Pallonlaukaisija() {
		this.laukaisija = new NXTRegulatedMotor(MotorPort.B);
	}

        /**
         * Metodi luo piippausäänen ja laukaisee pallon.
         */
	public void laukaise() {
		Sound.beep();
		this.laukaisija.setSpeed(900);
		this.laukaisija.rotate(360);
		
	}
}
