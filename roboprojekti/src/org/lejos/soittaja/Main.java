package org.lejos.soittaja;

/**
 * Main class for the guitar-playing robot.
 */
public class Main {

	public static void main(String[] args) {
			String hbd_chord = " D A A D D !G  !D<A>!D";
			String hbd_melody = "12143 12154 186432 76454";

			Laulaja laulaja = new Laulaja(hbd_melody, 50);
			Soittaja soittaja = new Soittaja(hbd_chord);

			//get tempo from sound sensor
			int tempo = Utils.getTempo();
			soittaja.setTempo(tempo);
			laulaja.setTempo(tempo);

			soittaja.start();
			laulaja.start();

	}

}
