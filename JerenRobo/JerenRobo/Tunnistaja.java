import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.LightSensor;
import lejos.util.Delay;

public class Tunnistaja {

	int valkoisenVariArvo;
	int mustanVariArvo;
	LightSensor sensori;
	int vari;
	Kalibroija kalibroija;

	public Tunnistaja(LightSensor sensor) {
		this.sensori = sensor;
		this.kalibroija = new Kalibroija(sensor);
	}

	public void kaynnista() {
		while (true) {
			Motor.A.rotate(-45);
			vari = sensori.getNormalizedLightValue();
			naytaVari(vari);
			Motor.A.rotate(-45);
			Delay.msDelay(1000);
		}
	}

	public void naytaVari(int vari) {
		if (vari <= mustanVariArvo + 40 && vari > mustanVariArvo - 40) {
			musta();
		}
		if (vari > valkoisenVariArvo - 20){
			valkoinen();
		}
		else{
			eiPalloa();
		}
	}

	public void valkoinen() {
		Motor.B.rotateTo(-90);
		Delay.msDelay(1000);
		alkuun();
	}
	public void musta() {
		Motor.B.rotateTo(90);
		Delay.msDelay(1000);
		alkuun();
	}
	public void eiPalloa() {
		LCD.drawString("Ei palloa", 1, 1);
		LCD.drawString("paina enter", 1, 2);
		Motor.B.rotateTo(180);
		Button.waitForPress();
		LCD.clear();
		alkuun();
	}
	public void alkuun() {
		Motor.B.rotateTo(0);
	}

	public void kalibroi() {
		System.out.println("kalibroidaan");
		valkoisenVariArvo = kalibroija.kalibroiValkoinen();
		mustanVariArvo = kalibroija.kalibroiMusta();

	}

}
