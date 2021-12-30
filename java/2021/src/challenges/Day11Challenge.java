package challenges;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**Day 11 challenge: <a href=https://adventofcode.com/2021/day/11>Dumbo Octopus</a>*/
public class Day11Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**The grid containing the octopi.*/
	private int[][] grid;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day11Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 11);
		
		//init
		
		grid = new int[10][10];
		
	}

	
	/*****************/
	/*    Methods    */
	/*****************/
	
	@Override
	protected void challengePart1() {
		
		loadGrid();
	
		long totalFlashes = 0; //total flashes over 100 steps
		
		//do steps
		for(int step = 0; step < 100; step++) {
			
			ArrayList<Point> octopiToFlash = new ArrayList<Point>(); //locations to flash
			
			//increase energy levels
			for(int y = 0; y < grid.length; y++) {
				for(int x = 0; x < grid[y].length; x++) {
					
					grid[y][x]++;
					
					//check which to flash first
					if(grid[y][x] > 9)
						octopiToFlash.add(new Point(x, y));
					
				}
			}
			
			ArrayList<Point> alreadyFlashed = new ArrayList<Point>(); //which octopi have already flashed
			
			//continue until no more can flash
			while(!octopiToFlash.isEmpty()) {
				
				ArrayList<Point> newFlashes = new ArrayList<>(); //new flashes discovered this loop
				
				//first flash current octopi
				for(Point octopus : octopiToFlash) {
					
					totalFlashes++;
					alreadyFlashed.add(octopus);
					
					//get next to flash
					for(Point neighbor : getNeighbors(octopus.x, octopus.y)) {
						
						grid[neighbor.y][neighbor.x]++;
						
						//add new flashes to list
						if(grid[neighbor.y][neighbor.x] > 9 &&
								!alreadyFlashed.contains(neighbor) &&
								!newFlashes.contains(neighbor) &&
								!octopiToFlash.contains(neighbor)) {
							
							newFlashes.add(neighbor);
							
						}
						
					}
					
				}
				
				octopiToFlash = newFlashes; //reset flash list
				
			}
			
			//reset all flashed back to 0
			for(Point flashed : alreadyFlashed)
				grid[flashed.y][flashed.x] = 0;
			
		}
		
		System.out.println(totalFlashes);
		
	}

	@Override
	protected void challengePart2() {
		
		loadGrid();
		
		int step = 1; //current step
		
		//do steps until correct step is found
		while(true) {
			
			ArrayList<Point> octopiToFlash = new ArrayList<Point>(); //locations to flash
			
			//increase energy levels
			for(int y = 0; y < grid.length; y++) {
				for(int x = 0; x < grid[y].length; x++) {
					
					grid[y][x]++;
					
					//check which to flash first
					if(grid[y][x] > 9)
						octopiToFlash.add(new Point(x, y));
					
				}
			}
			
			ArrayList<Point> alreadyFlashed = new ArrayList<Point>(); //which octopi have already flashed
			
			//continue until no more can flash
			while(!octopiToFlash.isEmpty()) {
				
				ArrayList<Point> newFlashes = new ArrayList<>(); //new flashes discovered this loop
				
				//first flash current octopi
				for(Point octopus : octopiToFlash) {
					
					alreadyFlashed.add(octopus);
					
					//get next to flash
					for(Point neighbor : getNeighbors(octopus.x, octopus.y)) {
						
						grid[neighbor.y][neighbor.x]++;
						
						//add new flashes to list
						if(grid[neighbor.y][neighbor.x] > 9 &&
								!alreadyFlashed.contains(neighbor) &&
								!newFlashes.contains(neighbor) &&
								!octopiToFlash.contains(neighbor)) {
							
							newFlashes.add(neighbor);
							
						}
						
					}
					
				}
				
				octopiToFlash = newFlashes; //reset flash list
				
			}
			
			//reset all flashed back to 0
			for(Point flashed : alreadyFlashed)
				grid[flashed.y][flashed.x] = 0;
			
			boolean allFlashed = true; //whether all octopi flashed this step
			
			//check if all flashed
			allFlashCheck: //fancy labels
			for(int y = 0; y < grid.length; y++) {
				for(int x = 0; x < grid[y].length; x++) {
					if(!alreadyFlashed.contains(new Point(x, y))) {
						allFlashed = false;
						break allFlashCheck;
					}
				}
			}
			
			if(allFlashed)
				break;
			
			step++;
			
		}
		
		System.out.println(step);
		
	}
	
	/**Loads the grid from the input.*/
	private void loadGrid() {
		for(int y = 0; y < input.size(); y++)
			for(int x = 0; x < input.get(y).length(); x++)
				grid[y][x] = Integer.parseInt("" + input.get(y).charAt(x));
	}
	
	/**Finds the neighboring coordinates for a specific point in the grid.
	 * @param x The x coordinate of the point.
	 * @param y The y coordinate of the point.
	 * @return A list of all valid neighbors for this point.*/
	private ArrayList<Point> getNeighbors(int x, int y) {
		
		ArrayList<Point> neighbors = new ArrayList<>(); //list of neighboring points
		
		//add all potential neighbors
		neighbors.add(new Point(x-1, y));
		neighbors.add(new Point(x+1, y));
		neighbors.add(new Point(x, y-1));
		neighbors.add(new Point(x, y+1));
		neighbors.add(new Point(x+1, y+1));
		neighbors.add(new Point(x+1, y-1));
		neighbors.add(new Point(x-1, y+1));
		neighbors.add(new Point(x-1, y-1));
		
		ArrayList<Point> validNeighbors = new ArrayList<>(); //neighbors that are not out of bounds
		
		//throw out non-existent neighbors
		for(Point neighbor : neighbors)
			if(!(neighbor.x < 0 || neighbor.x >= grid[0].length || neighbor.y < 0 || neighbor.y >= grid.length))
				validNeighbors.add(neighbor);
		
		return validNeighbors;
		
	}

}
