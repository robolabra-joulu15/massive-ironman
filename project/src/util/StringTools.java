package util;

import java.util.ArrayList;

public class StringTools {

	//This rojbos java shit tells me there's no such function as
	//String.split(" ") so I had to do this a bit differently than I planned...
	
	public static ArrayList<String> multiRow(String str, int maxRowLength) {
		ArrayList<String> rows = new ArrayList<String>();
		String current = "";
		
		while(str.length() != 0) {
			int spaceIndex = str.indexOf(' ');
			
			if (spaceIndex == -1) {
				if (current.length() + str.length() > maxRowLength) {
					rows.add(current);
					rows.add(str);
				}else {
					current = current + str;
					rows.add(current);
				}

				return rows;
			}else {
				String word = str.substring(0, spaceIndex+1);
				str = str.substring(spaceIndex + 1);
				
				if (current.length() + word.length() > maxRowLength) {
					rows.add(current);
					current = "";
				}
				
				current = current + word;
			}
		}
		
		return rows;
	}
	
}
