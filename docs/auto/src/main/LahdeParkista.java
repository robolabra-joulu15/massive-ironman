package main;

//lahtee pois parkista, valiten kahdesta eri tavasta riippuen parkkipaikan koosta

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;

public class LahdeParkista implements Behavior {

	private boolean parkissa = false;
	private OmaPilootti pilootti;
	private EtsiParkkipaikka etsiPaikka;

	public LahdeParkista(OmaPilootti pilootti, EtsiParkkipaikka paikka) {
		this.pilootti = pilootti;
		this.etsiPaikka = paikka;
	}
	//kovakoodattu lähtö parkkipaikasta, parempi olisi sensoripohjainen, 
	//tormays behaviori toimisi silloin täälläkin
	public void action() { 
		Thread.yield();
		LCD.clear();
		System.out.print("Paina nappulaa,\nniin lahdetaan\nparkista");
		Button.waitForPress();
		LCD.clear();
		LCD.drawString("Lahde Parkista", 0, 5);
		pilootti.setSpeed(400);
		pilootti.kaanny("oikea", -400);
		pilootti.kaanny("vasen", 400);
		pilootti.kaanny("oikea", -500);
		pilootti.kaanny("vasen", 1600);
		pilootti.liiku(900);
		pilootti.kaanny("oikea", 2300);
		etsiPaikka.parkkipaikkaLoytynyt(false);
		parkissa = false;

	}

	public void suppress() {
	}

	public boolean takeControl() {
		return parkissa;
	}

	public void setParkissa(boolean arvo) {
		parkissa = arvo;
	}
	public boolean getParkissa() {
		return parkissa;
	}

}
