package project;

public class Ship {

	private int size;
	private boolean vertical;
	private char locationRow;
	private int locationColumn;
	private int hits;
	private boolean afloat;
	private int shipNum;

	public void battalion (int num){
		int [] boats = {2,3,3,4,5};
	}
	
	public Ship(int num, int s) {
		shipNum = num;
		size = s;
		hits = 0;
		afloat = true;
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
		
		return hits;
	}
	
	public void hit(){
		hits++;
		if (hits == size) {
			sink();
		}
	}
	
	public void sink() { 
		afloat = false;
	}
	  
	public boolean isAfloat(){
		return afloat;
	}
}