package org.lejos.example;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class OhjausKontrolli {

	private MoottoriKontrolli konehuone;
	private KaikuluotainKontrolli sonar;
	private KompassiKontrolli kompassi;
	private TouchSensor kosketus = new TouchSensor(SensorPort.S4);
	private int[] tilaMarkov = new int[2];

	public OhjausKontrolli() {
		konehuone = new MoottoriKontrolli();
		sonar = new KaikuluotainKontrolli(konehuone);
		kompassi = new KompassiKontrolli(konehuone);
		tilaMarkov[0]=1;
		tilaMarkov[1]=1; //nykyinen toteutus ei hyödynnä tietoa edellisestä tilasta
	}

	public void aja() {
		LCD.setAutoRefresh(true);
		LCD.clear();
		LCD.drawString("Tila:aloitus", 1, 1);
		konehuone.odota(1000);
		
		while(!Button.ESCAPE.isPressed()) { //ajo-luuppi
			int dist = sonar.haeEtaisyys(1);
			if (dist < 10) {
				konehuone.peruuta(500);
			}
			if (tilaMarkov[1] == 1) { //käytävä
				if (kosketus.isPressed()) {
					konehuone.peruuta(500);
				}
				int apu = haeKaytavanSuunta();
				if (apu > 0) {
					liikuEteen(apu);
				}
			} else if (tilaMarkov[1] == 2) { //kulma
				if (kosketus.isPressed()) {
					konehuone.peruuta(500);
				}
				int apu = haeKulmanSuunta();
				liikuEteen(apu);
			} //else if (tilaMarkov[1] == 3) { //risteys, mahdolliset muut laajennukset
		}
		
		//lopetus
		konehuone.pysayta();
		sonar.pysayta();
		LCD.clear();
		LCD.drawString("Valmis!",0,0);
		konehuone.odota(1000);
	}
	
	private void liikuEteen(int apu) { //metodi, joka korjaa liikkumisen aikana tapahtuneen vahinkokänntymisen
		int alkuSuunta, suunta = 0;
		alkuSuunta = kompassi.haeSuunta(2);
		konehuone.eteen(Math.min(40*apu, 2000));
		suunta = kompassi.haeSuunta(2);
		if (Math.abs(suunta - alkuSuunta) > 25 && Math.abs(suunta - alkuSuunta) < 335) { //robotti on vahinkokääntynyt matkalla, eli luultavasti törmännyt seinään
			konehuone.peruuta(500);
			//tarkistetaan kummalle puolelle robo on kääntynyt ja käännetään vähän yli takaisin, ettei toivottavasti jää uudelleen samaan kohtaan
			if (suunta > alkuSuunta && alkuSuunta <= 350 || suunta < 180 && alkuSuunta > 350) { //kääntynyt luultavasti oikealle
				if (alkuSuunta > 10) {
					kaannaSuuntaan(alkuSuunta - 10);
				} else {
					kaannaSuuntaan(360 + alkuSuunta - 10);
				}
			} else {
				if (alkuSuunta < 350) {
					kaannaSuuntaan(alkuSuunta + 10);
				} else {
					kaannaSuuntaan(alkuSuunta - 350);
				}
			}
		}
	}
	

	private void kaannaSuuntaan(int kohdesuunta) {
		//metodi robotin kääntämiseksi annettuun kompassisuuntaan
		while (!Button.ESCAPE.isPressed()) {
			int suunta = kompassi.haeSuunta(2);
			int suuntaVirhe = suunta-kohdesuunta;
			if (Math.abs(suuntaVirhe) < 7) {
				break;
			}
			if ( (suuntaVirhe < 0 && suuntaVirhe > -180) || suuntaVirhe > 180) {
				konehuone.kaannaOik(5*Math.abs(suuntaVirhe));
			} else {
				konehuone.kaannaVas(5*Math.abs(suuntaVirhe));
			}
		}
		if (Button.ESCAPE.isPressed()) {
			System.exit(0);
		}
	}

	private int haeKaytavanSuunta() {
		//metodi käytävällä suunnistamiseen
		tilaMarkov[0]=tilaMarkov[1];
		int alkuSuunta = kompassi.haeSuunta(2);
		int suurinSuunta = alkuSuunta;
		int suurin = sonar.haeEtaisyys(2);
		for (int k = 0; k < 2; k++) {
			if (suurin < 220) {//jatketaan, jos pääsee suoraan eteen, muuten etsitään sivuilta
				for (int i = 0; i < 2; i++) {
					if (Button.ESCAPE.isPressed()) {
						System.exit(0);
					}
					LCD.clear();
					LCD.drawString("suurin:"+suurin, 1, 1);
					LCD.drawString("suunta:"+suurinSuunta, 1, 2);
					//konehuone.odota(200);
					if (k == 0) {
						konehuone.kaannaOik(200);
					} else {
						konehuone.kaannaVas(200);
					}
					if (kosketus.isPressed()) {
						konehuone.peruuta(500);
					}
					int apu = sonar.haeEtaisyys(2);
					if (apu > suurin) {
						suurin = apu;
						suurinSuunta = kompassi.haeSuunta(2);
					}
				}
				kaannaSuuntaan(alkuSuunta);
			}
		}
		if (suurin > 30) {
			kaannaSuuntaan(suurinSuunta);				
		} else { //jos edessä ei ole tilaa niin ollaan kulmassa
			kaannaSuuntaan(alkuSuunta);
			tilaMarkov[1]=2;
			suurin = 0;
		}		
		return suurin;
	}
	

	private int haeKulmanSuunta() {
		//metodi kulmassa suunnistamiseen
		tilaMarkov[0]=tilaMarkov[1];
		LCD.asyncRefresh();
		LCD.clear();
		LCD.drawString("kulma", 0, 0);
		int alkuSuunta = kompassi.haeSuunta(2);
		int[] suurinSuunta = new int[2];
		int[] suurin = new int[3];
		suurin[0] = sonar.haeEtaisyys(2);
		for (int i = 0; i < 2; i++) { //etsitään suurin vapaa etäisyys käymällä molemmat sivut läpi
			while (!Button.ESCAPE.isPressed()) {
				if (i == 0) {
					konehuone.kaannaVas(250);
				} else {
					konehuone.kaannaOik(250);
				}
				if (kosketus.isPressed()) {
					konehuone.peruuta(500);
				}
				suurin[i+1] = sonar.haeEtaisyys(2);
				int suunta = kompassi.haeSuunta(2);
				if (suurin[i+1] > suurin[i]) {
					suurin[i]=suurin[i+1];
					suurinSuunta[i]= suunta;
				}
				//lopetetaan ennen tulosuuntaan kääntymistä
				if (Math.abs(suunta - alkuSuunta) > 110 && Math.abs(suunta - alkuSuunta) < 250) {
					if (i == 0) {
						kaannaSuuntaan(alkuSuunta);
					}
					break;
				}
			}
		}
		tilaMarkov[1]=1;
		//jos löydetään riittävästi tilaa, käännetään suurinta löydettyä kohti ja palautetaan löydetty etäisyys
		if (suurin[0] > suurin[1] && suurin[0] > 40) {
			kaannaSuuntaan(suurinSuunta[0]);
			return suurin[0];
		} else if (suurin[1] > 40) {
			kaannaSuuntaan(suurinSuunta[1]);
			return suurin[1];
		}
		//umpikujassa käännytään ympäri
		kaannaSuuntaan(alkuSuunta +180 % 360);
		return sonar.haeEtaisyys(2);
	}
}
