package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**Day 8 challenge: <a href=https://adventofcode.com/2021/day/8>Seven Segment Search</a>*/
public class Day8Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day8Challenge() throws FileNotFoundException, IOException {
		super(2021, 8);
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {

		int numberCount = 0; //count of all 1/4/7/8
		
		//find numbers in output
		for(String line : input) {
			
			// >regex
			String outputs = line.split(" \\| ")[1]; //get output numbers
			
			//sum unique numbers
			for(String output : outputs.split(" "))
				if(output.length() == 2 || output.length() == 3 || output.length() == 4 || output.length() == 7)
					numberCount++;
			
		}
		
		System.out.println(numberCount);
		
	}

	@Override
	protected void challengePart2() {
		
		int total = 0; //total sum of outputs

		for(String line : input) {
			
			String[] patterns = line.split(" \\| ")[0].split(" "); //input char patterns
			
			//sort strings alphabetically for easier processing
			for(int x = 0; x < patterns.length; x++) {
				char[] stringChars = patterns[x].toCharArray();
				Arrays.sort(stringChars);
				patterns[x] = new String(stringChars);
			}
			
			ArrayList<String> numberStrings = findNumbersFromPatterns(patterns); //find number assignments
			total += getNumberFromStrings(line.split(" \\| ")[1].split(" "), numberStrings); //get output number
			
		}
		
		System.out.println(total);
		
	}
	
	/**Determines which character patterns are associated with which numbers.
	 * @param patterns A list of ten strings from the input, each representing a unique single digit number.
	 * @return A list of strings with all characters in alphabetical order. The string at each index represents the
	 * number for that index.*/
	public ArrayList<String> findNumbersFromPatterns(String[] patterns) {
		
		ArrayList<String> knownNumbers = new ArrayList<>(); //strings associated with numbers
		
		//possible character assignments to segments
		ArrayList<ArrayList<Character>> knownAssignments = new ArrayList<ArrayList<Character>>();
		
		//init lists
		
		for(int x = 0; x < 10; x++)
			knownNumbers.add("");
		
		for(int x = 0; x < 7; x++)
			knownAssignments.add(new ArrayList<>());
		
		//find 1/4/7/8 strings
		for(String num : patterns) {
			
			if(num.length() == 2)
				knownNumbers.set(1, num);
			else if(num.length() == 3)
				knownNumbers.set(7, num);
			else if(num.length() == 4)
				knownNumbers.set(4, num);
			else if(num.length() == 7)
				knownNumbers.set(8, num);
			
		}

		//segment 0 can be found from strings 7 and 1
		for(int x = 0; x < knownNumbers.get(7).length(); x++) {
			
			//check which segment in 7 is missing from 1
			if(!knownNumbers.get(1).contains("" + knownNumbers.get(7).charAt(x)))
				knownAssignments.get(0).add(knownNumbers.get(7).charAt(x));
			
			//add other two characters to segments 1 and 2
			else {
				knownAssignments.get(1).add(knownNumbers.get(7).charAt(x));
				knownAssignments.get(2).add(knownNumbers.get(7).charAt(x));
			}
			
		}
		
		//get segments from string 4 that aren't in string 1
		for(int x = 0; x < knownNumbers.get(4).length(); x++) {
			if(!knownNumbers.get(1).contains("" + knownNumbers.get(4).charAt(x))) {
				knownAssignments.get(5).add(knownNumbers.get(4).charAt(x));
				knownAssignments.get(6).add(knownNumbers.get(4).charAt(x));
			}
		}
		
		//find strings 6, 9 and 0
		for(String num : patterns) {
			if(num.length() == 6) {
				
				int sharedSegments = 0; //number of shared segments with string 4
				
				//get shared characters with 4
				for(int x = 0; x < num.length(); x++)
					if(knownNumbers.get(4).contains("" + num.charAt(x)))
						sharedSegments++;
				
				//6 or 0
				if(sharedSegments == 3) {
					
					//0
					if(num.contains("" + knownAssignments.get(1).get(0)) &&
					   num.contains("" + knownAssignments.get(1).get(1))) {
						knownNumbers.set(0, num);
					}
					
					//6
					else
						knownNumbers.set(6, num);
					
				}
				
				//9
				else {
					
					knownNumbers.set(9, num);
					
					//can also figure out segment 3 from strings 4 and 9
					for(int x = 0; x < num.length(); x++) {
						if(num.charAt(x) != knownAssignments.get(0).get(0) &&
						   !knownNumbers.get(4).contains("" + num.charAt(x))) {
							
							knownAssignments.get(3).add(num.charAt(x));
							break;
							
						}
					}
					
				}
				
			}
		}
		
		//find segment 4 from strings 9 and 8
		for(int x = 0; x < knownNumbers.get(8).length(); x++) {
			if(!knownNumbers.get(9).contains("" + knownNumbers.get(8).charAt(x))) {
				knownAssignments.get(4).add(knownNumbers.get(8).charAt(x));
				break;
			}
		}
			
		//find segments 1 and 2 from strings 8 and 6
		for(int x = 0; x < knownNumbers.get(8).length(); x++) {
			if(!knownNumbers.get(6).contains("" + knownNumbers.get(8).charAt(x))) {
				
				//save both possibilities for segments 1/2
				ArrayList<Character> seg1Or2 = new ArrayList<>(knownAssignments.get(1));
				
				knownAssignments.get(1).clear();
				knownAssignments.get(2).clear();
				knownAssignments.get(1).add(knownNumbers.get(8).charAt(x)); //add segment 1
				knownAssignments.get(2).add(seg1Or2.get(0) == knownNumbers.get(8).charAt(x) ?
						seg1Or2.get(1) : seg1Or2.get(0)); //add segment 2
				
				break;
				
			}
		}
		
		//find segments 5 and 6 from string 0
		
		//save both possibilities for segments 5/6
		ArrayList<Character> seg5Or6 = new ArrayList<>(knownAssignments.get(5));
		
		//clear originals
		knownAssignments.get(5).clear();
		knownAssignments.get(6).clear();
		
		//find correct assignment
		if(knownNumbers.get(0).contains("" + seg5Or6.get(0))) {
			knownAssignments.get(5).add(seg5Or6.get(0));
			knownAssignments.get(6).add(seg5Or6.get(1));
		}
		else {
			knownAssignments.get(5).add(seg5Or6.get(1));
			knownAssignments.get(6).add(seg5Or6.get(0));
		}
		
		//find remaining numbers using signal assignments
		for(String num : patterns) {
			if(num.length() == 5) {
				
				//determine if 2, 3 or 5
				if(!num.contains("" + knownAssignments.get(2).get(0)) &&
				   !num.contains("" + knownAssignments.get(5).get(0))) {
					
					knownNumbers.set(2, num);
					
				}
				else if(!num.contains("" + knownAssignments.get(4).get(0)) &&
						!num.contains("" + knownAssignments.get(5).get(0))) {
					
					knownNumbers.set(3, num);
					
				}
				else
					knownNumbers.set(5, num);
				
			}
		}
		
		return knownNumbers;
		
	}
	
	/**Determines the value of a 4 digit character pattern number given the known number character patterns.
	 * @param patterns The list of character patterns representing the output number.
	 * @param knownNumbers The associations from character patterns to numbers.
	 * @return The number that the patterns are encoding.*/
	public int getNumberFromStrings(String[] patterns, ArrayList<String> knownNumbers) {
		
		String numberString = ""; //final number
		
		//convert patterns to numbers
		for(int x = 0; x < patterns.length; x++) {
			
			//sort string alphabetically
			char[] stringChars = patterns[x].toCharArray();
			Arrays.sort(stringChars);
			String sortedString = new String(stringChars);
			
			numberString += knownNumbers.indexOf(sortedString); //find number and add it to total
			
		}
		
		return Integer.parseInt(numberString);
		
	}

}
