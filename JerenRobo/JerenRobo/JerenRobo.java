import lejos.nxt.*;
import lejos.util.Delay;
import lejos.nxt.SensorPort;

public class JerenRobo {
	static int mustanVariArvo;
	static int valkoisenVariArvo;

	public static void main(String[] args) {

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button button) {
				System.exit(0);
			}

			public void buttonReleased(Button button) {
			}
		});

		Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
		kayttoliittyma.kaynnista();

	}
}