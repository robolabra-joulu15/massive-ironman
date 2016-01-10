package org.lejos.example;

public class RoboProjekti {
	
	private OhjausKontrolli ohjaus;
	
	public static void main(String[] args) {
		(new RoboProjekti()).run();
	}

	public void run() {
		ohjaus = new OhjausKontrolli();
		ohjaus.aja();
		System.exit(0);
	}
}