
public class Kahdeksan implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Kahdeksan(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		y.ylos();
		tussi.laske();
		y.ylos();
		x.oikealle();
		y.alas();
		x.vasemmalle();
		y.alas();
		x.oikealle();
		y.ylos();
		tussi.nosta();
		y.alas();
		
		x.seuraava();
	}

}
