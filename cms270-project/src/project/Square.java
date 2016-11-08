package project;

public class Square {
	
	private boolean ship;
	private boolean missle;
	
	public Square(){
		ship = false;
		missle = false;
	}
	
	public boolean hasShip(){
		return ship;
	}
	public boolean hasMissle(){
		return missle;
	}
	public void placeShip(){
		ship = true;
	}
	public void placeMissle(){
		missle = true;
	}
	
	

}