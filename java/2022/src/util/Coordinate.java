package util;

import java.util.Objects;

/**Simple utility class for a coordinate pair.*/
public class Coordinate {
    
    /*Variables*/

    /**The x value of this coordinate.*/
    public int x;
    /**The y value of this coordinate.*/
    public int y;


    /*Constructors*/

    /**Creates a new coordinate at the origin (0, 0).*/
    public Coordinate() {

        //init

        this.x = 0;
        this.y = 0;

    }

    /**Creates a new coordinate at the given location.
     * @param x The initial x value.
     * @param y The initial y value.
    */
    public Coordinate(int x, int y) {

        //init

        this.x = x;
        this.y = y;

    }

    /**Creates a copy of the given coordinate object.
     * @param c The coordinate to copy.
    */
    public Coordinate(Coordinate c) {

        //init

        this.x = c.x;
        this.y = c.y;

    }

    /**Creates a new coordinate at the location specified by a string.
     * @param coordString A string in the form "x,y".
    */
    public Coordinate(String coordString) {

        //init

        String[] coordParts = coordString.split(",");

        this.x = Integer.parseInt(coordParts[0]);
        this.y = Integer.parseInt(coordParts[1]);

    }


    /*Methods*/

    @Override
    public boolean equals(Object o) {

        if(o instanceof Coordinate c) {
            return c.x == this.x && c.y == this.y;
        }

        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
