package toiminnot;
import lejos.nxt.Motor;

/*
 * Hallitsee moottorien toimintaa.
 * 
 */

public class MoottorienLiikuttaja {
	private static final int PERUSNOPEUS = 400;
	private static final int PUOLINOPEUS = 200;
	private static final int TUPLANOPEUS = 800;

	public void pysahdy() {
		Motor.A.stop();
		Motor.B.stop();
	}
	
	public void pakita() {
		asetaNopeus(PUOLINOPEUS);
		Motor.A.forward();
		Motor.B.forward();
	}
	
	public void eteenpain() {
		asetaNopeus(PERUSNOPEUS);
		Motor.A.backward();
		Motor.B.backward();
	}
	
	/**
	 * Kääntää robottia vasemmalle tuplanopeudella.
	 * 
	 * @param ms	käännöksen kesto millisekunteina
	 * @throws InterruptedException
	 */
	public void kaannyVasemmalle(int ms) throws InterruptedException {
		Motor.A.stop();
		asetaNopeus(TUPLANOPEUS);
		Motor.B.backward();
		Thread.sleep(ms);
	}
	
	/**
	 * Kääntää robottia oikealle tuplanopeudella.
	 * 
	 * @param ms	käännöksen kesto millisekunteina
	 * @throws InterruptedException
	 */
	public void kaannyOikealle(int ms) throws InterruptedException {
		Motor.B.stop();
		asetaNopeus(TUPLANOPEUS);
		Motor.A.backward();
		Thread.sleep(ms);
	}
	
	public void ympari() {
		pysahdy();
		asetaNopeus(TUPLANOPEUS);
		Motor.A.backward();
	}
	
	/**
	 * Asettaa moottoreille nopeuden.
	 * 
	 * @param nopeus
	 */
	public void asetaNopeus(int nopeus) {
		Motor.A.setSpeed(nopeus);
		Motor.B.setSpeed(nopeus);
	}
}
