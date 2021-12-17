package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;


/**Day 2 challenge: <a href=https://adventofcode.com/2021/day/2>Dive!</a>*/
public class Day2Challenge extends Challenge {
	
	/*********************/
	/*    Constructor    */
	/*********************/

	/**Creates a new Day2Challenge object.
	 * @throws FileNotFoundException If the input file for this challenge is not found.
	 * @throws IOException*/
	public Day2Challenge() throws FileNotFoundException, IOException {
		super(2021, 2);
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		int position = 0; //current horizontal position
		int depth = 0; //current depth
		
		//do all movements
		for(String movement : input) {
			
			String direction = movement.split(" ")[0]; //direction to move
			int units = Integer.parseInt(movement.split(" ")[1]); //how far to move
			
			//check movement direction
			switch(direction) {
			
				case "forward":
					position += units;
					break;
					
				case "down":
					depth += units;
					break;
					
				case "up":
					depth -= units;
					break;
					
				default:
					break;
			
			}
			
		}
		
		System.out.println(position * depth);
		
	}

	@Override
	protected void challengePart2() {
		
		int position = 0; //current horizontal position
		int depth = 0; //current depth
		int aim = 0; //current aim
		
		//do all movements
		for(String movement : input) {
			
			String direction = movement.split(" ")[0]; //direction to move
			int units = Integer.parseInt(movement.split(" ")[1]); //how far to move
			
			//check movement direction
			switch(direction) {
			
				case "forward":
					position += units;
					depth += (aim * units);
					break;
					
				case "down":
					aim += units;
					break;
					
				case "up":
					aim -= units;
					break;
					
				default:
					break;
			
			}
			
		}
		
		System.out.println(position * depth);
		
	}

}
