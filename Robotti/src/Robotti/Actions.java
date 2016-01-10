package Robotti;

public class Actions {
	
	private hihna hihna;
	private Ir ir;
	
	
	public Actions(){
		hihna = new hihna();
		ir = new Ir();
	}
	
	public void kaynnista(){
		hihna.kaynnista();
	}
	
	public void stop(){
		hihna.stop();
	}
	
	
	public boolean block(){
		if (ir.Sample() < 8){
			return true;
		}
		return false;
	}
	
	public void toRight(){
		hihna.right();
	}
	
	public void toLeft(){
		hihna.left();
	}
	
	public void sort(int sample){
		if (sample == 0){
			System.out.println("RED");
			hihna.right();
			kaynnista();
		}else if (sample == 1){
			System.out.println("GREEN");
			hihna.left();
			kaynnista();
		}else{
			hihna.poisto();
			System.out.println("UNKNOWN");
		}
		
	}
	

}
