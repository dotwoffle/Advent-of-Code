package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import util.Pair;


/**Day 9 challenge: <a href="https://adventofcode.com/2022/day/9">Rope Bridge</a>*/
public class Day9Challenge extends Challenge {

    /*Variables*/

    private Pair<Integer, Integer> headPos;
    private Pair<Integer, Integer> tailPos;


    /*Constructor*/

    public Day9Challenge() throws FileNotFoundException, IOException {

        super(2022, 9);

        //init

        this.headPos = new Pair<>(0, 0);
        this.tailPos = new Pair<>(0, 0);

    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        ArrayList<Pair<Integer, Integer>> uniqueVisits = new ArrayList<>();
        uniqueVisits.add(new Pair<Integer,Integer>(0, 0));

        for(String move : input) {

            String[] moveParts = move.split(" "); //split move
            int moveDist = Integer.parseInt(moveParts[1]); //get distance to move

            for(int step = 0; step < moveDist; step++) {

                //move head
                switch(moveParts[0]) {

                    case "R":
                        headPos.first++;
                        break;
                    case "L":
                        headPos.first--;
                        break;
                    case "U":
                        headPos.second--;
                        break;
                    case "D":
                        headPos.second++;
                        break;

                }

                if(!knotIsTouchingParent(tailPos, headPos)) {

                    moveKnotToParent(tailPos, headPos);

                    boolean isUnique = true;

                    //insert position if unique
                    for(Pair<Integer, Integer> location : uniqueVisits) {
                        if(location.equalsPair(tailPos)) {
                            isUnique = false;
                            break;
                        }
                    }

                    if(isUnique) {
                        uniqueVisits.add(new Pair<Integer,Integer>(tailPos.first, tailPos.second));
                    }

                }

            }

        }

        System.out.println(uniqueVisits.size());

    }

    @Override
    protected void challengePart2() {
        
        ArrayList<Pair<Integer, Integer>> knots = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> uniqueVisits = new ArrayList<>();
        uniqueVisits.add(new Pair<Integer,Integer>(0, 0));

        for(int i = 0; i < 10; i++) {
            knots.add(new Pair<Integer,Integer>(0, 0));
        }

        for(String move : input) {

            String[] moveParts = move.split(" "); //split move
            int moveDist = Integer.parseInt(moveParts[1]); //get distance to move

            for(int step = 0; step < moveDist; step++) {

                //move head
                switch(moveParts[0]) {

                    case "R":
                        knots.get(0).first++;
                        break;
                    case "L":
                        knots.get(0).first--;
                        break;
                    case "U":
                        knots.get(0).second--;
                        break;
                    case "D":
                        knots.get(0).second++;
                        break;

                }

                for(int i = 1; i < knots.size(); i++) {

                    if(!knotIsTouchingParent(knots.get(i), knots.get(i-1))) {

                        moveKnotToParent(knots.get(i), knots.get(i-1));

                        if(i == knots.size()-1) {

                            boolean isUnique = true;

                            //insert position if unique
                            for(Pair<Integer, Integer> location : uniqueVisits) {
                                if(location.equalsPair(knots.get(i))) {
                                    isUnique = false;
                                    break;
                                }
                            }

                            if(isUnique) {
                                uniqueVisits.add(new Pair<Integer,Integer>(knots.get(i).first, knots.get(i).second));
                            }

                        }

                    }

                }

            }

        }

        System.out.println(uniqueVisits.size());

    }

    @Override
    protected void initialize() {}

    /**Determines if the tail is adjacent to or touching the head.*/
    private boolean knotIsTouchingParent(Pair<Integer, Integer> knot, Pair<Integer, Integer> parent) {
        return !(Math.abs(knot.first - parent.first) > 1 || Math.abs(knot.second - parent.second) > 1);
    }

    /**Moves the tail to catch up with the head.*/
    private void moveKnotToParent(Pair<Integer, Integer> knot, Pair<Integer, Integer> parent) {


        //determine direction
        if(parent.second > knot.second) {
            knot.second++;
        }
        if(parent.second < knot.second) {
            knot.second--;
        }
        if(parent.first < knot.first) {
            knot.first--;
        }
        if(parent.first > knot.first) {
            knot.first++;
        }

    }
    
}
