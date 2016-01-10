import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.util.Delay;

/*	
  	Pääohjelma piirtorobotille, joka piirtää
	satunnaisesti generoituja numeroita tussilla
	digitaalisessa muodossa.
*/

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<Numero> numerot = new ArrayList<Numero>();
		
		RegulatedMotor xAkseli = Motor.A;			
		RegulatedMotor yAkseli = Motor.B;		
		RegulatedMotor tussimoottori = Motor.C;
		
		
		//toinen parametri määrittää piirrettyjen numeroiden koon, 
		//XAkselilla leveys- ja YAkselilla pituussuunnassa.	
		
		YAkseli y = new YAkseli(yAkseli, 130);
		XAkseli x = new XAkseli(xAkseli, 100);
		Tussi tussi = new Tussi(tussimoottori);
		
		//moottorin nopeus
		tussi.nopeus(40);
		x.nopeus(80);
		y.nopeus(130);
		
		
		//lista satunnaisgeneraatiota varten
		
		numerot.add(new Nolla(x, y, tussi));
		numerot.add(new Yksi(x, y, tussi));
		numerot.add(new Kaksi(x, y, tussi));
		numerot.add(new Kolme(x, y, tussi));
		numerot.add(new Nelja(x, y, tussi));
		numerot.add(new Viisi(x, y, tussi));
		numerot.add(new Kuusi(x, y, tussi));
		numerot.add(new Seitseman(x, y, tussi));
		numerot.add(new Kahdeksan(x, y, tussi));
		numerot.add(new Yhdeksan(x, y, tussi));
		
		
		//toinen parametri määrittää kuinka monta numeroa robotti piirtää
		
		SatunnaisetLuvut satunnaisetLuvut = new SatunnaisetLuvut(numerot, 4);
						
// 		satunnaisetLuvut.piirraLuvut();
		for (Numero n: numerot) {
			n.piirra();
		}
		
		x.siirryPoisTielta();
				
	}
	
}
		
		
			

