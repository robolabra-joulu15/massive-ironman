package main;

//pakittaa auton parkkiin, valiten kahdesta eri tavasta riippuen parkkipaikan koosta

import lejos.nxt.*;
import lejos.robotics.subsumption.Behavior;

public class PakitaParkkiin implements Behavior {

	private OmaPilootti pilootti;
	private EtsiParkkipaikka etsiPaikka;
	private int etaisyysSeinasta;
	private LahdeParkista lahde;

	public PakitaParkkiin(OmaPilootti pilootti,
			EtsiParkkipaikka etsiParkkipaikka, LahdeParkista lahde) {
		this.pilootti = pilootti;
		this.etsiPaikka = etsiParkkipaikka;
		this.lahde = lahde;

	}

	public void setPing(int ping) {
		this.etaisyysSeinasta = ping;
	}

	// kova koodattu parkeeraus, parempi ratkaisu oli sensori pohjainen,
	// silloin tormays behaviori toimisi täälläkin
	public void action() {
		LCD.clear();
		LCD.drawString("pakita parkkiin", 0, 5);

		pilootti.setSpeed(400);
		pilootti.kaanny("oikea", -2500);
		if (etaisyysSeinasta < 11) { // jos auto on lähellä seinää kun lähtee parkkeeraamaan
			pilootti.liiku(-400);
			pilootti.kaanny("vasen", -2500);
			pilootti.liiku(300);
		} else { // jos auto on kauempana seinästä niin tekee sig-sag liikettä
					// jotta olisi kunnolla parkissa
			pilootti.liiku(-(75 * etaisyysSeinasta));
			pilootti.kaanny("vasen", -1500);
			pilootti.kaanny("oikea", 500);
			pilootti.kaanny("vasen", -500);
			pilootti.kaanny("oikea", -500);
			pilootti.kaanny("vasen", -500);
			pilootti.kaanny("oikea", 500);
			pilootti.kaanny("vasen", 500);
			pilootti.liiku(-400);
		}
		lahde.setParkissa(true);

	}

	public void suppress() {
	}

	public boolean takeControl() {
		return etsiPaikka.getParkkipaikka() && !lahde.getParkissa();
	}
}
