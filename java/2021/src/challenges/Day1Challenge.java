package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**Day 1 challenge: <a href="https://adventofcode.com/2021/day/1">Sonar Sweep</a>*/
public class Day1Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**The depth measurements as integers.*/
	private ArrayList<Integer> measurements;
	

	/*********************/
	/*    Constructor    */
	/*********************/
	
	/**Creates a new Day1Challenge object.
	 * @throws FileNotFoundException If the input file for this challenge is not found.
	 * @throws IOException*/
	public Day1Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 1);
		
		this.measurements = new ArrayList<Integer>();
		
		for(String line : input)
			measurements.add(Integer.parseInt(line));
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		int numIncreases = 0; //total measurement increases

		//find increasing measurements
		for(int x = 1; x < measurements.size(); x++)
			if(measurements.get(x) > measurements.get(x-1))
				numIncreases++;
		
		System.out.println(numIncreases);
		
	}

	@Override
	protected void challengePart2() {
		
		int numIncreases = 0; //total measurement increases
		
		//loop through measurements
		for(int x = 3; x < measurements.size(); x++) {
			
			//sum groups of 3 measurements
			int prevGroupSum = measurements.get(x-3) + measurements.get(x-2) + measurements.get(x-1);
			int newGroupSum = measurements.get(x-2) + measurements.get(x-1) + measurements.get(x);
			
			//check if new sum is greater than older
			if(newGroupSum > prevGroupSum)
				numIncreases++;
			
		}
		
		System.out.println(numIncreases);

	}

}
