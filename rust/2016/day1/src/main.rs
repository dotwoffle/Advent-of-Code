use std::fs;


/**Enum for the four directions.*/
enum Direction {
    North,
    South,
    East,
    West
}

/**Struct to hold an x and y coordinate.*/
struct Coordinate(i32, i32);


fn main() {
    
    part_1();

}

fn part_1() {

    //get input move list
    let moves = fs::read_to_string("..\\..\\..\\inputs\\2016\\day1.txt")
        .expect("Could not read file");
    let moves: Vec<&str> = moves.split(", ").collect();

    println!("{}", do_moves(moves));

}

/**Runs through all of the given moves and determines ending coordinates.
 * Parameters:
 *      moves: The vector of moves.
 * Returns:
 *      The total number of blocks away the final location is from the starting location.
*/
fn do_moves(moves: Vec<&str>) -> i32 {

    let mut direction_facing = Direction::North; //current direction
    let mut location = Coordinate(0, 0); //current location

    //do each move
    for next_move in moves {

        //get rotation value
        let rotation = next_move.chars().next()
            .expect("Could not fetch next character from move!");

        //get move distance
        let move_dist = next_move[1..].to_string();
        let move_dist: i32 = move_dist.parse().unwrap();

        direction_facing = get_new_direction(&direction_facing, rotation); //update direction
        location = get_new_position(&location, &direction_facing, move_dist); //update position

    }

    location.0.abs() + location.1.abs()

}

/**Applies a rotation to the given direction.
 * Parameters:
 *      direction: The current Direction.
 *      rotation: 'R' or 'L', the direction to rotate
 * Returns:
 *      The new direction after rotating.
*/
fn get_new_direction(direction: &Direction, rotation: char) -> Direction {

    match direction {
        Direction::North => if rotation == 'R' {Direction::East} else {Direction::West},
        Direction::South => if rotation == 'R' {Direction::West} else {Direction::East},
        Direction::East => if rotation == 'R' {Direction::South} else {Direction::North},
        Direction::West => if rotation == 'R' {Direction::North} else {Direction::South}
    }

}

/**Updates the position based on the current direction.
 * Parameters:
 *      position: The current position.
 *      direction: The current direction.
 *      distance: The distance to move in the current direction.
 * Returns:
 *      A new location after moving.
*/
fn get_new_position(position: &Coordinate, direction: &Direction, distance: i32) -> Coordinate {

    match direction {
        Direction::North => Coordinate(position.0, position.1 + distance),
        Direction::South => Coordinate(position.0, position.1 - distance),
        Direction::East => Coordinate(position.0 + distance, position.1),
        Direction::West => Coordinate(position.0 - distance, position.1)
    }

}