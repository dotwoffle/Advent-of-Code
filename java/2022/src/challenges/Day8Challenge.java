package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;


/**Day 8 challenge: <a href="https://adventofcode.com/2022/day/8">Treetop Tree House</a>*/
public class Day8Challenge extends Challenge {

    /*Variables*/

    /**The matrix of all the tree heights.*/
    private int[][] treeHeights;


    /*Constructor*/

    public Day8Challenge() throws FileNotFoundException, IOException {

        super(2022, 8);

        //init

        treeHeights = new int[input.get(0).length()][input.size()];

        //load matrix
        for(int y = 0; y < input.size(); y++) {
            for(int x = 0; x < input.get(0).length(); x++) {
                treeHeights[y][x] = Integer.parseInt(input.get(y).charAt(x) + "");
            }
        }

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int visibleCount = 0;

        //count visible trees
        for(int y = 0; y < treeHeights.length; y++) {
            for(int x = 0; x < treeHeights[0].length; x++) {
                if(isVisible(x, y)) {
                    visibleCount++;
                }
            }
        }

        System.out.println(visibleCount);

    }

    @Override
    protected void challengePart2() {
        
        int bestScore = 0;

        //findBestScore
        for(int y = 0; y < treeHeights.length; y++) {
            for(int x = 0; x < treeHeights[0].length; x++) {
                
                int score = calculateScore(x, y);

                //check for better score
                if(score > bestScore) {
                    bestScore = score;
                }

            }
        }

        System.out.println(bestScore);

    }

    @Override
    protected void initialize() {}

    /**Determines if a specifix tree is visible from any grid edge.*/
    private boolean isVisible(int treeX, int treeY) {

        //trees at grid edges are always visible
        if(treeX == 0 || treeY == 0 || treeX == treeHeights[0].length-1 || treeY == treeHeights.length-1) {
            return true;
        }

        boolean[] visibilities = {true, true, true, true}; //visibilities from top, bottom, left and right

        //top edge
        for(int y = treeY-1; y >= 0; y--) {
            if(treeHeights[y][treeX] >= treeHeights[treeY][treeX]) {
                visibilities[0] = false;
                break;
            }
        }

        if(visibilities[0]) {
            return true;
        }

        //bottom edge
        for(int y = treeY+1; y < treeHeights.length; y++) {
            if(treeHeights[y][treeX] >= treeHeights[treeY][treeX]) {
                visibilities[1] = false;
                break;
            }
        }

        if(visibilities[1]) {
            return true;
        }

        //left edge
        for(int x = treeX-1; x >= 0; x--) {
            if(treeHeights[treeY][x] >= treeHeights[treeY][treeX]) {
                visibilities[2] = false;
                break;
            }
        }

        if(visibilities[2]) {
            return true;
        }

        //right edge
        for(int x = treeX+1; x < treeHeights[0].length; x++) {
            if(treeHeights[treeY][x] >= treeHeights[treeY][treeX]) {
                visibilities[3] = false;
                break;
            }
        }

        if(visibilities[3]) {
            return true;
        }

        return false;

    }

    /**Calculates the scenic score for a specific tree.*/
    private int calculateScore(int treeX, int treeY) {

        //trees at grid edges have no score
        if(treeX == 0 || treeY == 0 || treeX == treeHeights[0].length-1 || treeY == treeHeights.length-1) {
            return 0;
        }

        int score = 1;
        int currentVisible = 0;

        //top edge
        for(int y = treeY-1; y >= 0; y--) {

            currentVisible++;

            if(treeHeights[y][treeX] >= treeHeights[treeY][treeX]) {
                break;
            }

        }

        score *= currentVisible;
        currentVisible = 0;

        //bottom edge
        for(int y = treeY+1; y < treeHeights.length; y++) {

            currentVisible++;

            if(treeHeights[y][treeX] >= treeHeights[treeY][treeX]) {
                break;
            }

        }

        score *= currentVisible;
        currentVisible = 0;

        //left edge
        for(int x = treeX-1; x >= 0; x--) {

            currentVisible++;

            if(treeHeights[treeY][x] >= treeHeights[treeY][treeX]) {
                break;
            }

        }

        score *= currentVisible;
        currentVisible = 0;

        //right edge
        for(int x = treeX+1; x < treeHeights[0].length; x++) {

            currentVisible++;

            if(treeHeights[treeY][x] >= treeHeights[treeY][treeX]) {
                break;
            }

        }

        score *= currentVisible;
        currentVisible = 0;

        return score;

    }
    
}
