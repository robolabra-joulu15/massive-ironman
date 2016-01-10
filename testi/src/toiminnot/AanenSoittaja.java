package toiminnot;
import java.io.File;

import lejos.nxt.Sound;

/*
 * Luokkaa käytetään äänileikkeiden soittamiseen.
 * 
 */

public class AanenSoittaja {
	private File woof;
	private File whine;
	private File scared;
	
	public AanenSoittaja() {
		this.woof = new File("brk.wav");
		this.whine = new File("whi.wav");
		this.scared = new File("pup.wav");
	}
	
	public void soitaWoof() {
		soitaLeike(100, woof);
	}
	
	public void soitaWhine() {
		soitaLeike(80, whine);
	}
	
	public void soitaScared() {
		soitaLeike(80, scared);
	}
	
	/**
	 * Soittaa äänileikkeen halutulla äänenvoimakkuudella.
	 * 
	 * @param volume
	 * @param leike
	 */
	public void soitaLeike(int volume, File leike) {
		Sound.setVolume(volume);
		Sound.playSample(leike);
	}

}
