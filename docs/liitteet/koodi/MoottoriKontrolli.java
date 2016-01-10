package org.lejos.example;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.robotics.DCMotor;

public class MoottoriKontrolli {
	private DCMotor rEng = new NXTMotor(MotorPort.getInstance(0));
	private DCMotor lEng = new NXTMotor(MotorPort.getInstance(1));
	
	public MoottoriKontrolli() {
		lEng.setPower(40);
		rEng.setPower(40);
		lEng.stop();
		rEng.stop();
	}
	
	public MoottoriKontrolli(int teho) {
		if (teho > 100) {
			teho = 100;
		}
		lEng.setPower(teho);
		rEng.setPower(teho);
		lEng.stop();
		rEng.stop();
	}
	
	public void eteen(int aika) {
		lEng.forward();
		rEng.forward();
		if (aika > 0) {
			odota(aika);
			lEng.stop();
			rEng.stop();
		}
	}
	
	public void kaannaOik(int aika) {
		lEng.forward();
		rEng.backward();
		if (aika > 0) {
			odota(aika);
			lEng.stop();
			rEng.stop();
		}
	}
	
	public void kaannaVas(int aika) {
		lEng.backward();
		rEng.forward();
		if (aika > 0) {
			odota(aika);
			lEng.stop();
			rEng.stop();
		}
	}
	
	public void peruuta(int aika) {
		lEng.backward();
		rEng.backward();
		if (aika > 0) {
			odota(aika);
			lEng.stop();
			rEng.stop();
		}
	}
	
	public void pysayta() {
		rEng.stop();
		lEng.stop();
	}
	
	public void odota (int aika) {
		try {
			Thread.sleep((long)aika);
		} catch (Exception e) {
			System.out.println("Keskeytetty!");
			System.exit(1);
		}
	}
}
