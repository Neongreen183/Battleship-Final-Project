package project;

public class Square {
	
	private boolean ship;
	private boolean missle;
	private int shipNum;
	
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
	
	public int getShipNum(){
		return shipNum;
	}
	
	public void placeShip(int num){
		ship = true;
		shipNum = num;
	}
	public void placeMissle(){
		missle = true;
	}
	
	

}