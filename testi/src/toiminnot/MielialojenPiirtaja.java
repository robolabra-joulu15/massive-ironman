package toiminnot;
import javax.microedition.lcdui.Graphics;

/*
 * Luokkaa käytetään mielialojen graafiseen esittämiseen.
 * 
 */

public class MielialojenPiirtaja {
	private Graphics graf;
	
	public MielialojenPiirtaja(Graphics graf) {
		this.graf = graf;
	}
	
	public void clear() {
		graf.clear();
	}
	
	public void piirraIloinen() {
		clear();
		graf.fillRect(10, 20, 10, 40);
		graf.fillRect(20, 20, 10, 10);
		graf.fillRect(30, 20, 10, 40);

		graf.fillRect(60, 20, 10, 40);
		graf.fillRect(70, 20, 10, 10);
		graf.fillRect(80, 20, 10, 40);
	}
	
	public void piirraOk() {
		clear();
		graf.fillArc(10, 10, 25, 50, 90, 360);
		graf.fillArc(60, 10, 25, 50, 90, 360);
	}
	
	public void piirraVihainen() {
		clear();
		graf.fillRect(3, 5, 10, 9);
		graf.fillRect(10, 10, 10, 9);
		graf.fillRect(17, 15, 10, 9);
		graf.fillRect(24, 20, 10, 9);
		graf.fillRect(31, 25, 10, 9);
		graf.fillRect(35, 30, 10, 9);
		graf.fillRect(31, 35, 10, 9);
		graf.fillRect(24, 40, 10, 9);
		graf.fillRect(17, 45, 10, 9);
		graf.fillRect(10, 50, 10, 9);
		graf.fillRect(3, 55, 10, 9);
		
		graf.fillRect(17, 35, 10, 20); // vasen pupilli
		graf.fillRect(71, 35, 10, 20); // oikea pupilli
	
		graf.fillRect(85, 5, 10, 9);
		graf.fillRect(78, 10, 10, 9);
		graf.fillRect(71, 15, 10, 9);
		graf.fillRect(64, 20, 10, 9);
		graf.fillRect(57, 25, 10, 9);
		graf.fillRect(53, 30, 10, 9);
		graf.fillRect(57, 35, 10, 9);
		graf.fillRect(64, 40, 10, 9);
		graf.fillRect(71, 45, 10, 9);
		graf.fillRect(78, 50, 10, 9);
		graf.fillRect(85, 55, 10, 9);	
	}
	
}
