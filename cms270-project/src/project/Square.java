package project;

/**
 * @author Jerry Abril, Felix Ruiz, Roderick Zak, Rene Borr
 * @version 1.0.0
 */
public class Square {

	private boolean ship;
	private boolean missle;
	private int shipNum; 
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
		ship = false;
		missle = false;
		shipNum = -1;
		row = r;
		column = c;
		myShip = new Ship(-1,1);
	}

	/**
	 * Tells the user whether there is a ship on that square.
	 * 
	 * @return Whether there is a ship on the square or not.
	 */
	public boolean hasShip(){
		return ship;
	}

	/**
	 * Tells the user whether there is a missile on that square.
	 * 
	 * @return Whether there is a missile on the square or not.
	 */
	public boolean hasMissle(){
		return missle;
	}

	/**
	 * Returns the ship number
	 * 
	 * @return The ship being used in that current square.
	 */
	public int getShipNum(){
		return shipNum;
	} 

	/**
	 * Lets the square know that a ship has been placed there
	 * 
	 * @param num The number of the ship that goes in that square
	 * @param newShip The ship that is being placed there.
	 */
	public void placeShip(int num, Ship newShip){ 
		ship = true;
		shipNum = num;
		myShip = newShip;
	}
	
	/**
	 * Lets the square know that a missile fell in that location
	 */
	public void placeMissle(){
		missle = true;
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
}