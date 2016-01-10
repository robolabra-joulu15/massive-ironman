import lejos.robotics.RegulatedMotor;

/*
 	Robotin alemmat pyörät edustavat
 	käytännössä x-akselia. Moottorin
 	toimintaa määrittelevä luokka.
*/

public class XAkseli {
	private RegulatedMotor x;
	private int leveys;
	
	public XAkseli(RegulatedMotor x, int leveys) {
		this.x = x;
		this.leveys = leveys;
	}
	
	public void oikealle() {
		x.rotate(leveys * -1);
	}
	
	public void vasemmalle() {
		x.rotate(leveys);
	}
	
	public void seuraava() {
		x.rotate(-80);
	}
	
	public void nopeus(int luku) {
		x.setSpeed(luku);
	}
	
	public void siirryPoisTielta() {
		x.rotate(leveys * -4);
	}
}
