package main;

import java.io.File;

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;

public class Tormays implements Behavior {
	
	private TouchSensor sensori;
	
	public Tormays() {
		this.sensori = new TouchSensor(SensorPort.S4);
	}

	//sanoo perkele jos takapuskuri menee sisään ;-), 
	//tällä hetkellä ei toimi lahdeparkista ja 
	//pakitaparkkiin kanssa (behaviorit kovakoodut)
	public void action() { 
		
		Motor.B.stop(true);
		Motor.C.stop();
		Sound.setVolume(80);
		Sound.playSample(new File("eihyva.wav"));
		LCD.clear();
		LCD.drawString("PERKELE", 5, 5);
		LCD.drawString("paina nappullaa", 0, 6);
		LCD.drawString("jatkaakseksi", 0, 7);
		Button.waitForPress();
	}

	
	public void suppress() {
	}

	
	public boolean takeControl() {
		return sensori.isPressed();
	}

}
