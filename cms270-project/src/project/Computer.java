package project;
import java.util.*;

/**
 * @author Felix Ruiz, and Rene Borr
 * @version 1.0.0
 */
public class Computer extends Player { 
	private Random rand = new Random();
	private int CColumn;
	private char CRow;
	private int turn;
	private List<Square> random = new ArrayList<Square>();

	/**
	 * Initializes the computer class by calling the superclass: Player
	 */
	public Computer() {
		super();
		turn = 0;
	}

	/**
	 * Generates random row selection (between A-J) for the Computer's ships.
	 * 
	 * @return row The row the computer selected randomly.
	 */
	@Override
	public char getRow(){
		char row;
		if(turn>0){
			return CRow;
		}
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		row = options[rand.nextInt(9)];
		return row;
	}

	/**
	 * Randomly decided whether the ship the computer is placing is vertical or not
	 * 
	 * @return i Whether the ship is vertical or not.
	 */
	public boolean getVert(){
		boolean i = rand.nextBoolean();
		return i;
	}

	/**
	 * Generates random column selection (between 1-10) for the Computer's ships.
	 * 
	 * @return column The column the computer selected randomly.
	 */
	@Override
	public int getColumn(){
		if (turn>0) {
			return CColumn;
		}
		int column;
		column = rand.nextInt(10)+1;
		return column;
	}

	/**
	 * Generates random square for the computer to fire to.
	 * 
	 * @param row The row the missile is going to.
	 * @param column The column the missile is going to.
	 * @return Whether the a ship was hit or not.
	 */
	@Override
	public boolean fire (char row, int column){
		boolean success;
		//Checks for out of bounds
		if(turn > 0){
			updateRowAndColumn();
		}
		success = getMyBoard().placeMissle(row, column);
		return success;
	}

	/**
	 * Randomly places the ships across the board for the computer.
	 * 
	 * @param size The size of the ship
	 * @return Whether the placing of the ship was successful.
	 */
	public boolean placeShip(int size){
		char row = getRow();
		int column = getColumn();
		boolean vert = getVert();
		boolean success;
		success = myBoard.placeShip(row, column, vert, size);
		return success;
	}

	/**
	 * Remembers the guesses the computer has made so that it will intelligently
	 * hit the right square next once it's found a ship.
	 * 
	 * @param row The row in which a ship was struck.
	 * @param column The column in which a ship was struck.
	 */
	private void updateGuesses(char row, int column) {
		random = new ArrayList<Square>();

		if(column == 10){
			random.add(getMyBoard().getSquare(row, column - 1));
			random.add(getMyBoard().getSquare(row, column - 2));
		}else if(column == 1){
			random.add(getMyBoard().getSquare(row, column + 1));
			random.add(getMyBoard().getSquare(row, column + 2));
		}else{
			random.add(getMyBoard().getSquare(row, column +1));
			random.add(getMyBoard().getSquare(row, column-1));
		}

		if(row == 65 || row == 97){
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row+2),column));

		}else if(row == 74 || row == 106){
			random.add(getMyBoard().getSquare((char)(row-1), column));
			random.add(getMyBoard().getSquare((char)(row-2),column));

		}else{
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row-1),column));
		}
	}

	/**
	 * Updates which row and column is being fired to.
	 */
	private void updateRowAndColumn(){
		CRow = random.get(turn).getRow();
		CColumn = random.get(turn).getColumn();
		turn++;
		if(turn == 4){
			turn = 0;
		}
	}

	/**
	 * Getter for the computer's board
	 */
	public Board getMyBoard(){
		return myBoard;
	}
}