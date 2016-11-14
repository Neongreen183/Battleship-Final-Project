package project;
import java.util.*;


public class Computer extends Player { 
	Random rand = new Random();
	int start = 0;
	int end = 9;
	int randNum;
	char randRow;
	boolean flag;

	public Computer() {
		super();//call to superclass constructor 
	}

	@Override
	public char getRow(){
		char row;
		randNum = rand.nextInt(9);
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		row = options[randNum];
		return row;
	}

	@Override
	public int getColumn(){
		int column;
		column = rand.nextInt(10)+1;
		return column;
	}

	public boolean placeShip(Player player, Ship ship, char row, int column, Boolean vert){
		char test = row;
		if(vert==true && charToNum(row)+ship.getSize()>10){
			//System.out.println("Sorry you cannot place this ship there");
			return false;
		}else if(vert==false && column+ship.getSize()>11){
			//System.out.println("Sorry you cannot place this ship here");
			return false;
		}
		for(int i=0;i<ship.getSize();i++){
			if(vert){

				if(player.getMyBoard().getSquare(test, column).hasShip()==true){
					//System.out.println("There is already a ship there");
					return false;
				}
				test++;
			}else{
				if(player.getMyBoard().getSquare(row, column+i).hasShip()==true){
					//System.out.println("There is already a ship there");
					return false;
				}
			} 
		}
		
		for(int i=0; i<ship.getSize();i++ ){
			if(vert == true){
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
				row++;
			}else{
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
				column++ ;
			}
		}
		return true;
	}

}

