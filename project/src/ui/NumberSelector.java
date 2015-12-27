package ui;

import util.NumberArrayTools;

public class NumberSelector {

	private int[] selection;
	
	public NumberSelector(int numbers, int value) {
		this.selection = NumberArrayTools.numberToArray(numbers, value);
	}
	
	
	
}
