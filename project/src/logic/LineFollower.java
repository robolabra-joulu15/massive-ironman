package logic;

import ui.ConfiguratorUI;

public class LineFollower {

	public void start() {
		
		ConfiguratorUI config = new ConfiguratorUI();
		
		while(true) {
			
			if (!config.start()) break;
			
		}
		
	}
	
}
