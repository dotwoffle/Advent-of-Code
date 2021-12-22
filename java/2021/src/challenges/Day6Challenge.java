package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**Day 6 challenge: <a href=https://adventofcode.com/2021/day/6>Lanternfish</a>*/
public class Day6Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day6Challenge() throws FileNotFoundException, IOException {
		super(2021, 6);
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		final int NUM_DAYS = 80; //total simulation days
		ArrayList<Integer> timers = new ArrayList<>(); //lanternfish timers
		
		//build timers
		for(String num : input.get(0).split(","))
			timers.add(Integer.parseInt(num));
		
		//simulate fish
		for(int x = 0; x < NUM_DAYS; x++) {
			
			int newFish = 0; //number of new fish spawned today
			
			//update all existing timers
			for(int y = 0; y < timers.size(); y++) {
				
				//if timer is 0, reset it and spawn a new fish
				if(timers.get(y) == 0) {
					timers.set(y, 6);
					newFish++;
				}
				//otherwise decrement timer
				else
					timers.set(y, timers.get(y)-1);
				
			}
			
			//add new fish
			for(int y = 0; y < newFish; y++)
				timers.add(8);
			
		}
		
		System.out.println(timers.size());

	}

	@Override
	protected void challengePart2() {
		
		final int NUM_DAYS = 256; //total simulation days
		long[] timers = new long[9]; //total number of fish at each timer value, one index for each day
		
		//build timers
		for(String num : input.get(0).split(","))
			timers[Integer.parseInt(num)]++;
		
		//simluate fish
		for(int day = 0; day < NUM_DAYS; day++) {
			
			long newFish = timers[0]; //number of new fish to spawn
			
			//update non-0 timers
			for(int x = 1; x < timers.length; x++)
				timers[x-1] = timers[x];
			
			timers[8] = newFish; //add new fish
			timers[6] += newFish; //reset fish that spawned today
			
		}
		
		long totalFish = 0; //total fish
		
		//count fish
		for(long fish : timers)
			totalFish += fish;
		
		System.out.println(totalFish);

	}

}
