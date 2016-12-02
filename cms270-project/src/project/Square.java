package project;

import javafx.scene.paint.Color;

/**
 * @author Jerry Abril, Felix Ruiz, Roderick Zak, Rene Borr
 * @version 1.0.0
 */
public class Square {

	private boolean missle;
	private Ship myShip;
	private char row;
	private int column;

	/**
	 * Generates a specific square
	 * 
	 * @param r Row for the square. Between A-J.
	 * @param c Column for the square. Between 1-10.
	 */
	public Square(char r, int c){

		missle = false;
		row = r;
		column = c;
		myShip = null;
	}

	/**
	 * Tells the user whether there is a missile on that square.
	 * 
	 * @return Whether there is a missile on the square or not.
	 */
	public boolean hasMissle(){
		return missle;

	}
	
	/**Places a ship on a specific square
	 * 
	 * @param newShip The ship being placed on that square.
	 */
	public void placeShip(Ship newShip){ 
		myShip = newShip;
	}

	/**
	 * Lets the square know that a missile fell in that location
	 */
	public void placeMissle(){
		missle = true;
		if(myShip != null){
			myShip.hit();
		}
	}

	/**
	 * Returns the ship on the square
	 * 
	 * @return The ship on the square
	 */
	public Ship getMyShip(){
		return myShip;
	}

	/**
	 * Returns the column of the specific square.
	 * 
	 * @return The column of the square. Between 1-10.
	 */
	public int getColumn(){
		return column;
	}

	/**
	 * Returns the row of the specific square.
	 * 
	 * @return The row of the square. Between A-J.
	 */
	public char getRow(){
		return row;
	}

	/**
	 * Returns the character that should be on a specific square
	 * 
	 * @return The character (between !, X, O, S, or -) that goes on a square.
	 */
	public String getChar(){
		if(myShip != null && missle && myShip.isAfloat() == false){
			return "!";
		}
		else if(myShip != null && missle){
			return "X";
		}
		else if(missle){
			return "O";
		}
		else if(myShip != null){
			return "S";
		}
		else {
			String s = "-";
			return s;
		}
	}

	/**
	 * Sets the square color based on the status of the square.
	 * 
	 * @return The square color
	 */
	public String getSquareColor() {

		if(myShip != null && missle && myShip.isAfloat() == false){
			return "#696969";
		}
		else if(myShip != null && missle){
			return "#ff6347";
		}
		else if(missle){
			return "#fff5ee";
		}
		else if(myShip != null){
			return "#c0c0c0";
		}
		else {
			return "#2B65EC";
		}


	}
}