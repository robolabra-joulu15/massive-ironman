package org.lejos.example;

import lejos.nxt.*;


public class Maalivahti {
	private NXTRegulatedMotor maalivahti;
	
	public Maalivahti () {
		this.maalivahti = new NXTRegulatedMotor(MotorPort.A);
	}
	
	public void start() {
	
			maalivahti.setSpeed(200);
			maalivahti.rotate(180);

	}
}
