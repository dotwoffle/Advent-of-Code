package main;

import challenges.Challenge;
import challenges.Day11Challenge;


/**Driver class for challenges.*/
public class Main {

	/**************/
	/*    Main    */
	/**************/
	
	public static void main(String[] args) {
		
		try {
			
			Challenge challenge = new Day11Challenge();
			
			challenge.runChallenge();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
