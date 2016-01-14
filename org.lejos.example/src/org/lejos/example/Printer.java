package org.lejos.example;

import lejos.nxt.*;

public class Printer {

	private int maxV;
	private final int xDPS = 90; // x, y and z motors degrees per second.
	private final int yDPS = 90; // Configure depending on build.
	private final int PlanterDPS = 90;

	private NXTRegulatedMotor xMotor;
	private NXTRegulatedMotor yMotor;
	private NXTRegulatedMotor Planter;

	public Printer(int maxValue, NXTRegulatedMotor AMotor,
			NXTRegulatedMotor BMotor, NXTRegulatedMotor CMotor) {
		this.maxV = maxValue;
		xMotor = AMotor;
		yMotor = BMotor;
		Planter = CMotor;
		xMotor.setSpeed(xDPS);
		yMotor.setSpeed(yDPS);
		Planter.setSpeed(PlanterDPS);
	}

	private void movePlatform(NXTRegulatedMotor motor, int amount)
			throws InterruptedException {
		if (amount < 0) { // if amount is negative, platform is moved backwards
			motor.backward();
			amount = -amount;
		} else {
			motor.forward();
		}
		for (int i = 0; i <= amount; i++) { //Wait that platform is moved or escape is pressed 
			Thread.sleep(1000);
			System.out.println(i);
			if (Button.ESCAPE.isPressed()) { // If escape is pressed, quit program

				throw new InterruptedException();
			}
		}
		motor.stop();

	}

	private void moveX(int amount) throws InterruptedException {
		movePlatform(xMotor, amount);
	}

	private void moveY(int amount) throws InterruptedException {
		movePlatform(yMotor, amount);
	}

	private void setPlanterDownAndUp() throws InterruptedException{
		Planter.backward(); // 
		Thread.sleep(1000);//
		Planter.forward();
		Thread.sleep(1000);
		Planter.stop();
	}

	public void PickUpAndPlantBlock(int x, int y) throws InterruptedException {
		if (x > maxV) // X cant be bigger than maxValue
			x = maxV;
		if (y > maxV)
			y = maxV;
		setPlanterDownAndUp(); // First take block from magazine
		moveX(-x-2); // Magazine takes room so pictures (0,0) must be two blocks from planters starting point
		moveY(-y);	//Coordinates are negative, input is positive.;
		setPlanterDownAndUp();
		moveY(y);
		moveX(x+2);

	}

}
