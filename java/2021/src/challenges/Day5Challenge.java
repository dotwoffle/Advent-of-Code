package challenges;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


/**Day 5 challenge: <a href=https://adventofcode.com/2021/day/5>Hydrothermal Venture</a>*/
public class Day5Challenge extends Challenge {

	/*******************/
	/*    Variables    */
	/*******************/
	
	/**A list of line objects from the input.*/
	private ArrayList<LineSegment> lines;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/
	
	public Day5Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 5);
		
		//init
		
		this.lines = new ArrayList<>();
		
		//build lines
		for(String line : input) {
			
			String[] coords = line.split(" -> "); //get coord pairs
			
			//get coords
			int startX = Integer.parseInt(coords[0].split(",")[0]);
			int startY = Integer.parseInt(coords[0].split(",")[1]);
			int endX = Integer.parseInt(coords[1].split(",")[0]);
			int endY = Integer.parseInt(coords[1].split(",")[1]);
			
			lines.add(new LineSegment(startX, startY, endX, endY)); //create new line
			
		}
		
	}
	
	
	/***************************/
	/*    LineSegment Class    */
	/***************************/
	
	/**A simple class to store line segment data. No matter which two points are passed to the constructor, the point
	 * closest to the origin is considered the starting point, and the other is considered the endpoint.*/
	private class LineSegment {
		
		/*******************/
		/*    Variables    */
		/*******************/
		
		/**Line starting point. This is the point closest to the origin.*/
		public final Point START_POINT;
		/**Line ending point. This is the point farthest from the origin.*/
		public final Point END_POINT;
		/**All integer point coordinates along this line segment.*/
		private ArrayList<Point> points;
		
		
		/*********************/
		/*    Constructor    */
		/*********************/
		
		/**Creates a new line segment object.
		 * @param startX The X coordinate for the first point of the line.
		 * @param startY The Y coordinate for the first point of the line.
		 * @param endX The X coordinate for the second point of the line.
		 * @param endY The Y coordinate for the second point of the line.*/
		public LineSegment(int startX, int startY, int endX, int endY) {
			
			//init
			this.points = new ArrayList<>();
			
			//create both points
			Point point1 = new Point(startX, startY);
			Point point2 = new Point(endX, endY);
			
			//assign points based on distance from origin
			if(point1.distance(new Point()) <= point2.distance(new Point())) {
				this.START_POINT = point1;
				this.END_POINT = point2;
			}
			else {
				this.START_POINT = point2;
				this.END_POINT = point1;
			}
			
			//fill line points
			if(isVertical())
				for(int y = START_POINT.y; y <= END_POINT.y; y++)
					points.add(new Point(START_POINT.x, y));
			else if(isHorizontal())
				for(int x = START_POINT.x; x <= END_POINT.x; x++)
					points.add(new Point(x, START_POINT.y));
			
			//diagonals
			else {
				
				int delta = Math.abs(START_POINT.x - END_POINT.x); //total change for both directions
				int xDirection = START_POINT.x <= END_POINT.x ? 1 : -1; //direction of change for x
				int yDirection = START_POINT.y <= END_POINT.y ? 1 : -1; //direction of change for y
				int currentX = START_POINT.x; //current x coord
				int currentY = START_POINT.y; //current y
				
				//add diagonal points
				for(int i = 0; i <= delta; i++) {
					
					points.add(new Point(currentX, currentY)); //add new point
					
					//change current point
					currentX += xDirection;
					currentY += yDirection;
					
				}
				
			}
			
		}
		
		
		/*****************/
		/*    Methods    */
		/*****************/
		
		/**Check if this line is vertical.
		 * @return True if it is vertical, false otherwise.*/
		public boolean isVertical() {
			return START_POINT.x == END_POINT.x;
		}
		
		/**Check if this line is horizontal.
		 * @return True if it is horizontal, false otherwise.*/
		public boolean isHorizontal() {
			return START_POINT.y == END_POINT.y;
		}
		
		/**Check if this line segment contains the specified point.
		 * @param point The point to find.
		 * @return True if the given point is in this line segment, false otherwise.*/
		public boolean containsPoint(Point point) {
			
			for(Point linePoint : points)
				if(point.equals(linePoint))
					return true;
			
			return false;
			
		}
		
		/**Find all the points in this line segment that are intersected with another line segment.
		 * @param line The line segment to compare against.
		 * @return A list of all the points that this line has in common with the given line.*/
		public ArrayList<Point> getIntersectingPoints(LineSegment line) {
			
			ArrayList<Point> intersectingPoints = new ArrayList<>(); //all intersecting points
			
			//find intersections
			for(Point point : points)
				if(line.containsPoint(point))
					intersectingPoints.add(point);
			
			return intersectingPoints;
			
		}

		@Override
		public String toString() {
			return START_POINT.x + "," + START_POINT.y + " -> " + END_POINT.x + "," + END_POINT.y;
		}
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		HashSet<Point> intersections = new HashSet<>(); //all points of intersection
		
		//find all unique intersections
		for(int x = 0; x < lines.size(); x++) {
			for(int y = x+1; y < lines.size(); y++) {
				
				//only check vertical/horizontal lines
				if((lines.get(x).isHorizontal() || lines.get(x).isVertical()) && 
				   (lines.get(y).isHorizontal() || lines.get(y).isVertical())) {
				
					for(Point point : lines.get(x).getIntersectingPoints(lines.get(y)))
						intersections.add(point);
					
				}
				
			}
		}
		
		System.out.println(intersections.size());
		
	}

	@Override
	protected void challengePart2() {
		
		HashSet<Point> intersections = new HashSet<>(); //all points of intersection
		
		//find all unique intersections
		for(int x = 0; x < lines.size(); x++)
			for(int y = x+1; y < lines.size(); y++)
				for(Point point : lines.get(x).getIntersectingPoints(lines.get(y)))
					intersections.add(point);
		
		System.out.println(intersections.size());
		
	}

}
