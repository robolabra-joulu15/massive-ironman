package mielialat;
import toiminnot.AanenSoittaja;
import toiminnot.MielialojenPiirtaja;
import toiminnot.MoottorienLiikuttaja;

/*
 * Robotin mieliala kosketussensorin painamisen jälkeen.
 * 
 */

public class Vihainen extends Mieliala {
	
	public Vihainen(MielialojenPiirtaja piirtaja, AanenSoittaja soittaja, MoottorienLiikuttaja liikuttaja) {
		super(piirtaja, soittaja, liikuttaja);
	}

	public void toteutus() throws InterruptedException {
		soittaja.soitaWhine();
		piirtaja.piirraVihainen();
		Thread.sleep(800);
		
		soittaja.soitaScared();
		liikuttaja.pakita();
		Thread.sleep(1500);
		
		liikuttaja.pysahdy();
		Thread.sleep(2000); 	//mahdollista nyt sammuttaa painamalla ESCAPE+ENTER!
	}



}
