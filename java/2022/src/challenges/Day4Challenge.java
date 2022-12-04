package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;

/**Day 4 challenge: <a href="https://adventofcode.com/2022/day/2">Camp Cleanup</a>*/
public class Day4Challenge extends Challenge {

    /*Constructor*/

    /**Initializes the challenge.*/
    public Day4Challenge() throws FileNotFoundException, IOException {
        super(2022, 4);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {

        int totalOverlaps = 0;

        //count all subsets
        for(String pair : input) {
            
            //get range strings
            String[] ranges = pair.split(",");
            String[] range1 = ranges[0].split("-");
            String[] range2 = ranges[1].split("-");

            //get range starts and ends
            int r1Start = Integer.parseInt(range1[0]);
            int r2Start = Integer.parseInt(range2[0]);
            int r1End = Integer.parseInt(range1[1]);
            int r2End = Integer.parseInt(range2[1]);

            //check if one range is a subset of the other
            if((r1Start >= r2Start && r1End <= r2End) || (r2Start >= r1Start && r2End <= r1End)) {
                totalOverlaps++;
            }

        }

        System.out.println(totalOverlaps);

    }

    @Override
    protected void challengePart2() {

        int totalOverlaps = 0;

        //count all subsets
        for(String pair : input) {
            
            //get range strings
            String[] ranges = pair.split(",");
            String[] range1 = ranges[0].split("-");
            String[] range2 = ranges[1].split("-");

            //get range starts and ends
            int r1Start = Integer.parseInt(range1[0]);
            int r2Start = Integer.parseInt(range2[0]);
            int r1End = Integer.parseInt(range1[1]);
            int r2End = Integer.parseInt(range2[1]);

            //check if any ranges overlap
            for(int x = r1Start; x <= r1End; x++) {
                if(x >= r2Start && x <= r2End) {
                    totalOverlaps++;
                    break;
                }
            }

        }

        System.out.println(totalOverlaps);

    }

    @Override
    protected void initialize() {}
    
}
