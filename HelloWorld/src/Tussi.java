import lejos.robotics.RegulatedMotor;

/*
 	Tussia kontrolloivan 
 	moottorin käyttäytyminen.
*/

public class Tussi {
	private RegulatedMotor tussi;
	
	public Tussi(RegulatedMotor tussi) {
		this.tussi = tussi;
	}
	
	public void laske() {
		tussi.rotate(-60);
	}
	
	public void nosta() {
		tussi.rotate(60);
	}
	
	public void nopeus(int luku) {
		tussi.setSpeed(luku);
	}
}
