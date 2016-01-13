package main;

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;


public class ScannaaParkkipaikka implements Behavior {

	private OmaPilootti pilootti;
	private UltrasonicSensor sonar;
	private boolean suppressed = false;
	private EtsiParkkipaikka etsiPaikka;
	private PakitaParkkiin pakki;

	public ScannaaParkkipaikka(OmaPilootti pilootti, UltrasonicSensor sonar,
			EtsiParkkipaikka etsiPaikka, PakitaParkkiin pakki) {
		this.pilootti = pilootti;
		this.sonar = sonar;
		this.etsiPaikka = etsiPaikka;
		this.pakki = pakki;
	}

	//tutkii että onko mahdollinen parkkipaikka tarpeeksi iso autolle
	public void action() { 
		suppressed = false;
		LCD.clear();
		LCD.drawString("Scannaa paikkaa", 0, 5);

		pilootti.setSpeed(400);
		pilootti.resetTachos();
		pilootti.eteen();

		while (!suppressed) {			
			sonar.ping();
			int etaisyys = sonar.getDistance();
			LCD.drawString("etaisyys: ", 0, 6);
			LCD.drawString("    ", 0, 7);
			LCD.drawInt(etaisyys, 0, 7);
			Thread.yield();
			if (etaisyys < 20) {
				pilootti.stop();
				int matka = pilootti.matkattuMatka();

				if (matka >= 40) {
					pakki.setPing(etaisyys);
					etsiPaikka.parkkipaikkaLoytynyt(true);
					int alkupaikka = 1100 - ((matka - 40) * 50); // laskee kuinka paljon tarvitsee matkatta eteen päin ennen kuin aloitta parkeerauksen
					if (alkupaikka < 0) {
						alkupaikka = 0;
					}
					pilootti.liiku(alkupaikka);
				}
				suppress();

			}
		}
	}

	public void suppress() {
		suppressed = true;
	}

	public boolean takeControl() {
		sonar.ping();
		int distance = sonar.getDistance();
		return distance > 20 && distance != 255
				&& !etsiPaikka.getParkkipaikka();
	}

}
