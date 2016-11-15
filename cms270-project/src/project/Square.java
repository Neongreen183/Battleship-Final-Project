package project;

public class Square {
	
	private boolean ship;
	private boolean missle;
	private int shipNum; 
	private Ship myShip;
	
	public Square(){
		ship = false;
		missle = false;
		shipNum = -1;
	}
	
	public boolean hasShip(){
		return ship;
	}
	public boolean hasMissle(){
		return missle;
	}
	
	public int getShipNum(){
		return shipNum;
	} 
	
	public void placeShip(int num, Ship newShip){ 
		ship = true;
		shipNum = num;
		myShip = newShip;
	}
	public void placeMissle(){
		missle = true;
	}
	
	public Ship getMyShip(){
		return myShip;
		//hello
	}
	
	

}