
public class Nelja implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Nelja(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		y.ylos();
		y.ylos();
		tussi.laske();
		y.alas();
		x.oikealle();
		y.ylos();
		y.alas();
		y.alas();		
		tussi.nosta();		
				
		x.seuraava();
	}
}
