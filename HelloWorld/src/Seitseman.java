
public class Seitseman implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Seitseman(XAkseli x, YAkseli y, Tussi tussi) {
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
		y.alas();
		tussi.nosta();
		
		x.seuraava();
	}

}
