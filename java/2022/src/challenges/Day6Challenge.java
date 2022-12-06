package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

/**Day 6 challenge: <a href="https://adventofcode.com/2022/day/6">Supply Stacks</a>*/
public class Day6Challenge extends Challenge {

    /*Constructor*/

    public Day6Challenge() throws FileNotFoundException, IOException {
        super(2022, 6);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        //loop through characters
        for(int i = 0; i < input.get(0).length()-3; i++) {

            HashSet<Character> uniqueChars = new HashSet<>(); //unique chars in window

            //count chars in window
            for(int j = i; j < i+4; j++) {
                uniqueChars.add(input.get(0).charAt(j));
            }

            //check set size
            if(uniqueChars.size() == 4) {
                System.out.println(i+4);
                break;
            }

        }
        
    }

    @Override
    protected void challengePart2() {
        
        //loop through characters
        for(int i = 0; i < input.get(0).length()-13; i++) {

            HashSet<Character> uniqueChars = new HashSet<>(); //unique chars in window

            //count chars in window
            for(int j = i; j < i+14; j++) {
                uniqueChars.add(input.get(0).charAt(j));
            }

            //check set size
            if(uniqueChars.size() == 14) {
                System.out.println(i+14);
                break;
            }

        }
        
    }

    @Override
    protected void initialize() {}
    
}
