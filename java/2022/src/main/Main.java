package main;

import challenges.Challenge;
import challenges.Day13Challenge;


/**Driver class for challenges.*/
public class Main {

	/**************/
	/*    Main    */
	/**************/
	
	public static void main(String[] args) {
		
		try {
			
			Challenge challenge = new Day13Challenge();
			
			challenge.runChallenge();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
