
public class Nolla implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Nolla(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		tussi.laske();
		x.oikealle();
		y.ylos();
		y.ylos();
		x.vasemmalle();
		y.alas();
		y.alas();
		tussi.nosta();
		x.oikealle();
		
		x.seuraava();
	}
}
