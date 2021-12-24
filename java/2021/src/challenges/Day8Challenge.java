package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


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

	}

}
