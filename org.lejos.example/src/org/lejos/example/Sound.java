package org.lejos.example;

import lejos.nxt.SoundSensor;

//luokka hoitaa äänen kuulostelun
public class Sound { 
	
	private SoundSensor sound;
	private boolean status = false; //status ollaanko liikkeessä
	
	public Sound(SoundSensor s) {
		this.sound = s;
	}
	
	//omaa debuggia varten äänen arvo soutattavaksi NXT palikkaan
	public int getNoiseInt() {
		return this.sound.readValue();
	}
	
	//tarkistaa onko kuulunut ääntä ja päivittää sen mukaan statuksen
	public void checkNoise() { 
		int value = sound.readValue();
		if(value >38) {
			if(status) {  //asetetaan status false jos aikaisemmin true ja true jos false.
				status = false;
			} else {
				status = true;
			}
		}
	} 
	//statuksen getter
	public boolean getStatus() {
		return status;
	}
}
