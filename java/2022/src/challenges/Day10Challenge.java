package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;


/**Day 10 challenge: <a href="https://adventofcode.com/2022/day/10">Cathode-Ray Tube</a>*/
public class Day10Challenge extends Challenge {

    /*Constructor*/

    public Day10Challenge() throws FileNotFoundException, IOException {
        super(2022, 10);
    }


    /*Methods*/

    @Override
    protected void challengePart1() {
        
        int cycle = 0; //current cycle
        int x = 1; //current value of x register
        int sum = 0; //sum of x values at specific cycles

        //do each instruction
        for(String instruction : input) {

            String[] instructionParts = instruction.split(" "); //split line

            //process addx
            if(instructionParts[0].equals("addx")) {

                //if interesting cycle would occur during this instruction, add old value of x
                if((cycle + 1) % 40 == 20) {
                    sum += (cycle + 1) * x;
                }
                else if((cycle + 2) % 40 == 20) {
                    sum += (cycle + 2) * x;
                }

                x += Integer.parseInt(instructionParts[1]); //update x
                cycle += 2;

            }

            //nop
            else {

                cycle++;

                //check for interesting cycle
                if(cycle % 40 == 20) {
                    sum += cycle * x;
                }

            }

        }

        System.out.println(sum);

    }

    @Override
    protected void challengePart2() {
        
        int cycle = 0; //current cycle
        int x = 1; //current value of x register

        //"screen" to write on
        String[] crtScreen = {
            "........................................",
            "........................................",
            "........................................",
            "........................................",
            "........................................",
            "........................................"
        };

        //do each instruction
        for(String instruction : input) {

            String[] instructionParts = instruction.split(" "); //split line

            //nop
            if(instructionParts[0].equals("noop")) {
                drawPixel(x, cycle, crtScreen); //draw pixel if cycle location lies inside sprite
                cycle++;
            }

            //addx
            else if(instructionParts[0].equals("addx")) {

                //draw pixels and increment cycles
                drawPixel(x, cycle, crtScreen);
                cycle++;
                drawPixel(x, cycle, crtScreen);
                cycle++;

                x += Integer.parseInt(instructionParts[1]); //increment x

            }

        }

        for(String row : crtScreen) {
            System.out.println(row);
        }

    }

    @Override
    protected void initialize() {
        
    }

    /**Draws a pixel if the cycle lies inside the sprite.*/
    private void drawPixel(int x, int cycle, String[] crtScreen) {

        int moddedCycle = cycle % 240; //current location on screen
        int replaceIdx = moddedCycle % 40; // index of char to replace

        if(replaceIdx >= x-1 && replaceIdx <= x+1) {

            int replaceRow = moddedCycle / 40; //index of row to modify

            //draw pixel
            crtScreen[replaceRow] = crtScreen[replaceRow].substring(0, replaceIdx) +
                "#" +
                crtScreen[replaceRow].substring(replaceIdx+1);

        }

    }
    
}
