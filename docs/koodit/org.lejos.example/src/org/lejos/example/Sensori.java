package org.lejos.example;

import lejos.nxt.*;

public class Sensori {
	private UltrasonicSensor sensori;
	private int etaisyys;
	
	public Sensori(int etaisyys) {
		this.sensori = new UltrasonicSensor(SensorPort.S4);
		this.etaisyys = etaisyys;
	}
	
	public boolean laukaistaanko() {
		int[] etaisyydet = new int[10];
		int lkm = 0;
		for(int i = 0; i < 10; i++) {
			int arvo = this.sensori.getDistance();
			System.out.println(arvo);
			etaisyydet[i] = arvo;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if(arvo > this.etaisyys) {
				lkm++;
			}
		}
		if(lkm > 7) {
			return true;
		} else {
			return false;
		}
		
	}

}
