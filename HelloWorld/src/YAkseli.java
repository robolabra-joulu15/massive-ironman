import lejos.robotics.RegulatedMotor;

/*
	Robotin ylemmät pyörät edustavat
	käytännössä y-akselia. Moottorin
 	toimintaa määrittelevä luokka.
*/

public class YAkseli {
	private RegulatedMotor y;
	private int pituus;
	
	public YAkseli(RegulatedMotor y, int pituus) {
		this.y = y;
		this.pituus = pituus;
	}
	
	public void ylos() {
		y.rotate(pituus * -1);
	}
	
	public void alas() {
		y.rotate(pituus);
	}
	
	public void nopeus(int luku) {
		y.setSpeed(luku);
	}
}
