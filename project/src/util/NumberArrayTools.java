package util;

public class NumberArrayTools {

	public static int[] numberToArray(int numbers, int value) {
		int[] array = new int[numbers];
		
		double divider = Math.pow(10, numbers-1);
		
		for (int i = 0; i < numbers; i++) {
			array[i] = (int)Math.floor((value / divider));
			value -= array[i] * divider;
			divider = divider / 10;
		}
		
		return array;
	}
	
	public static int arrayToNumber(int[] array) {
		int result = 0;
		
		int multiplier = (int)Math.pow(10, array.length-1);
		
		for (int i = 0; i < array.length; i++) {
			result += multiplier * array[i];
			multiplier = multiplier / 10;
		}
		
		return result;
	}
	
}
