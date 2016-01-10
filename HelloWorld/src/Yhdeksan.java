
public class Yhdeksan implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Yhdeksan(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		x.oikealle();
		y.ylos();
		tussi.laske();
		x.vasemmalle();
		y.ylos();
		x.oikealle();
		y.alas();
		y.alas();
		x.vasemmalle();
		tussi.nosta();
		x.oikealle();
		
		x.seuraava();
	}

}
