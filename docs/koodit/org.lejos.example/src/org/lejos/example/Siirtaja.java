package org.lejos.example;

import lejos.nxt.*;

/**
 * Luokka siirtaja liikuttaa pallonlaukaisijaa sivulta sivulle.
 * @author janikakaariainen
 */
public class Siirtaja {
	private NXTRegulatedMotor siirtaja;
	private Sensori sensori;
	private Pallonlaukaisija laukaisija;
	
        /**
         * Konstruktorissa saadaan ultraäänisensori ja pallonlaukaisija.
         * @param sensori
         * @param pallonlaukaisija 
         */
	public Siirtaja(Sensori sensori, Pallonlaukaisija pallonlaukaisija) {
		this.siirtaja = new NXTRegulatedMotor(MotorPort.C);
		this.sensori = sensori;
		this.laukaisija = pallonlaukaisija;
	}
	
        /**
         * Metodi liikuttaa pallonlaukaisijaa oikealle, ja kysyy ultraääni-
         * sensorilta, pitäisikö pallo laukaista. Jos laukaistaan, niin 
         * liikutetaan pallonlaukaisijaa hieman vasemmalle, laukaistaan pallo ja
         * palautetaan se alkuasemaan. Jos palloa ei laukaista, viedään pallon-
         * laukaisija suoraan alkuasemaan.
         */
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