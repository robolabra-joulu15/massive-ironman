package org.lejos.example;

import lejos.nxt.*;


public class Maalivahti {
	private NXTRegulatedMotor maalivahti;
	
	public Maalivahti () {
		this.maalivahti = new NXTRegulatedMotor(MotorPort.A);
	}
	
	public void start() {
		while(!Button.ENTER.isPressed()) {
			maalivahti.setSpeed(35);
			maalivahti.rotate(360);
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
