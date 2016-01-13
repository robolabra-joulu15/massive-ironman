package main;

// etsii mahdollista parkki paikkaa

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;

public class EtsiParkkipaikka implements Behavior {

	private OmaPilootti pilootti;
	private boolean parkkipaikka = false;
	private boolean suppressed = false;

	public EtsiParkkipaikka(OmaPilootti pilootti) {
		this.pilootti = pilootti;
	}

	public boolean takeControl() {
		return !parkkipaikka;
	}

	public void suppress() {
		suppressed = true;
	}
	//Menee eteen päin kunnes toinen behavior ottaa komennon
	public void action() {
		suppressed = false;
		LCD.clear();
		LCD.drawString("EtsiParkkipaikka", 0, 5);

		pilootti.setSpeed(600);
		pilootti.eteen();

		while (!suppressed) {
			Thread.yield();
		}
		pilootti.stop();
	}

	public void parkkipaikkaLoytynyt(boolean arvo) {
		this.parkkipaikka = arvo;
	}

	public boolean getParkkipaikka() {
		return parkkipaikka;
	}
}
