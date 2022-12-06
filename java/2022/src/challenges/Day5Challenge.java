package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


/**Day 5 challenge: <a href="https://adventofcode.com/2022/day/5">Supply Stacks</a>*/
public class Day5Challenge extends Challenge {

    /*Variables*/

    /**List of stacks for each platform.*/
    private ArrayList<Stack<Character>> crateStacks;
    /**List of each move. Moves are stored as an array of 3 integers, ex. move [0] from [1] to [2]*/
    private ArrayList<Integer[]> moves;


    /*Constructor*/

    public Day5Challenge() throws FileNotFoundException, IOException {
        super(2022, 5);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        //do moves
        for(Integer[] move : moves) {
            for(int i = 0; i < move[0]; i++) {
                crateStacks.get(move[2]-1).push(crateStacks.get(move[1]-1).pop());
            }
        }

        //print message
        for(Stack<Character> stack : crateStacks) {
            System.out.print(stack.peek());
        }

        System.out.println();
        
    }

    @Override
    protected void challengePart2() {

        //do moves
        for(Integer[] move : moves) {

            ArrayList<Character> movedCrates = new ArrayList<>(); //temp storage for batched crates

            //store batch of crates
            for(int i = 0; i < move[0]; i++) {
                movedCrates.add(crateStacks.get(move[1]-1).pop());
            }

            //push crates in reverse order
            for(int i = movedCrates.size()-1; i >= 0; i--) {
                crateStacks.get(move[2]-1).push(movedCrates.get(i));
            }

        }
        
        //print message
        for(Stack<Character> stack : crateStacks) {
            System.out.print(stack.peek());
        }

        System.out.println();
        
    }

    @Override
    protected void initialize() {
        
        moves = new ArrayList<>();
        crateStacks = new ArrayList<>();
        ArrayList<String> crateStrings = new ArrayList<>(); //input strings up until move list
        int idx = 0; //current index in input

        //gather crates
        while(input.get(idx) != "") {
            crateStrings.add(input.get(idx));
            idx++;
        }

        idx++; //skip newline

        //gather moves
        for(int i = idx; i < input.size(); i++) {

            String[] moveParts = input.get(i).split(" ");

            //get move numbers
            moves.add(new Integer[] {
                Integer.parseInt(moveParts[1]),
                Integer.parseInt(moveParts[3]),
                Integer.parseInt(moveParts[5])
            });

        }

        initCrates(crateStrings); //set up crates
        
    }

    /**Initializes the crates list.
     * @param A list of strings that is the beginning of the input file up until the blank line.
    */
    private void initCrates(ArrayList<String> crateStrings) {

        String idxString = crateStrings.get(crateStrings.size()-1); //string with stack numbers
        int numStacks = Integer.parseInt("" + idxString.charAt(idxString.length()-2)); //get number of stacks

        //put crates in respective stacks
        for(int i = 0; i < numStacks; i++) {

            crateStacks.add(new Stack<>()); //add new stack

            //check for crates in this stack in each line
            for(int j = crateStrings.size()-2; j >= 0; j--) {
                if(crateStrings.get(j).charAt((i*4) + 1) != ' ') { //skip spaces
                    crateStacks.get(i).push(crateStrings.get(j).charAt((i*4) + 1));
                }
            }

        }

    }
    
}
