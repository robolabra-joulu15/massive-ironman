package org.lejos.example;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassSensor;

public class KompassiKontrolli {
	
	private CompassSensor kompassi;
	private MoottoriKontrolli konehuone;
	
	public KompassiKontrolli(MoottoriKontrolli koneet) {
		kompassi = new CompassSensor(SensorPort.S1);
		konehuone = koneet;
	}
	
	public int haeSuunta(int moodi) {
		if (moodi == 1) { //yksittäinen mittaus
			return Math.round(kompassi.getDegrees()) % 360;
		} else { //palautetaan parhaimman oloinen kolmesta lukemasta
			int[] apu = new int[3];
			for (int i = 0; i < 3; i++) {
				konehuone.odota(50);
				apu[i] = Math.round(kompassi.getDegrees()) % 360;
			}
			//jos oikea suunta näyttäisi olevan lähellä 0/360, mediaani vie luultavasti metsään -> palautetaan satunnainen mittaus
			if ((apu[0]+apu[1]+apu[2] > 680 && apu[0]>340 || apu[1]>340 || apu[2]>340) || (apu[0]+apu[1]+apu[2] < 400 && (apu[0]>340 || apu[1]>340 || apu[2]>340))) {
				return apu[1];
			}
			if ((apu[0] <= apu[1] && apu[0] > apu[2]) || (apu[0] >= apu[1] && apu[0] < apu[2])) {
				return apu[0];
			} else if ( (apu[1] <= apu[0] && apu[1] >= apu[2]) || (apu[1] >= apu[0] && apu[1] <= apu[2])) {
				return apu[1];
			}
			return apu[2];
		}
	}
}
