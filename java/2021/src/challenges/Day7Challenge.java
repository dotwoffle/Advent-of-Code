package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**Day 7 challenge: <a href=https://adventofcode.com/2021/day/7>The Treachery of Whales</a>*/
public class Day7Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**The positions of each crab.*/
	private ArrayList<Integer> positions;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day7Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 7);
		
		//init
		
		positions = new ArrayList<>();
		
		//load positions
		for(String num : input.get(0).split(","))
			positions.add(Integer.parseInt(num));
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		int minCost = Integer.MAX_VALUE; //current best cost
		
		for(int x = 0; x <= Collections.max(positions); x++) {
			
			int totalFuel = 0; //total fuel cost for this position
			
			//sum fuel costs
			for(int pos : positions)
				totalFuel += Math.abs(pos-x);
			
			//update best cost
			if(totalFuel < minCost)
				minCost = totalFuel;
			
		}
		
		System.out.println(minCost);
		
	}

	@Override
	protected void challengePart2() {
		
		int minCost = Integer.MAX_VALUE; //current best cost
		
		for(int x = 0; x <= Collections.max(positions); x++) {
			
			int totalFuel = 0; //total fuel cost for this position
			
			//sum fuel costs
			for(int pos : positions)
				totalFuel += (Math.abs(pos-x) * (Math.abs(pos-x) + 1) / 2);
			
			//update best cost
			if(totalFuel < minCost)
				minCost = totalFuel;
			
		}
		
		System.out.println(minCost);
		
	}

}
