package main;

import challenges.Challenge;
import challenges.Day7Challenge;


/**Driver class for challenges.*/
public class Main {

	/**************/
	/*    Main    */
	/**************/
	
	public static void main(String[] args) {
		
		try {
			
			Challenge challenge = new Day7Challenge();
			
			challenge.runChallenge();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
