import java.util.ArrayList;
import java.util.Random;

/*
  SatunnaisetLuvut saa listan
  Numero-rajapinnan toteuttavia
  luokkia. Rajapinta mahdollistaa
  numeroiden satunnaisgeneraation.
*/

public class SatunnaisetLuvut {
	private ArrayList<Numero> numerot;
	private int kuinkaMonta;
	private Random random;
	
	public SatunnaisetLuvut(ArrayList<Numero> numerot, int kuinkaMonta) {
		this.numerot = numerot;
		this.kuinkaMonta = kuinkaMonta;
		this.random = new Random();
	}
	
	public void piirraLuvut() {
		int i = 0;
		
		while (i < kuinkaMonta) {
			numerot.get(random.nextInt(10)).piirra();
			i++;
		}
	}
}
