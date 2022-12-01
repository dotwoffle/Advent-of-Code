package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**Day 1 challenge: <a href="https://adventofcode.com/2022/day/1">Calorie Counting</a>*/
public class Day1Challenge extends Challenge {

    /*Constructor*/

    /**Initialize the challenge.*/
    public Day1Challenge() throws FileNotFoundException, IOException {

        super(2022, 1);

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int maxCals = -1; //max calorie value
        int runningValue = 0; //value for current group

        //find max
        for(int i = 0; i < input.size(); i++) {

            //check for end of group
            if(i == input.size()-1 || input.get(i) == "") {

                //check for higher value
                if(runningValue > maxCals) {
                    maxCals = runningValue;
                }

                runningValue = 0;

            }

            //otherwise add new value to sum
            else {
                runningValue += Integer.parseInt(input.get(i));
            }

        }

        System.out.println(maxCals);
        
    }

    @Override
    protected void challengePart2() {
        
        ArrayList<Integer> calorieCounts = new ArrayList<>(); //sums of all groups of calories
        int runningValue = 0; //value for current group

        //build list
        for(int i = 0; i < input.size(); i++) {

            //check for end of group
            if(i == input.size()-1 || input.get(i) == "") {
                calorieCounts.add(runningValue); //add value to list
                runningValue = 0;
            }

            //otherwise add new value to sum
            else {
                runningValue += Integer.parseInt(input.get(i));
            }

        }

        //sort list and sum top 3
        Collections.sort(calorieCounts);
        int sum = calorieCounts.get(calorieCounts.size()-1) +
            calorieCounts.get(calorieCounts.size()-2) +
            calorieCounts.get(calorieCounts.size()-3);

        System.out.println(sum);
        
    }

    @Override
    protected void initialize() {}
    
}
