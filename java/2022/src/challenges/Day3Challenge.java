package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**Day 3 challenge: <a href="https://adventofcode.com/2022/day/3">Rucksack Reorganization</a>*/
public class Day3Challenge extends Challenge {

    /*Constructor*/


    /**Initializes the challenge.*/
    public Day3Challenge() throws FileNotFoundException, IOException {
        super(2022, 3);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int totalPriority = 0;

        //sum priorities in each bag
        for(String sack : input) {

            int midpoint = sack.length() / 2; //middle index of string
            String[] halves = {sack.substring(0, midpoint), sack.substring(midpoint)}; //split string

            //search for corresponding characters
            for(int i = 0; i < halves[0].length(); i++) {
                if(halves[1].contains("" + halves[0].charAt(i))) {
                    totalPriority += calculatePriority(halves[0].charAt(i));
                    break;
                }
            }

        }

        System.out.println(totalPriority);
        
    }

    @Override
    protected void challengePart2() {
        
        int totalPriority = 0;

        //group each batch together
        for(int i = 0; i < input.size(); i += 3) {

            HashMap<Character, Integer> charCounts = new HashMap<>(); //counts of each char present in the batch
            Character groupBadge = null; //char that is present in all 3 bags

            //find character counts
            for(int elf = 0; elf < 3; elf++) {

                HashSet<Character> uniqueChars = stringToSet(input.get(i+elf)); //get unique chars

                //count characters
                for(Character c : uniqueChars) {

                    if(charCounts.containsKey(c)) {
                        charCounts.put(c, charCounts.get(c) + 1);
                    }
                    else {
                        charCounts.put(c, 1);
                    }

                    //break when desired char is found
                    if(charCounts.get(c) == 3) {
                        groupBadge = c;
                        break;
                    }

                }

                if(groupBadge != null) {
                    break;
                }

            }

            totalPriority += calculatePriority(groupBadge); //add priority

        }

        System.out.println(totalPriority);

    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        
    }

    /**Calculates the priority of a character.*/
    private int calculatePriority(char c) {

        if(c >= 'a' && c <= 'z') {
            return (c - 'a') + 1;
        }
        else if(c >= 'A' && c <= 'Z') {
            return (c - 'A') + 27;
        }

        return 0;

    }

    /**Converts a string into a set containing only the unique characters from that string.*/
    private HashSet<Character> stringToSet(String s) {

        HashSet<Character> result = new HashSet<>();

        //get unique
        for(int i = 0; i < s.length(); i++) {
            result.add(s.charAt(i));
        }

        return result;

    }

}
