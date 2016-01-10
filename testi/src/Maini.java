import lejos.nxt.Sound;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import mielialat.*;
import toiminnot.*;
import javax.microedition.lcdui.Graphics;

public class Maini {
	
	public static void main(String[] args) throws Exception{
		
		TouchSensor tatsi = new TouchSensor(SensorPort.S1);
		UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
		SoundSensor aaniV = new SoundSensor(SensorPort.S3, true); 

		Graphics graf = new Graphics();
		MoottorienLiikuttaja liikuttaja = new MoottorienLiikuttaja();
		MielialojenPiirtaja piirtaja = new MielialojenPiirtaja(graf);
		AanenSoittaja soittaja = new AanenSoittaja();
		Iloinen ilo = new Iloinen(piirtaja, soittaja, liikuttaja);
		Vihainen viha = new Vihainen(piirtaja, soittaja, liikuttaja);
		
		ultra.continuous();
		
		while (true) {
			piirtaja.piirraOk();
			
			while (!tatsi.isPressed()) {
				liikuttaja.eteenpain();
				
				if (ultra.getDistance() < 50) {				
					liikuttaja.kaannyVasemmalle(700);
				}
				if (aaniV.readValue() > 50) {
					liikuttaja.pysahdy();
					ilo.toteutus();
				}	
			}
			
			viha.toteutus();
		}
	}
}