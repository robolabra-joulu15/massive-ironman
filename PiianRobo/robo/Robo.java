package robo;

import lejos.nxt.*;
//import String;

public class Robo {
	
	
	public static void main(String[] args) {
		
		Button.ESCAPE.addButtonListener(new EscapeKuuntelija());

		int aSpeed = 30;
		int bSpeed = 70;
		int cSpeed = 70;
		
		int aAngle = 160;
		int bAngle = 180;
		int cAngle = 180;
		
		Motor.A.setSpeed(aSpeed);
		Motor.B.setSpeed(bSpeed);
		Motor.C.setSpeed(cSpeed);
		
		// OHJELMA OLETTAA, ETTÄ OLET ITSE SÄÄTÄNYT ROBOTIN ALKUASENTOON!
		// ALKUASENTO ON ASENTO, JOSTA WHILE-LOOP LÄHTEE.
		
		
		
		
		
		// ALOITUS
		System.out.println("Paina ENTER");
		
		Button.ENTER.waitForPressAndRelease(); // odottaa, että entteriä painetaan 
		
		// pyörittää luuppia (HUOM: escape-nappia kuunnellaan koko ajan!)
		while(true) { 
			
			// alas
			Motor.B.rotate(-1 * bAngle);
			
			// kiinni
			Motor.A.rotate(-1 * aAngle);
			
			// ylös
			Motor.B.rotate(bAngle);
			
			// vasemmalle
			Motor.C.rotate(cAngle);
			
			// alas
			Motor.B.rotate(-1 * bAngle);
			
			// auki
			Motor.A.rotate(aAngle);
			
			// ylös
			Motor.B.rotate(bAngle);
			
			// oikealle
			Motor.C.rotate(-1 * cAngle);
			
					
		}
		
	}

}
