package mielialat;
import toiminnot.AanenSoittaja;
import toiminnot.MielialojenPiirtaja;
import toiminnot.MoottorienLiikuttaja;
import lejos.util.Delay;

/*
 * Robotin mieliala.
 * 
 */

public class Iloinen extends Mieliala {
	
	public Iloinen(MielialojenPiirtaja piirtaja, AanenSoittaja soittaja, MoottorienLiikuttaja liikuttaja) {
		super(piirtaja, soittaja, liikuttaja);
	}
	/**
	 * Soittaa woof-äänen, jonka jälkeen halutun pituinen viive.
	 * 
	 * @param ms	viiveen kesto millisekunteina
	 */
	private void woofTauolla(int ms) {
		soittaja.soitaWoof();
		Delay.msDelay(ms);
	}

	public void toteutus() {  // ympäri ja haukkuu
		piirtaja.piirraIloinen();
		liikuttaja.pysahdy();
		woofTauolla(600);
		
		liikuttaja.ympari();
		
		woofTauolla(400);
		woofTauolla(700);
		woofTauolla(500);
		woofTauolla(500);
		
		piirtaja.clear();
		piirtaja.piirraOk();
		
	}
	



}
