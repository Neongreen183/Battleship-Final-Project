package project;

/**
 * @author Jerry Abril, Rene Borr, Roderick Zack, Felix Ruiz
 * @version 1.0.0
 */
public class Ship {

	private int size;
	private boolean vertical;
	private int hits;
	private boolean afloat;
	private int shipNum;

	/**
	 * Initializes a ship to be placed on the board.
	 * 
	 * @param num The number of the ship.
	 * @param s How big the ship is.
	 */
	public Ship(int num, int s) {
		shipNum = num;
		size = s;
		hits = 0;
		afloat = true;
	}

	/**
	 * Returns the size of the ship.
	 * 
	 * @return Size of ship.
	 */
	public int getSize (){
		return size;
	}

	/**
	 * Returns the ship number.
	 * 
	 * @return Number of the ship.
	 */
	public int getShipNum(){
		return shipNum;
	}

	/**
	 * Returns whether the ship is vertically placed or not.
	 * 
	 * @return whether the ship is vertical or not.
	 */
	public boolean isVertical() {
		return vertical;
	}


	/**
	 * Returns how many times the ship has been hit.
	 * 
	 * @return The amount of hits a ship has taken.
	 */
	public int getHits() { 
		return hits;
	}

	/**
	 * Adds a hit to the ship if it's been hit .
	 */
	public void hit(){
		hits++;
		if (hits == size) {
			sink();
		}
	}

	/**
	 * Marks the ship as being sunk .
	 */
	public void sink() { 
		afloat = false;
		
	}

	/**
	 * Returns whether the ship is active or not.
	 * 
	 * @return Whether the ship is afloat or not.
	 */
	public boolean isAfloat(){
		return afloat;
	}
}