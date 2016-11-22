package project;

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
	/**
	 * Lets the square know that a ship has been placed there
	 * 
	 * @param num The number of the ship that goes in that square
	 * @param newShip The ship that is being placed there.
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
	
	public char getChar(){
		if(myShip != null && missle && myShip.isAfloat() == false){
			return '!';
		}
		else if(myShip != null && missle){
			return 'X';
		}
		else if(missle){
			return 'O';
		}
		else if(myShip != null){
			return 'S';
		}
		else {
			return '-';
		}
	}
}