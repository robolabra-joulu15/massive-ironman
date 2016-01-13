package main;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class OmaPilootti {

	private NXTRegulatedMotor ohjaus = Motor.A;
	private NXTRegulatedMotor vasen = Motor.B;
	private NXTRegulatedMotor oikea = Motor.C;
	private int vasemmalle;
	private int oikealle;
	private int keskelle;

	public OmaPilootti() {
		ohjaus.setSpeed(200);
		calibrateSteering();
		vasen.setSpeed(500);
		oikea.setSpeed(500);
		vasen.setAcceleration(3000);
		oikea.setAcceleration(3000);
	}

	public void taakse() {
		vasen.forward();
		oikea.forward();
	}

	public void eteen() {
		vasen.backward();
		oikea.backward();
	}

	public void liiku(int paljonko) {
		if (paljonko > 0) {
			vasen.rotate(-paljonko, true);
			oikea.rotate(-paljonko);
		} else {
			vasen.rotate(Math.abs(paljonko), true);
			oikea.rotate(Math.abs(paljonko));
		}

	}

	public void kaanny(String suunta, int paljonko) {
		if (suunta.equals("oikea")) {
			ohjaus.rotateTo(oikealle);
		} else if (suunta.equals("vasen")) {
			ohjaus.rotateTo(vasemmalle);
		}
		liiku(paljonko);
		// palauttaa eturenkaat takaisin keskelle, voi joutua säättämään näitä
		// arvoja riippuen akkujen varaustasosta
		if (suunta.equals("oikea")) {
			ohjaus.rotateTo(keskelle);
		} else if (suunta.equals("vasen")) {
			ohjaus.rotateTo(keskelle - 15);
		}
		ohjaus.waitComplete();

	}

	public void stop() {
		oikea.stop(true);
		vasen.stop();

	}

	public void setSpeed(int speed) {
		oikea.setSpeed(speed);
		vasen.setSpeed(speed);
	}

	public void resetTachos() {
		oikea.resetTachoCount();
		vasen.resetTachoCount();
	}

	public int getTachos() {
		int t1 = oikea.getTachoCount();
		int t2 = vasen.getTachoCount();
		return t1 + t2 / 2;
	}

	// palauttaa pyöreän arvion matkatusta matkasta centimetreinä
	public int matkattuMatka() {
		int tachos = Math.abs(getTachos());
		return tachos / 155;
	}

	// Metodi kopioitu SteerinPilotilta, kalibroi ohjauksen
	public void calibrateSteering() {

		LCD.drawString("Calibration", 0, 5);

		ohjaus.setSpeed(100);
		ohjaus.setStallThreshold(10, 100);

		ohjaus.forward();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int l = ohjaus.getTachoCount();

		ohjaus.backward();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int r = ohjaus.getTachoCount();

		int keskelle = (l + r) / 2;

		r -= keskelle;
		l -= keskelle;
		oikealle = r;
		vasemmalle = l;

		ohjaus.rotateTo(keskelle);
		ohjaus.resetTachoCount();
		ohjaus.setStallThreshold(50, 1000);
		LCD.clear();
	}
}
