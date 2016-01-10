
public class Yksi implements Numero{
	private XAkseli x;
	private YAkseli y;
	private Tussi tussi;

	public Yksi(XAkseli x, YAkseli y, Tussi tussi) {
		this.x = x;
		this.y = y;
		this.tussi = tussi;
	}
	
	public void piirra() {
		x.oikealle();
		y.ylos();
		y.ylos();
		tussi.laske();
		y.alas();
		y.alas();
		tussi.nosta();
		
		x.seuraava();
	}

}
