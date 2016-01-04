package util;

public class NumberArrayTools {

	//Utility function used to convert number value to an array containing single numbers
	public static int[] numberToArray(int numbers, double value, int decimals) {
		int[] array = new int[numbers];
		
		//Calculate divider. If the number is 1200, the divider for digit 1 is 10^3=1000 because 1200 / 1000 = 1 when rounded down
		double divider = Math.pow(10, numbers - decimals-1);
		
		//Do the math for every number as explained above
		for (int i = 0; i < numbers; i++) {
			array[i] = (int)Math.floor((value / divider));
			value -= array[i] * divider; //decrease original number
			divider = divider / 10; //reduce divider
		}
		
		return array;
	}
	
	//Same operation as above, but in the other way. Array -> Number
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
