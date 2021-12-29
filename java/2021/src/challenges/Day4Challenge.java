package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**Day 4 challenge: <a href=https://adventofcode.com/2021/day/4>Giant Squid</a>*/
public class Day4Challenge extends Challenge {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**The sequence of pulled numbers.*/
	private ArrayList<Integer> numSequence;
	/**A list of Board objects to track.*/
	private ArrayList<Board> boards;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/

	public Day4Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 4);
		
		this.numSequence = new ArrayList<Integer>();
		this.boards = new ArrayList<Board>();
		
		//load number sequence
		for(String num : input.get(0).split(","))
			numSequence.add(Integer.parseInt(num));
		
	}
	
	
	/*********************/
	/*    Board Class    */
	/*********************/
	
	/**Small class to help keep track of boards and their states.*/
	private class Board {
		
		/*******************/
		/*    Variables    */
		/*******************/
		
		/**The width and height of every board.*/
		private static final int BOARD_SIZE = 5;
		/**A 2d array that tracks marked squares on the board.*/
		private boolean[][] markers = new boolean[BOARD_SIZE][BOARD_SIZE];
		/**A 2d array that stores the numbers on the board.*/
		private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
		
		
		/*********************/
		/*    Constructor    */
		/*********************/
		
		/**Creates a new Board object.
		 * @param boardLines A list of 5 strings that contain the numbers for each row of the board.*/
		public Board(ArrayList<String> boardLines) {
			
			//build board
			for(int x = 0; x < BOARD_SIZE; x++) {
				
				String[] rowNums = boardLines.get(x).trim().split("\s+"); //list of numbers in row
				
				//load numbers from row
				for(int y = 0; y < BOARD_SIZE; y++)
					board[x][y] = Integer.parseInt(rowNums[y]);
				
			}
					
		}
		
		
		/*****************/
		/*    Methods    */
		/*****************/
		
		/**Marks the square with the given number. No squares are marked if the number is not on the board.
		 * @param number The number to mark on the board.*/
		public void markSquare(int number) {
			
			for(int x = 0; x < BOARD_SIZE; x++) {
				for(int y = 0; y < BOARD_SIZE; y++) {
					if(board[x][y] == number) {
						markers[x][y] = true;
						break;
					}
				}
			}
						
			
		}
		
		/**Checks if this board is a winner.*/
		public boolean isWinner() {
			
			//check rows
			for(boolean[] row : markers)
				if(allAreTrue(row))
					return true;
			
			//check cols
			for(int y = 0; y < BOARD_SIZE; y++) {
				
				boolean[] col = new boolean[BOARD_SIZE]; //current column
				
				//build column
				for(int x = 0; x < BOARD_SIZE; x++)
					col[x] = markers[x][y];
				
				if(allAreTrue(col))
					return true;
				
			}
			
			//check diagonals
			
			//diagonal arrays
			boolean[] rToLDiagonal = new boolean[BOARD_SIZE];
			boolean[] lToRDiagonal = new boolean[BOARD_SIZE];
			
			//build diagonals
			for(int x = 0; x < BOARD_SIZE; x++) {
				rToLDiagonal[x] = markers[x][BOARD_SIZE-x-1];
				lToRDiagonal[x] = markers[BOARD_SIZE-x-1][x];
			}
			
			if(allAreTrue(lToRDiagonal))
				return true;
			if(allAreTrue(rToLDiagonal))
				return true;
			
			return false;
			
		}
		
		/**Calculates the score of this board.
		 * @param calledNumber The number that was pulled when the score is requested.
		 * @return The final score for this board.*/
		public int getScore(int calledNumber) {
			
			int score = 0; //final score
			
			//sum score
			for(int x = 0; x < BOARD_SIZE; x++)
				for(int y = 0; y < BOARD_SIZE; y++)
					if(!markers[x][y])
						score += board[x][y];
			
			return score * calledNumber;
			
		}
		
		@Override
		public String toString() {
			
			String boardString = "";
			
			for(int x = 0; x < BOARD_SIZE; x++) {
				for(int y = 0; y < BOARD_SIZE; y++) {
					boardString += (markers[x][y] ? "X" : board[x][y]) + " ";
				}
				boardString += "\n";
			}

			return boardString;
			
		}
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		initBoards(); //reset boards
		
		int winningNum = 0; //number that wins the game
		Board winningBoard = null; //board that wins
		
		//pull numbers until a board wins
		for(int num : numSequence) {
			
			//check each board
			for(Board board : boards) {
				
				board.markSquare(num); //mark board
				
				//check for a win
				if(board.isWinner()) {
					winningNum = num;
					winningBoard = board;
					break;
				}
				
			}
			
			//stop if a winner is found
			if(winningBoard != null)
				break;
			
		}
		
		System.out.println(winningBoard.getScore(winningNum));

	}

	@Override
	protected void challengePart2() {
		
		initBoards(); //reset boards
		
		ArrayList<Board> remainingBoards = boards; //list of boards that haven't won yet
		
		//pull numbers until no boards are left
		for(int num : numSequence) {
			
			ArrayList<Board> newBoards = new ArrayList<Board>(); //new boards minus winners
			
			//check each board
			for(Board board : remainingBoards) {
				
				board.markSquare(num); //mark board
				
				//check for live boards
				if(!board.isWinner())
					newBoards.add(board);
				
			}
			
			//check for last win
			if(newBoards.size() == 0) {
				
				if(remainingBoards.size() == 1) {
					System.out.println(remainingBoards.get(0).getScore(num));
					break;
				}
				else {
					System.out.println(remainingBoards.get(remainingBoards.size()-1).getScore(num));
					break;
				}
			}
			
			remainingBoards = newBoards; //update remaining boards
				
			
		}

	}
	
	/**Loads all the boards into their initial states.*/
	private void initBoards() {
		
		boards.clear();
		
		ArrayList<String> rows = new ArrayList<String>(); //list of rows for current board
		
		//build boards
		for(int x = 2; x < input.size(); x++) {
			
			if(!input.get(x).equals(""))
				rows.add(input.get(x));
				
			if(input.get(x).equals("") || x == input.size()-1) {
				boards.add(new Board(rows));
				rows.clear();
			}
			
		}
		
	}
	
	/**Checks if all the booleans in the given list are true.
	 * @param boolList The list to check.
	 * @return True if every element in the list is true, false otherwise.*/
	private boolean allAreTrue(boolean[] boolList) {
		
		for(boolean element : boolList)
			if(!element)
				return false;
		
		return true;
		
	}

}
