package org.lejos.example;

import lejos.nxt.*;

/**
 * Luokka Sensori ohjaa ultraäänisensoria.
 * @author janikakaariainen
 */
public class Sensori {
	private UltrasonicSensor sensori;
	private int etaisyys;
	
        /**
         * Konstruktorissa alustetaan etaisyys-muuttujan arvo ja asetetaan
         * ultraäänisensori porttiin S4. 
         * @param etaisyys 
         */
	public Sensori(int etaisyys) {
		this.sensori = new UltrasonicSensor(SensorPort.S4);
		this.etaisyys = etaisyys;
	}
	
        /**
         * Metodi tutkii, halutaanko laukaista pallo. Metodi kysyy ultraääni-
         * sensorilta 10 etäisyyttä, ja vertaa niitä etaisyys-muuttujaan.
         * @return true jos metodi arvelee ettei maalivahti ole edessä,
         * muuten false.
         */
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
