
public class Viisi implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Viisi(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		y.ylos();
		y.ylos();
		x.oikealle();
		tussi.laske();
		x.vasemmalle();
		y.alas();
		x.oikealle();
		y.alas();
		x.vasemmalle();
		tussi.nosta();
		x.oikealle();
		
		x.seuraava();
	}

}
