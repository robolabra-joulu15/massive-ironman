package util;

import java.util.ArrayList;

public class StringTools {
	
	//Utility function used to convert a long string to a multi row string so the text can be easily
	//printed to the NXT LCD screen, which has only 16 characters width.
	public static ArrayList<String> multiRow(String str, int maxRowLength) {
		ArrayList<String> rows = new ArrayList<String>();
		String current = "";
		
		while(str.length() != 0) {
			int spaceIndex = str.indexOf(' ');
			
			//If indexOf doesn't find any spaces, it's the last word! (indexOf returns -1 when not found)
			if (spaceIndex == -1) { 
				//If the only word left doesn't fit in the current row, add a new row and insert it there. Else just add it to current row.
				if (current.length() + str.length() > maxRowLength) {
					rows.add(current);
					rows.add(str);
				}else {
					current = current + str;
					rows.add(current);
				}
				
				return rows;
			}else { 
				//Take the first word from the beginning to an own variable and remove it from str
				String word = str.substring(0, spaceIndex+1);
				str = str.substring(spaceIndex + 1);
				
				//Finish current row and add a new one if there's no space left
				if (current.length() + word.length() > maxRowLength) {
					rows.add(current);
					current = "";
				}
				
				//Add the word to current row
				current = current + word;
			}
		}
	
		return rows;
	}
	
}
