package org.lejos.example;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class KaikuluotainKontrolli {
	
	private UltrasonicSensor sensori = new UltrasonicSensor(SensorPort.S2);
	private MoottoriKontrolli konehuone;
	
	public KaikuluotainKontrolli(MoottoriKontrolli koneet) {
		konehuone = koneet;
		//ping-mode
		sensori.setMode(1);
	}
	
	public int haeEtaisyys(int moodi) {
		//k‰ytet‰‰n yht‰ mittausta
		if (moodi == 1) {
			sensori.setMode(1);
			konehuone.odota(60);
			sensori.ping();
			return sensori.getDistance();
		}
		//kolme mittausta
		int[] apu = new int[3];
		for (int i = 0; i < 3; i++) {
			sensori.setMode(1);
			konehuone.odota(60);
			sensori.ping();
			apu[i]=sensori.getDistance();
		}
		//palautetaan mediaani havainnoista
		if (moodi == 2) {
			if ( (apu[0] <= apu[1] && apu[0] > apu[2]) || (apu[0] >= apu[1] && apu[0] < apu[2])) {
				return apu[0];
			} else if ( (apu[1] <= apu[0] && apu[1] >= apu[2]) || (apu[1] >= apu[0] && apu[1] <= apu[2])) {
				return apu[1];
			}
			return apu[2];
		} else {
			//palautetaan pienin havainto
			return Math.min(apu[0],Math.min(apu[1],apu[2]));
		}
	}
	
	public void pysayta() {
		sensori.off();	
	}
}
