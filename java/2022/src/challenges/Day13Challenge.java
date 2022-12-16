package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

/**Day 13 challenge: <a href="https://adventofcode.com/2022/day/13">Distress Signal</a>*/
public class Day13Challenge extends Challenge {

    /*Constructor*/

    public Day13Challenge() throws FileNotFoundException, IOException {
        super(2022, 13);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int orderedSum = 0;

        //compare all pairs
        for(int i = 0; i < input.size(); i += 3) {
            if(comparePackets(input.get(i), input.get(i+1)) == -1) {
                orderedSum += (i/3) + 1;
            }
        }

        System.out.println(orderedSum);

    }

    @Override
    protected void challengePart2() {
        
        //create set of packets ordered by packet comparison function
        TreeSet<String> orderedPackets = new TreeSet<>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return comparePackets(o1, o2);
            }
            
        });

        //add all packets
        for(String packet : input) {
            if(!packet.equals("")) {
                orderedPackets.add(packet);
            }
        }

        //add special packets
        orderedPackets.add("[[2]]");
        orderedPackets.add("[[6]]");

        int idx = 0; //current index
        int idx2 = -1; //index of [[2]] packet
        int idx6 = -1; //index of [[6]] packet

        //find special packets
        for(String packet : orderedPackets) {
            
            if(packet.equals("[[2]]")) {
                idx2 = idx+1;
            }
            else if(packet.equals("[[6]]")) {
                idx6 = idx+1;
            }

            idx++;

        }

        System.out.println(idx2 * idx6);

    }

    @Override
    protected void initialize() {}

    /**Recursively compares two packet lists.
     * @param left The left packet string.
     * @param right The right packet string.
     * @return -1 if the left packet is less than the right, 1 if right is less than left, or 0 if they are not
     * comparable.
    */
    private int comparePackets(String left, String right) {

        //get packet as list
        ArrayList<String> leftItems = splitPacketList(left);
        ArrayList<String> rightItems = splitPacketList(right);

        //compare each item
        for(int i = 0; i < leftItems.size(); i++) {

            //check if right ran out of items
            if(i >= rightItems.size()) {
                return 1;
            }

            //compare ints
            if(Character.isDigit(leftItems.get(i).charAt(0)) && Character.isDigit(rightItems.get(i).charAt(0))) {

                //get integer values
                int leftVal = Integer.parseInt(leftItems.get(i));
                int rightVal = Integer.parseInt(rightItems.get(i));

                if(leftVal < rightVal) {
                    return -1;
                }
                else if(leftVal > rightVal) {
                    return 1;
                }
                else {
                    continue;
                }

            }

            //if only one int, convert it to list
            if(Character.isDigit(leftItems.get(i).charAt(0))) {
                leftItems.set(i, "[" + leftItems.get(i) + "]");
            }
            else if(Character.isDigit(rightItems.get(i).charAt(0))) {
                rightItems.set(i, "[" + rightItems.get(i) + "]");
            }

            int result = comparePackets(leftItems.get(i), rightItems.get(i)); //compare sublists
            
            if(result != 0) {
                return result;
            }

        }

        //left ran out first
        if(leftItems.size() < rightItems.size()) {
            return -1;
        }

        return 0; //packets weren't comparable

    }

    /**Splits a packet list string into its components.*/
    private ArrayList<String> splitPacketList(String packet) {

        int idx = 1; //current index in string
        ArrayList<String> list = new ArrayList<>(); //list of integers and other lists
        Stack<Character> bracketStack = new Stack<>(); //stack to track opening and closing brackets

        while(idx < packet.length()-1) {

            //list type
            if(packet.charAt(idx) == '[') {
                
                int listStart = idx; //index of start of list
                bracketStack.push('['); //begin list

                //find entire list
                while(!bracketStack.isEmpty()) {

                    idx++;

                    //find closing or opening brackets
                    switch(packet.charAt(idx)) {
                        case '[':
                            bracketStack.push('[');
                            break;
                        case ']':
                            bracketStack.pop();
                            break;
                        default:
                            break;
                    }

                }

                list.add(packet.substring(listStart, idx+1));
                idx += 2; //skip comma

            }

            //int type
            else {

                int intStart = idx; //start location of integer

                //find comma
                while(packet.charAt(idx) != ',' && packet.charAt(idx) != ']') {
                    idx++;
                }

                list.add(packet.substring(intStart, idx));
                idx++; //skip comma

            }

        }

        return list;

    }
    
}
