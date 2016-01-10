package mielialat;
import toiminnot.AanenSoittaja;
import toiminnot.MielialojenPiirtaja;
import toiminnot.MoottorienLiikuttaja;

/*
 * Pohjana mielialoille. Tämän luokan ansiosta uusien mielialojen luominen on helppoa.
 * 
 */

public abstract class Mieliala {
	protected MielialojenPiirtaja piirtaja;
	protected AanenSoittaja soittaja;
	protected MoottorienLiikuttaja liikuttaja;
	
	public Mieliala(MielialojenPiirtaja piirtaja, AanenSoittaja soittaja, MoottorienLiikuttaja liikuttaja) {
		this.piirtaja = piirtaja;
		this.soittaja = soittaja;
		this.liikuttaja = liikuttaja;
	}
	
	abstract void toteutus() throws InterruptedException;
	
}
