package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import util.Coordinate;


/**Day 12 challenge: <a href="https://adventofcode.com/2022/day/12">Hill Climbing Algorithm</a>*/
public class Day12Challenge extends Challenge {

    /*Variables*/

    /**Starting location.*/
    private Coordinate startLoc;
    /**Destination location.*/
    private Coordinate endLoc;


    /*Constructor*/

    public Day12Challenge() throws FileNotFoundException, IOException {

        super(2022, 12);

        //find start and end location
        for(int y = 0; y < input.size(); y++) {
            for(int x = 0; x < input.get(0).length(); x++) {
                
                if(input.get(y).charAt(x) == 'S') {
                    startLoc =  new Coordinate(x, y);
                }
                else if(input.get(y).charAt(x) == 'E') {
                    endLoc = new Coordinate(x, y);
                }

            }
        }

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        Queue<Coordinate> searchQ = new LinkedList<>(); //bfs search queue
        HashSet<Coordinate> visited = new HashSet<>(); //visited nodes list
        HashMap<Coordinate, Coordinate> parents = new HashMap<>(); //map of locations to their parent locations in the path

        searchQ.add(startLoc);
        visited.add(startLoc);

        //search for endpoint
        while(!searchQ.isEmpty()) {

            Coordinate next = searchQ.remove(); //get next coordinate
            
            //search for applicable neighbors
            for(int x = next.x-1; x <= next.x+1; x++) {
                for(int y = next.y-1; y <= next.y+1; y++) {

                    if(x >= 0 && x < input.get(0).length() && y >= 0 && y < input.size() && (
                        (x == next.x && y == next.y-1) ||
                        (x == next.x && y == next.y+1) ||
                        (x == next.x-1 && y == next.y) ||
                        (x == next.x+1 && y == next.y)
                    )) {

                        Coordinate neighborLoc = new Coordinate(x, y); //neighbor coordinates

                        if((!visited.contains(neighborLoc)) && (getElevation(neighborLoc) <= getElevation(next)+1)) {
                            searchQ.add(neighborLoc);
                            parents.put(neighborLoc, next); //connect neighbor to parent
                            visited.add(neighborLoc); //track node
                        }

                    }

                }
            }

            if(next.equals(endLoc)) {
                break;
            }

        }

        int pathLength = 0;
        Coordinate currentLoc = endLoc;

        //step backwards in path
        while(parents.containsKey(currentLoc)) {
            currentLoc = parents.get(currentLoc);
            pathLength++;
        }

        System.out.println(pathLength);
        
    }

    @Override
    protected void challengePart2() {
        
        Coordinate finalDestination = null; //location of closest 'a'
        Queue<Coordinate> searchQ = new LinkedList<>(); //bfs search queue
        HashSet<Coordinate> visited = new HashSet<>(); //visited nodes list
        HashMap<Coordinate, Coordinate> parents = new HashMap<>(); //map of locations to their parent locations in the path

        //start at end location this time and search for nearest 'a'
        searchQ.add(endLoc);
        visited.add(endLoc);

        //search for endpoint
        while(!searchQ.isEmpty()) {

            Coordinate next = searchQ.remove(); //get next coordinate
            
            //search for applicable neighbors
            for(int x = next.x-1; x <= next.x+1; x++) {
                for(int y = next.y-1; y <= next.y+1; y++) {

                    if(x >= 0 && x < input.get(0).length() && y >= 0 && y < input.size() && (
                        (x == next.x && y == next.y-1) ||
                        (x == next.x && y == next.y+1) ||
                        (x == next.x-1 && y == next.y) ||
                        (x == next.x+1 && y == next.y)
                    )) {

                        Coordinate neighborLoc = new Coordinate(x, y); //neighbor coordinates

                        if((!visited.contains(neighborLoc)) && (getElevation(next) <= getElevation(neighborLoc)+1)) {
                            searchQ.add(neighborLoc);
                            parents.put(neighborLoc, next); //connect neighbor to parent
                            visited.add(neighborLoc); //track node
                        }

                    }

                }
            }

            if(getElevation(next) == 'a') {
                finalDestination = next;
                break;
            }

        }

        int pathLength = 0;
        Coordinate currentLoc = finalDestination;

        //step backwards in path
        while(parents.containsKey(currentLoc)) {
            currentLoc = parents.get(currentLoc);
            pathLength++;
        }

        System.out.println(pathLength);
        
    }

    @Override
    protected void initialize() {}

    /**Returns the elevation character at a given location in the graph.*/
    private char getElevation(Coordinate location) {

        if(location.equals(startLoc)) {
            return 'a';
        }
        else if(location.equals(endLoc)) {
            return 'z';
        }
        else {
            return input.get(location.y).charAt(location.x);
        }

    }
    
}
