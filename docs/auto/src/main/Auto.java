package main;

import lejos.nxt.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Auto {

	public static void main(String[] args) {
		// auton pysäytys
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonReleased(Button b) {
				System.exit(0);
			}

			public void buttonPressed(Button b) {
				LCD.clear();
				LCD.drawString("Auto Sammuu", 3, 5);
			}
		});

		UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
		OmaPilootti pilootti = new OmaPilootti();

		EtsiParkkipaikka etsiPaikka = new EtsiParkkipaikka(pilootti);
		LahdeParkista lahde = new LahdeParkista(pilootti, etsiPaikka);
		PakitaParkkiin pakki = new PakitaParkkiin(pilootti, etsiPaikka, lahde);

		Behavior b1 = etsiPaikka;
		Behavior b2 = new ScannaaParkkipaikka(pilootti, sonar, etsiPaikka,
				pakki);
		Behavior b3 = pakki;
		Behavior b4 = lahde;
		Behavior b5 = new Tormays();

		Behavior[] bArray = { b1, b2, b3, b4, b5 };
		Arbitrator parkkeeraus = new Arbitrator(bArray);
		parkkeeraus.start();
		

	}
}
