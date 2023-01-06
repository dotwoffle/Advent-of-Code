package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import util.Pair;

/**Day 11 challenge: <a href="https://adventofcode.com/2022/day/11">Monkey in the Middle</a>*/
public class Day11Challenge extends Challenge {

    /*Monke Class*/

    /**Class for return to monke*/
    private class Monke {

        /*Variables*/

        /**Value to test divisibility of items against.*/
        private int testValue;
        /**Index of monke to throw item to if divisibility test passes.*/
        private int trueMonke;
        /**Index of monke to throw item to if divisibility test fails.*/
        private int falseMonke;
        /**How many items this monke has inspected.*/
        public long inspectCount;
        /**Queue of items for this monke.*/
        private Queue<Integer> itemQueue;
        /**The operation to perform on items. The first entry is multiplication or addition, and the second is the
         * value given for the operation.
        */
        private Pair<String, Integer> operation;


        /*Constructor*/

        /**Initializes the monke.*/
        public Monke(int testValue, int trueMonke, int falseMonke, String opType, int opValue) {

            //init

            this.testValue = testValue;
            this.trueMonke = trueMonke;
            this.falseMonke = falseMonke;
            this.inspectCount = 0;
            this.itemQueue = new LinkedList<>();
            this.operation = new Pair<>(opType, opValue);

        }


        /*Methods*/

        /**Adds an item to this monke's item queue.*/
        public void addItem(int item) {
            itemQueue.add(item);
        }

        /**Returns true if this monke has an item to check.*/
        public boolean hasItem() {
            return !itemQueue.isEmpty();
        }

        /**Performs the process of inspecting and throwing one item in the queue.*/
        public Pair<Integer, Integer> inspectAndThrowNextItem(boolean decreaseWorry) {

            inspectCount++;
            int item = itemQueue.remove(); //take item from queue

            //apply operation
            item = switch (operation.first) {
                case "*" -> item * operation.second;
                case "+" -> item + operation.second;
                case "square" -> item * item;
                default -> item;
            };

            if(decreaseWorry) {
                item /= 3; //decrease worry
            }

            return new Pair<Integer, Integer>(item % testValue == 0 ? trueMonke : falseMonke, item);

        }

    }


    /*Variables*/

    /**List of monkes.*/
    private ArrayList<Monke> monkes;


    /*Constructor*/

    public Day11Challenge() throws FileNotFoundException, IOException {
        super(2022, 11);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        //do 20 rounds
        for(int round = 0; round < 20; round++) {
            for(Monke monke : monkes) {

                while(monke.hasItem()) {
                    Pair<Integer, Integer> inspectResult = monke.inspectAndThrowNextItem(true); //monke inspects item
                    monkes.get(inspectResult.first).addItem(inspectResult.second);
                }

            }
        }

        ArrayList<Long> monkeBusiness = new ArrayList<>(); //total inspection counts for each monke

        //add inspection counts
        for(Monke monke : monkes) {
            monkeBusiness.add(monke.inspectCount);
        }

        Collections.sort(monkeBusiness, Collections.reverseOrder()); //sort list

        System.out.println(monkeBusiness.get(0) * monkeBusiness.get(1));
        
    }

    @Override
    protected void challengePart2() {
        
        // //do 10000 rounds
        // for(int round = 0; round < 10000; round++) {
        //     for(Monke monke : monkes) {

        //         while(monke.hasItem()) {
        //             Pair<Integer, BigInteger> inspectResult = monke.inspectAndThrowNextItem(false); //monke inspects item
        //             monkes.get(inspectResult.first).addItem(inspectResult.second);
        //         }

        //     }
        // }

        // ArrayList<Long> monkeBusiness = new ArrayList<>(); //total inspection counts for each monke

        // //add inspection counts
        // for(Monke monke : monkes) {
        //     monkeBusiness.add(monke.inspectCount);
        // }

        // Collections.sort(monkeBusiness, Collections.reverseOrder()); //sort list

        // System.out.println(monkeBusiness.get(0) * monkeBusiness.get(1));

    }

    @Override
    protected void initialize() {

        monkes = new ArrayList<>(); //reset monkey list

        //load monkey list
        for(int i = 0; i < input.size(); i += 7) {

            //get monke info
            String[] opParts = input.get(i+2).strip().split(" ");
            String opType = opParts[5].equals("old") ? "square" : opParts[4];
            int opValue = opParts[5].equals("old") ? -1 : Integer.parseInt(opParts[5]);
            int testValue = Integer.parseInt(input.get(i+3).strip().split(" ")[3]);
            int trueMonke = Integer.parseInt(input.get(i+4).strip().split(" ")[5]);
            int falseMonke = Integer.parseInt(input.get(i+5).strip().split(" ")[5]);

            Monke monke = new Monke(testValue, trueMonke, falseMonke, opType, opValue); //create monke

            //fill monke with items
            for(String item : input.get(i+1).split(": ")[1].split(", ")) {
                monke.addItem(Integer.parseInt(item));
            }

            monkes.add(monke);

        }

    }
    
}