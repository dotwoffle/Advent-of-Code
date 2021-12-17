package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**Day 3 challenge: <a href=https://adventofcode.com/2021/day/3>Binary Diagnostic</a>*/
public class Day3Challenge extends Challenge {
	
	/*********************/
	/*    Constructor    */
	/*********************/
	
	public Day3Challenge() throws FileNotFoundException, IOException {
		super(2021, 3);
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {

		String gammaRate = ""; //gamma rate binary string
		String epsilonRate = ""; //epsilon rate binary string
		
		//stores a list of all bits at a given index for every index
		ArrayList<ArrayList<Character>> positionedBits = new ArrayList<ArrayList<Character>>();
		
		//initialize lists
		for(int x = 0; x < input.get(0).length(); x++)
			positionedBits.add(new ArrayList<Character>());
		
		//get all bits from each index
		for(String binaryString : input)
			
			//add each bit to the list
			for(int x = 0; x < binaryString.length(); x++)
				positionedBits.get(x).add(binaryString.charAt(x));
		
		//check each index
		for(ArrayList<Character> bits : positionedBits) {
			
			int zeroCount = 0; //number of 0 bits in list
			int oneCount = 0; //number of 1 bits in list
			
			//count bits
			for(Character bit : bits) {
				
				if(bit == '0')
					zeroCount++;
				else
					oneCount++;
				
			}
			
			//add bit to measurements
			if(zeroCount > oneCount) {
				gammaRate += "0";
				epsilonRate += "1";
			}
			else {
				gammaRate += "1";
				epsilonRate += "0";
			}
			
		}
		
		System.out.println(Integer.parseInt(epsilonRate, 2) * Integer.parseInt(gammaRate, 2));
		
	}

	@Override
	protected void challengePart2() {
		
		int o2Rating = Integer.parseInt(findO2OrCO2(true), 2);
		int co2Rating = Integer.parseInt(findO2OrCO2(false), 2);
		
		System.out.println(o2Rating * co2Rating);

	}
	
	/**Calculates the value of either oxygen rating or CO2 rating.
	 * @param findO2 If this is true, returns the oxygen rating. Otherwise, returns the CO2 rating.
	 * @return A binary string representing the specified rating.*/
	private String findO2OrCO2(boolean findO2) {
		
		int currentIndex = 0; //current index location in input words
		ArrayList<String> remainingNumbers = input; //track remaining data entries
		
		//loop until one number remains
		while(remainingNumbers.size() > 1) {
			
			int zeroCount = 0; //number of 0 bits at current index
			int oneCount = 0; //number of 1 bits at current index
			ArrayList<String> newNumbers = new ArrayList<String>(); //list to hold allowed numbers this iteration
			
			//count bits at current index
			for(String num : remainingNumbers) {
				
				if(num.charAt(currentIndex) == '0')
					zeroCount++;
				else
					oneCount++;
				
			}
			
			char searchBit = 0; //bit to check for at current index
			
			//set search bit
			if(zeroCount > oneCount)
				searchBit = findO2 ? '0' : '1';
			else
				searchBit = findO2 ? '1' : '0';
			
			//find all remaining words with search bit at current index
			for(String num : remainingNumbers)
				if(num.charAt(currentIndex) == searchBit)
					newNumbers.add(num);
			
			remainingNumbers = newNumbers; //trim numbers
			currentIndex++; //increment index
			
		}
		
		return remainingNumbers.get(0);
		
	}

}
