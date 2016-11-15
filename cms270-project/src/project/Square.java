package project;

public class Square {
	
	private boolean ship;
	private boolean missle;
	private int shipNum; 
	private Ship myShip;
	private char row;
	private int column;
	
	public Square(char r, int c){
		ship = false;
		missle = false;
		shipNum = -1;
		row = r;
		column = c;
		myShip = new Ship(-1,1);
	}
	//returns the ship boolean for the square
	public boolean hasShip(){
		return ship;
	}
	//returns the missile boolean for the square
	public boolean hasMissle(){
		return missle;
	}
	//returns the integer value for shipNum
	public int getShipNum(){
		return shipNum;
	} 
	//changes the boolean ship to true and sets shipNum to
	//the parameter value
	public void placeShip(int num, Ship newShip){ 
		ship = true;
		shipNum = num;
	}
	//changes the boolean missile value to true
	public void placeMissle(){
		missle = true;

	}
	// returns myShip Ship for the square
	public Ship getMyShip(){
		return myShip;
	}
	public int getColumn(){
		return column;
	}
	
	public char getRow(){
		return row;
	}
	
	

}