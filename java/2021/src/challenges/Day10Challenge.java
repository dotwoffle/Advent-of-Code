package challenges;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


/**Day 10 challenge: <a href=https://adventofcode.com/2021/day/10>Syntax Scoring</a>*/
public class Day10Challenge extends Challenge {

	/*******************/
	/*    Variables    */
	/*******************/
	
	/**List of opening "bracket" characters.*/
	private final ArrayList<Character> OPEN_CHARS;
	/**List of closing "bracket" characters.*/
	private final ArrayList<Character> CLOSED_CHARS;
	
	
	/*********************/
	/*    Constructor    */
	/*********************/
	
	public Day10Challenge() throws FileNotFoundException, IOException {
		
		super(2021, 10);
		
		//init
		
		OPEN_CHARS = new ArrayList<>();
		CLOSED_CHARS = new ArrayList<>();
		
		//add bracket characters
		
		OPEN_CHARS.add('(');
		OPEN_CHARS.add('[');
		OPEN_CHARS.add('{');
		OPEN_CHARS.add('<');
		
		CLOSED_CHARS.add(')');
		CLOSED_CHARS.add(']');
		CLOSED_CHARS.add('}');
		CLOSED_CHARS.add('>');
		
	}
	
	
	/*****************/
	/*    Methods    */
	/*****************/

	@Override
	protected void challengePart1() {
		
		int errorTotal = 0; //total score from illegal characters
		
		for(String line : input) {
		
			Stack<Character> syntaxStack = new Stack<>(); //stack for syntax checking
			
			//find first illegal character (if any)
			for(int x = 0; x < line.length(); x++) {
				
				//push opening brackets to stack
				if(OPEN_CHARS.contains(line.charAt(x)))
					syntaxStack.push(line.charAt(x));
				
				//otherwise this is a closing bracket
				else {
					
					//pop opening bracket from stack if it's there
					if(syntaxStack.peek() == getOppositeBracket(line.charAt(x)))
						syntaxStack.pop();
					
					//otherwise syntax error
					else {
						errorTotal += getBracketScore(line.charAt(x), true);
						break;
					}
					
				}
				
			}
		
		}
		
		System.out.println(errorTotal);
		
	}

	@Override
	protected void challengePart2() {
		
		ArrayList<Long> scores = new ArrayList<>(); //scores from all incomplete lines
		
		for(String line : input) {
			
			Stack<Character> syntaxStack = new Stack<>(); //stack for syntax checking
			
			//check line for errors
			for(int x = 0; x < line.length(); x++) {
				
				//push opening brackets to stack
				if(OPEN_CHARS.contains(line.charAt(x)))
					syntaxStack.push(line.charAt(x));
				
				//otherwise this is a closing bracket
				else {
					
					//pop opening bracket from stack if it's there
					if(syntaxStack.peek() == getOppositeBracket(line.charAt(x)))
						syntaxStack.pop();
					
					//otherwise syntax error
					else {
						syntaxStack.clear();
						break;
					}
					
				}
				
			}
			
			//line is incomplete if there are still things in the stack
			if(!syntaxStack.empty()) {
				
				long score = 0; //score for this line
				
				//calculate score from remaining stack characters
				while(!syntaxStack.empty()) {
					
					char nextChar = syntaxStack.pop(); //get next character
					
					//add to score
					score *= 5;
					score += getBracketScore(getOppositeBracket(nextChar), false);
					
				}
				
				scores.add(score); //add score to list
				
			}
		
		}
		
		Collections.sort(scores); //sort scores
		
		System.out.println(scores.get((scores.size() / 2)));
		
	}
	
	/**Gets the opposite bracket for the given character.
	 * @param bracket The character to check.
	 * @return The opposing bracket character.*/
	private char getOppositeBracket(char bracket) {
		if(OPEN_CHARS.contains(bracket))
			return CLOSED_CHARS.get(OPEN_CHARS.indexOf(bracket));
		else
			return OPEN_CHARS.get(CLOSED_CHARS.indexOf(bracket));
	}
	
	/**Gets the error score of the given bracket character.
	 * @param bracket The character to check.
	 * @param part1Scoring Whether to use the scoring method from part 1. If true, uses the scores from part 1, and if
	 * false, uses the scores from part 2.
	 * @return The score for the character.*/
	private int getBracketScore(char bracket, boolean part1Scoring) {
		
		switch(bracket) {
		
			case ')':
				return part1Scoring ? 3 : 1;
			case ']':
				return part1Scoring ? 57 : 2;
			case '}':
				return part1Scoring ? 1197 : 3;
			case '>':
				return part1Scoring ? 25137 : 4;
			default:
				return 0;
		
		}
		
	}

}
