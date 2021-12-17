package challenges;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**A template class for challenges.*/
public abstract class Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**The challenge number.*/
	private final int DAY;
	/**The challenge year.*/
	private final int YEAR;
	/**The lines from the puzzle input.*/
	protected ArrayList<String> input;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/
	
	/**Creates a new Challenge object.
	 * @param year The challenge year.
	 * @param day The challenge day number.
	 * @throws IOException 
	 * @throws FileNotFoundException If the input file matching the year and day can't be found.*/
	public Challenge(int year, int day) throws FileNotFoundException, IOException {
		
		this.DAY = day;
		this.YEAR = year;
		this.input = loadInput();
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/
	
	public void runChallenge() {
		
		System.out.println();
		System.out.println("========== PART 1 ==========");
		System.out.println();
		
		challengePart1();
		
		System.out.println();
		System.out.println("========== PART 2 ==========");
		System.out.println();
		
		challengePart2();
		
	}
	
	/**Loads the challenge input from the associated file.
	 * @return A list of strings with each element containing a line from the input file.
	 * @throws FileNotFoundException If the matching input file is not found.
	 * @throws IOException*/
	private ArrayList<String> loadInput() throws FileNotFoundException, IOException{
		
		//path to input file
		String inputFilePath = "../../inputs/" + Integer.toString(YEAR) + "/day" + Integer.toString(DAY) + ".txt";
		
		FileInputStream file = null; //input stream
		Scanner fileIn = null; //scanner to read file
		ArrayList<String> contents = new ArrayList<String>(); //file contents
		
		//attempt to read file contents
		try {
			
			file = new FileInputStream(inputFilePath); //open file
			fileIn = new Scanner(file); //open scanner
			
			//read each line into list
			while(fileIn.hasNext())
				contents.add(fileIn.nextLine());
			
		}
		catch(FileNotFoundException e) {
			throw e;
		}
		finally {
			file.close();
			fileIn.close();
		}
		
		return contents;
		
	}
	
	/**Runs the solution for part 1 of the challenge.*/
	protected abstract void challengePart1();
	
	/**Runs the solution for part 2 of the challenge.*/
	protected abstract void challengePart2();
	

}
