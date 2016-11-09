package project;

public class Ship {

	private int size;
	private boolean vertical;
	private char locationRow;
	private int locationColumn;
	private int hits;
	private boolean afloat;
	private int shipNum;

	
	public Ship(int num) {
		shipNum = num;
	}
	
	public int getSize (){
		return size;
	}
	
	public int getShipNum(){
		return shipNum;
	}
	
	public boolean isVertical() {
		return vertical;
	}
	
	public char getLocationRow() {
		return locationRow;
	}
	
	public int getLocationColumn() { 
		return locationColumn;
	}
	
	public int getHits() { 
		if (hits == size) {
			sink();
		}
		return hits;
	}
	
	public void sink() { 
		afloat = false;
	}
}
