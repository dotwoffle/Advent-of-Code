package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.Coordinate;

/**Day 14 challenge: <a href="https://adventofcode.com/2022/day/14">Regolith Reservoir</a>*/
public class Day14Challenge extends Challenge {

    /*Variables*/

    /**Location of the sand spawner.*/
    private final Coordinate SAND_SPAWNER;
    /**The y value after which no more rocks appear.*/
    private int maxY;
    /**A grid of the space. True means the space is occupied, false is an empty space.*/
    private boolean[][] grid;


    /*Constructor*/

    public Day14Challenge() throws FileNotFoundException, IOException {

        super(2022, 14);

        //init

        this.SAND_SPAWNER = new Coordinate(500, 0);

    }


    /*Methods*/

    @Override
    protected void challengePart1() {

        int totalSand = 0; //sand units at rest

        //loop until sand falls into abyss
        while(true) {

            Coordinate sandLoc = new Coordinate(SAND_SPAWNER); //track current location of sand particle
            boolean sandInAbyss = false; //becomes true if sand starts falling past y boundary
            totalSand++;

            //move sand
            while(true) {

                //check directly beneath
                if(!grid[sandLoc.y+1][sandLoc.x]) {

                    sandLoc.y += 1;

                    //if sand is in abyss, simulation is over
                    if(sandLoc.y > maxY) {
                        sandInAbyss = true;
                        break;
                    }

                    continue;

                }

                //check left and right
                if(!grid[sandLoc.y+1][sandLoc.x-1]) {
                    sandLoc.x -= 1;
                    sandLoc.y += 1;
                    continue;
                }
                else if(!grid[sandLoc.y+1][sandLoc.x+1]) {
                    sandLoc.x += 1;
                    sandLoc.y += 1;
                    continue;
                }

                //sand has nowhere to go, so solidify it and spawn next particle
                grid[sandLoc.y][sandLoc.x] = true;
                break;

            }

            //if sand went into abyss, end simulation
            if(sandInAbyss) {
                break;
            }

        }

        System.out.println(totalSand - 1); //don't count last particle that fell into abyss

    }

    @Override
    protected void challengePart2() {

        int totalSand = 0; //sand units at rest

        //fill in floor
        for(int x = 0; x < grid[0].length; x++) {
            grid[maxY][x] = true;
        }

        //loop until sand falls into abyss
        while(true) {

            Coordinate sandLoc = new Coordinate(SAND_SPAWNER); //track current location of sand particle
            boolean sandAtSpawn = false; //becomes true if sand stops at (500, 0)
            totalSand++;

            //move sand
            while(true) {

                //check directly beneath
                if(!grid[sandLoc.y+1][sandLoc.x]) {
                    sandLoc.y += 1;
                    continue;
                }

                //check left and right
                if(!grid[sandLoc.y+1][sandLoc.x-1]) {
                    sandLoc.x -= 1;
                    sandLoc.y += 1;
                    continue;
                }
                else if(!grid[sandLoc.y+1][sandLoc.x+1]) {
                    sandLoc.x += 1;
                    sandLoc.y += 1;
                    continue;
                }

                //if sand covers spawn, simulation is over
                if(sandLoc.equals(SAND_SPAWNER)) {
                    sandAtSpawn = true;
                    break;
                }

                //sand has nowhere to go, so solidify it and spawn next particle
                grid[sandLoc.y][sandLoc.x] = true;
                break;

            }

            //if sand went into abyss, end simulation
            if(sandAtSpawn) {
                break;
            }

        }

        System.out.println(totalSand);

    }

    @Override
    protected void initialize() {

        grid = new boolean[1000][1000];

        //put paths onto grid
        for(String path : input) {

            //get path coordinates
            ArrayList<Coordinate> pathSteps = new ArrayList<>(Arrays.asList(path.split(" -> ")).stream().map(
                location -> new Coordinate(location)
            ).collect(Collectors.toList()));

            //search for end of grid (abyss for part 1, floor for part 2)
            for(Coordinate coord : pathSteps) {
                if(coord.y > maxY) {
                    maxY = coord.y + 2;
                }
            }

            drawPath(pathSteps); //fill in rocks

        }

    }

    /**Draws a full path in the grid specified by the steps in the string.
     * @param path An array of coordinates specifying the path to draw.
    */
    private void drawPath(ArrayList<Coordinate> path) {

        //build paths on grid
        for(int i = 1; i < path.size(); i++) {
            
            //horizontal line
            if(path.get(i).y == path.get(i-1).y) {

                int y = path.get(i).y;
                int[] rangeBounds = new int[] {path.get(i).x, path.get(i-1).x}; //bounds for line

                Arrays.sort(rangeBounds); //determine which direction the line goes in

                //generate intermediate values
                int[] lineRange = IntStream.rangeClosed(rangeBounds[0], rangeBounds[1]).toArray();

                //fill line
                for(int x : lineRange) {
                    grid[y][x] = true;
                }

            }

            //vertical line
            else if(path.get(i).x == path.get(i-1).x) {

                int x = path.get(i).x;
                int[] rangeBounds = new int[] {path.get(i).y, path.get(i-1).y}; //bounds for line

                Arrays.sort(rangeBounds); //determine which direction the line goes in

                //generate intermediate values
                int[] lineRange = IntStream.rangeClosed(rangeBounds[0], rangeBounds[1]).toArray();

                //fill line
                for(int y : lineRange) {
                    grid[y][x] = true;
                }

            }

        }
        
    }
    
}
