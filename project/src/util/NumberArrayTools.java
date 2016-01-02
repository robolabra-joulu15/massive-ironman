package util;

public class NumberArrayTools {

	public static int[] numberToArray(int numbers, double value, int decimals) {
		int[] array = new int[numbers];
		
		double divider = Math.pow(10, numbers - decimals-1);
		
		for (int i = 0; i < numbers; i++) {
			array[i] = (int)Math.floor((value / divider));
			value -= array[i] * divider;
			divider = divider / 10;
		}
		
		return array;
	}
	
	public static double arrayToNumber(int[] array, int decimals) {
		double result = 0;
		
		double multiplier = Math.pow(10, array.length - decimals-1);
		
		for (int i = 0; i < array.length; i++) {
			result += multiplier * array[i];
			multiplier = multiplier / 10;
		}
		
		return result;
	}
	
}
