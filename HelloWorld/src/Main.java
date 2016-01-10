import java.util.ArrayList;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;

/*
	Pääluokka robotille, joka piirtää
	tussilla numeroita paperille.
*/
public class Main {
	public static void main(String [ ] args)
	{
	      RegulatedMotor xAkseli = Motor.A;
	      RegulatedMotor yAkseli = Motor.B;
	      RegulatedMotor tussimoottori = Motor.C;
	      
	      //akseleissa toinen parametri määrittää
	      //piirroksen leveyden(x) ja pituuden(y)
	      XAkseli x = new XAkseli(xAkseli, 80);
	      YAkseli y = new YAkseli(yAkseli, 130);
	      Tussi tussi = new Tussi(tussimoottori);
	      
	      ArrayList<Numero> numerot = new ArrayList<Numero>();
	      
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
	      
	      //toinen parametri määrittää piirrettyjen
	      //lukujen määrän.
	      SatunnaisetLuvut satunnaisetLuvut = new SatunnaisetLuvut(numerot, 4);
	      
	      satunnaisetLuvut.piirraLuvut();
	      
	      x.siirryPoisTielta();
	      
	}
	
	
}
