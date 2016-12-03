package project;

import java.util.ArrayList;

/**
 * @author Jerry Abril, Rene Borr, Roderick Zak, Felix Ruiz
 * @version 1.0.0
 */
public class Board {

	// For this board: 
	// S = ship
	// X = hit
	// O = miss
	// - = Empty place

	private Square[][] board;
	private ArrayList<Ship> ships = new ArrayList<Ship>();

	/**
	 * Initializes the board.
	 */
	public Board() {
		board = new Square[10][10];
		Square square;
		//Initializes the board
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				square = new Square(numToChar(i),j+1);
				board[i][j] = square;
			}
		} 
	}

	/**
	 * Returns square based on its location on the board.
	 * 
	 * @param row The row of a specific square.
	 * @param column The column of a specific square.
	 * @return Square value at a specific location.
	 */
	public Square getSquare(char row, int column){
		return board[charToNum(row)][column-1];
	}

	/**
	 * Overloading previous method in case something is different.
	 * 
	 * @param row The row of a specific square.
	 * @param column The column of a specific square.
	 * @return Square value at a specific location.
	 */
	public Square getSquare(int row, int column){
		return board[row][column];
	}

	/**
	 * Displays the board for the owner.
	 */
	public void displayWithShips(){
		System.out.println("  1  2  3  4  5  6  7  8  9  10 ");
		String alphabet = "ABCDEFGHIJ";

		//Loops through every square and prints: -,O,X,S, or !, according to what goes there.
		for(int i = 0;i<10;i++){
			System.out.print(alphabet.charAt(i));
			for(int j = 0;j<10;j++){
				System.out.print(" " + board[i][j].getChar() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Displays the board for opponent view.
	 */
	public void displayWithoutShips(){
		System.out.println("  1  2  3  4  5  6  7  8  9  10 ");
		String alphabet = "ABCDEFGHIJ";

		//Loops through every square and prints: -,O,X,S, or !, according to what goes there.
		for(int i = 0;i<10;i++){
			System.out.print(alphabet.charAt(i));
			for(int j = 0;j<10;j++){
				if(board[i][j].getChar().equalsIgnoreCase("s")){
					System.out.print(" - ");
				}else{
					System.out.print(" " + board[i][j].getChar() + " ");
				}
			}
			System.out.println("");
		}
	}

	/**
	 * Changes the char (between A-J) to a number.
	 * 
	 * @param c The letter that is being input (between A-J).
	 * @return Number between 1-10 for the location of the row.
	 */
	public int charToNum(char c){
		String alphabet = "ABCDEFGHIJ";
		for(int i=0;i<alphabet.length();i++){
			if (alphabet.charAt(i) == c){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the original char that was used for a coordinate.
	 * 
	 * @param i The number that represents a letter.
	 * @return The letter at the location of the int.
	 */
	public static char numToChar(int i){
		String alphabet = "ABCDEFGHIJ";
		return alphabet.charAt(i);
	}

	/**
	 * Places the actual ships on the board by calling the ship class
	 * 
	 * @param row The row of where the ship goes
	 * @param column The column where the ship goes
	 * @param vert Whether the ship is vertical or not
	 * @param size The size of the ship
	 * @return Whether the placing was successful at doing so.
	 */
	public boolean placeShip(char row, int column, boolean vert, int size){
		if(isValidShip(row,column,vert,size) == false){
			return false;
		}
		Ship ship = new Ship(1,size);
		ships.add(ship);
		for(int i=0; i<ship.getSize();i++ ){
			if(vert == true){
				getSquare(row, column).placeShip(ship);
				row++;
			}else{
				getSquare(row, column).placeShip(ship);
				column++ ;
			}
		}
		return true;
	}

	/**
	 * Places a missile on a specific part of the board
	 * 
	 * @param row The row where the missile is going
	 * @param column The column where the missile is going
	 * @return Whether it was successful or not.
	 */
	public boolean placeMissle(char row, int column){
		if(isValidMissle(row,column) == false){
			return false;
		}

		getSquare(row,column).placeMissle();
		return true;
	}

	/**
	 * Checks to see whether the ship can be placed at the desired location.
	 * 
	 * @param row The row of the square
	 * @param column The column of the square
	 * @param vert Whether the ship on it is vertical
	 * @param size The size of the ship
	 * @return Whether it was possible or not.
	 */
	private boolean isValidShip(char row, int column, boolean vert, int size){
		char test = row;
		if(vert){
			if(charToNum(row)+size>10){
				return false;
			}
		}else{
			if(column+size>11){
				return false;
			}
		}
		for(int i=0;i<size;i++){
			if(vert){
				if(getSquare(test, column).getMyShip() != null){
					//System.out.println("There is already a ship there");
					return false;
				}
				test++;
			}else{
				if(getSquare(row, column+i).getMyShip() !=null ){
					//System.out.println("There is already a ship there");
					return false;
				}
			} 
		}
		return true;
	}

	/**
	 * Checks to see whether the missile on the square is valid
	 * 
	 * @param row The row of the square 
	 * @param column The column of the square
	 * @return Whether it's valid or not
	 */
	private boolean isValidMissle(char row, int column){
		if (getSquare(row,column).hasMissle()){
			return false;
		}
		return true;

	}

	/**
	 * A method for holding all the ships and accessing them.
	 * 
	 * @return ArrayList of all the ships a player has.
	 */
	public ArrayList<Ship> getShips(){
		return ships;
	}

	/**
	 * How many ships the player has left.
	 * 
	 * @return How many ships the player has left.
	 */
	public int getShipsLeft(){
		int shipsLeft = 0;
		for(int i=0;i<ships.size();i++){
			if(ships.get(i).isAfloat() == true){
				shipsLeft++;
			}
		}
		return shipsLeft;
	}
	
	/**
	 * Generates a square on the board
	 * 
	 * @return The board made up of squares.
	 */
	public Square[][] getBoard(){
		return board;
	}
}