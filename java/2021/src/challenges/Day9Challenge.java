package challenges;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**Day 9 challenge: <a href=https://adventofcode.com/2021/day/9>Smoke Basin</a>*/
public class Day9Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**2d array representing the map.*/
	private int[][] grid;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day9Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 9);
		
		//init
		
		grid = new int[input.size()][input.get(0).length()];
		
		//load grid
		for(int y = 0; y < input.size(); y++)
			for(int x = 0; x < input.get(y).length(); x++)
				grid[y][x] = Integer.parseInt("" + input.get(y).charAt(x));
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {

		int dangerLevel = 0; //total danger level
		
		//find low points
		for(int y = 0; y < grid.length; y++)
			for(int x = 0; x < grid[y].length; x++)
				if(isLowPoint(x, y))
					dangerLevel += grid[y][x] + 1;
		
		System.out.println(dangerLevel);

	}

	@Override
	protected void challengePart2() {
		
		ArrayList<Point> lowPoints = new ArrayList<>(); //list of low points
		ArrayList<ArrayList<Point>> basins = new ArrayList<>(); //all basins that are found
		
		//find low points
		for(int y = 0; y < grid.length; y++)
			for(int x = 0; x < grid[y].length; x++)
				if(isLowPoint(x, y))
					lowPoints.add(new Point(x, y));
		
		//find basins
		for(Point point : lowPoints)
			basins.add(generateBasin(point.x, point.y));
		
		ArrayList<ArrayList<Point>> largestBasins = new ArrayList<>(); //3 largest basins
		
		//find largest basins
		for(int x = 0; x < 3; x++) {
			
			ArrayList<Point> largestBasin = basins.get(0); //current largest basin
			
			//locate largest
			for(ArrayList<Point> basin : basins)
				if(basin.size() > largestBasin.size())
					largestBasin = basin;
			
			largestBasins.add(largestBasin); //save largest
			basins.remove(largestBasin); //remove largest from original list
			
		}
		
		System.out.println(largestBasins.get(0).size() * largestBasins.get(1).size() * largestBasins.get(2).size());

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
		
		ArrayList<Point> validNeighbors = new ArrayList<>(); //neighbors that are not out of bounds
		
		//throw out non-existent neighbors
		for(Point neighbor : neighbors)
			if(!(neighbor.x < 0 || neighbor.x >= grid[0].length || neighbor.y < 0 || neighbor.y >= grid.length))
				validNeighbors.add(neighbor);
		
		return validNeighbors;
		
	}
	
	/**Checks if this point is a low point in the map.
	 * @param x The x coordinate of the point.
	 * @param y The y coordinate of the point.
	 * @return True if the point is a low point, false otherwise.*/
	private boolean isLowPoint(int x, int y) {

		ArrayList<Integer> neighborValues = new ArrayList<>(); //values from neighbors to this point

		//get values
		for(Point neighbor : getNeighbors(x, y))
			neighborValues.add(grid[neighbor.y][neighbor.x]);
		
		boolean allAreGreater = true; //whether all neighbors have a bigger value
		
		//check if this is a low point
		for(int value : neighborValues) {
			if(value <= grid[y][x]) {
				allAreGreater = false;
				break;
			}
		}
		
		return allAreGreater;
		
	}
	
	/**Finds the basin surrounding the given point.*/
	private ArrayList<Point> generateBasin(int x, int y) {
		
		ArrayList<Point> basin = new ArrayList<>(); //list of points in the basin
		Queue<Point> searchQ = new LinkedList<>(); //search queue
		
		//visit starting point
		searchQ.add(new Point(x, y));
		basin.add(new Point(x, y));
		
		//find all connected points
		while(!searchQ.isEmpty()) {
			
			Point nextPoint = searchQ.remove(); //get next point from queue
			
			//get all neighbors that don't have value 9
			for(Point point : getNeighbors(nextPoint.x, nextPoint.y)) {
				if(!basin.contains(point) && grid[point.y][point.x] != 9) {
					basin.add(point);
					searchQ.add(point);
				}
			}
			
		}
		
		return basin;
		
	}

}
