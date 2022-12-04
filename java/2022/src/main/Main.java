package main;

import challenges.Challenge;
import challenges.Day4Challenge;


/**Driver class for challenges.*/
public class Main {

	/**************/
	/*    Main    */
	/**************/
	
	public static void main(String[] args) {
		
		try {
			
			Challenge challenge = new Day4Challenge();
			
			challenge.runChallenge();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
