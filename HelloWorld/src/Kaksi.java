
public class Kaksi implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Kaksi(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		y.ylos();
		y.ylos();
		tussi.laske();
		x.oikealle();
		y.alas();
		x.vasemmalle();
		y.alas();
		x.oikealle();
		tussi.nosta();
		
		x.seuraava();
	}

}
