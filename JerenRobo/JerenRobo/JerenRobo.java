import lejos.nxt.*;
import lejos.util.Delay;
import lejos.nxt.SensorPort;

public class JerenRobo {
	public static void main(String[] args) {

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button button) {
				System.exit(0);
			}

			public void buttonReleased(Button button) {
			}
		});

		System.out.println("oletko valmis?");
		System.out.println("paina -->");
		Button.RIGHT.waitForPressAndRelease();
		LCD.clear();

		LightSensor sensor = new LightSensor(SensorPort.S1);

		int vari;

		Motor.A.setSpeed(45);
		Motor.B.setSpeed(90);

		while (true) {
			Motor.A.rotate(-45);
			vari = sensor.getNormalizedLightValue(); // valosensori mittaa
														// pallon värin
			naytaVari(vari);
			Motor.A.rotate(-45);
			Delay.msDelay(1000); // ohjelma odottaa sekunnin
		}
	}

	public static void naytaVari(int vari) {
		if (vari <= 330) {
			tumma();
		}

		if (vari > 460) {
			vaalea();
		}

		if (vari > 330 && vari <= 460) {
			eiPalloa();
		}
	}

	public static void vaalea() {
		Motor.B.rotateTo(-90);
		Delay.msDelay(2000);
		alkuun();
	}

	public static void tumma() {
		Motor.B.rotateTo(90);
		Delay.msDelay(2000);
		alkuun();
	}

	public static void eiPalloa() {
		LCD.drawString("Ei palloa", 1, 1);
		LCD.drawString("paina enter", 1, 2);
		Motor.B.rotateTo(180);
		Button.waitForPress();
		LCD.clear();
		alkuun();
	}

	public static void alkuun() {
		Motor.B.rotateTo(0);
	}
}