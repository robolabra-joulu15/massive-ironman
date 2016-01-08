package org.lejos.robo;

import lejos.nxt.*;


public class Main {

	public static void main(String[] args) {
		Maalivahti maalivahti = new Maalivahti();
		Pallonlaukaisija pallonlaukaisija = new Pallonlaukaisija();
		Sensori sensori = new Sensori(pallonlaukaisija);
		Siirtaja siirtaja = new Siirtaja(sensori);
		maalivahti.start();
		siirtaja.start();
		Button.waitForPress();
	}
}
