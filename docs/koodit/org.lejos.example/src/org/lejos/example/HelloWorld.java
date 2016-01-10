package org.lejos.example;

import lejos.nxt.*;

/**
 * Example leJOS Project with an ant build file
 *
 */
public class HelloWorld {

	public static void main(String[] args) {
		Maalivahti maalivahti = new Maalivahti();
		Pallonlaukaisija pallonlaukaisija = new Pallonlaukaisija();
		Sensori sensori = new Sensori(25);
		Siirtaja siirtaja = new Siirtaja(sensori, pallonlaukaisija);
		while(!Button.ENTER.isPressed()) {
			siirtaja.start();
			maalivahti.start();
		}

	}
}
