package project;

import java.awt.List;
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
	 * Returns an array of squares to the specified parameter
	 * 
	 * @param row The row of a specified square
	 * @param column The column of a specified square
	 * @param vert Whether the ship on it is vertical or not
	 * @param length The length of the ship
	 * @return Array of squares
	 */
	public Square[] getSquares(char row, int column, Boolean vert, int length){
		Square[] array = new Square[length];
		//Returns each square
		if(vert){
			for(int i = 0; i<length; i++){
				array[i] = board[charToNum(row)+i][column-1];
			}
		}else{
			for(int i = 0; i<length; i++){
				array[i] = board[charToNum(row)][column+i-1];
			}
		} 
		return array;
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
				if(board[i][j].hasMissle() && board[i][j].hasShip() && board[i][j].getMyShip().isAfloat() == false){
					System.out.print(" ! ");
				}
				else if (board[i][j].hasMissle() && board[i][j].hasShip()){

					System.out.print(" X ");
				}
				else if(board[i][j].hasMissle()){
					System.out.print(" O ");
				}else if(board[i][j].hasShip()){
					System.out.print(" S ");
				}else{
					System.out.print(" - ");
				}
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
				if(board[i][j].hasMissle() && board[i][j].hasShip() && board[i][j].getMyShip().isAfloat() == false){
					System.out.print(" ! ");
				}
				else if(board[i][j].hasMissle() && board[i][j].hasShip()){
					System.out.print(" X ");
				}else if(board[i][j].hasMissle()){
					System.out.print(" O ");
				}else{
					System.out.print(" - ");
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
	
	public boolean placeShip(char row, int column, boolean vert, int size){
		if(isValidShip(row,column,vert,size) == false){
			return false;
		}
		Ship ship = new Ship(1,size);
		ships.add(ship);
		for(int i=0; i<ship.getSize();i++ ){
			if(vert == true){
				getSquare(row, column).placeShip(ship.getShipNum(),ship);
				row++;
			}else{
				getSquare(row, column).placeShip(ship.getShipNum(),ship);
				column++ ;
			}
		}
		
		return true;
		
	}
	
	public boolean placeMissle(char row, int column){
		if(isValidMissle(row,column) == false){
			return false;
		}
		
		getSquare(row,column).placeMissle();
		return true;
	}
	
	private boolean isValidShip(char row, int column, boolean vert, int size){
		char test = row;
		for(int i=0;i<size;i++){
			if(vert){

				if(getSquare(test, column).hasShip()==true){
					//System.out.println("There is already a ship there");
					return false;
				}
				test++;
			}else{
				if(getSquare(row, column+i).hasShip()==true){
					//System.out.println("There is already a ship there");
					return false;
				}
			} 
		}
		return true;
	}
	
	private boolean isValidMissle(char row, int column){
		if(charToNum(row) == -1){
			return false;
		}
		else if(column>10 || column<1){
			return false;
		}
		return true;
		
	}
}