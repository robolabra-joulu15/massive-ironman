
public class Kolme implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Kolme(XAkseli x, YAkseli y, Tussi tussi) {
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
		x.oikealle();
		y.alas();
		x.vasemmalle();		
		tussi.nosta();		
		x.oikealle();
		
		x.seuraava();
	}
}
