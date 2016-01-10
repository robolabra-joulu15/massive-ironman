package org.lejos.example;

import lejos.nxt.*;

/**
 * Luokka Maalivahti.
 * @author janikakaariainen
 */
public class Maalivahti {
	private NXTRegulatedMotor maalivahti;
	
        /**
         * Maalivahtia liikutettava moottori asetetaan porttiin A.
         */
	public Maalivahti () {
		this.maalivahti = new NXTRegulatedMotor(MotorPort.A);
	}
	
        /**
         * Metodi liikuttaa maalivahtia puolelta toiselle.
         */
	public void start() {
			maalivahti.setSpeed(200);
			maalivahti.rotate(180);
	}
}
