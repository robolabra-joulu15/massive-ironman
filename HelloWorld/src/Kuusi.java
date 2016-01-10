
public class Kuusi implements Numero {
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Kuusi(XAkseli x, YAkseli y, Tussi tussi) {
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
		y.alas();		
		x.oikealle();		
		y.ylos();
		x.vasemmalle();	
		tussi.nosta();		
		y.alas();		
		x.oikealle();
		
		x.seuraava();
	}
}
