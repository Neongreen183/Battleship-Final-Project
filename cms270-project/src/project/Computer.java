package project;
import java.util.*;


public class Computer extends Player {

	public Computer() {
		super(); //call to superclass constructor 

	}
	
	Random rand = new Random();
	int start = 0;
	int end = 10;
	int randNum;
	int randNum2;
	char randColumn;
	boolean flag;
	
	public int getRow() { 
		randNum = rand.nextInt(((end - start) +1)  + start);
		return randNum;
	}
	
	public char getColumn() {
		randNum2 = rand.nextInt(((end - start) +1)  + start);
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		randColumn = options[randNum2];
		return randColumn;
	}
	
	@Override
	public boolean fire (Player player, char row, int column){
		player.getMyBoard().getSquare(row, column).placeMissle();
		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			return true;
		}
		if(!(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat())){
			System.out.println("A ship has been sunk!");
			player.sinkShip();
			return false;
		} 
		else {
			return false;
		}
	}
	
	

	
	
}
