package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


/**Day 1 challenge: <a href="https://adventofcode.com/2022/day/2">Rock Paper Scissors</a>*/
public class Day2Challenge extends Challenge {

    /*Variables*/

    /**A matrix that details the number of points earned for each combination of player and opponent input. The columns
     * are player input and the rows are opponent input.
     * Indices:
     *  A = X = 0 = Rock
     *  B = Y = 1 = Paper
     *  C = Z = 2 = Scissors
    */
    private final int[][] POINT_MTX = {{4, 8, 3}, {1, 5, 9}, {7, 2, 6}};
    /**A matrix that tells which input the player must throw in order to get a specific outcome. The rows are the
     * opponent input, and the columns are X, Y, or Z for loss, draw, and win. An entry in the matrix indicates which
     * throw the player must make.
    */
    private final String[][] THROW_MTX = {{"Z", "X", "Y"}, {"X", "Y", "Z"}, {"Y", "Z", "X"}};
    /**Map of letters to indices.*/
    private HashMap<String, Integer> letterMap;


    /*Constructor*/

    /**Initializes the challenge.*/
    public Day2Challenge() throws FileNotFoundException, IOException {

        super(2022, 2);

        //init

        letterMap = new HashMap<>();

        //populate map
        letterMap.put("A", 0);
        letterMap.put("B", 1);
        letterMap.put("C", 2);
        letterMap.put("X", 0);
        letterMap.put("Y", 1);
        letterMap.put("Z", 2);

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int totalScore = 0;

        //sum scores
        for(String round : input) {
            String[] results = round.split(" "); //split into opponent and player input
            totalScore += POINT_MTX[letterMap.get(results[0])][letterMap.get(results[1])]; //add round score
        }

        System.out.println(totalScore);
        
    }

    @Override
    protected void challengePart2() {
        
        int totalScore = 0;

        //sum scores
        for(String round : input) {
            String[] results = round.split(" "); //split into opponent and round outcome
            String playerThrow = THROW_MTX[letterMap.get(results[0])][letterMap.get(results[1])]; //determine throw
            totalScore += POINT_MTX[letterMap.get(results[0])][letterMap.get(playerThrow)]; //add round score
        }

        System.out.println(totalScore);

    }

    @Override
    protected void initialize() {}
    
}
