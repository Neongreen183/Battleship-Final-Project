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
		flag = false;
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
	
	@Override
	public boolean fire (Player player, char row, int column){
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			System.out.println("There is already a missle there!");
			return false;
		}

		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			flag = true;
			if(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat() == false){
				System.out.println("A ship has been sunk!");
				player.sinkShip();
				flag = false;
			} 
		}else{
			System.out.println("Miss! nothing was hit.");
		}
		return true; 
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
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum(), ship);
				row++;
			}else{
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum(), ship);
				column++ ;
			}
		}
		return true;
	}
	
	public void randomizer(Player computer, char row, int column) {
		List<Square> random = new ArrayList<Square>();
		ArrayList<ArrayList<Integer>> rand = new ArrayList<ArrayList<Integer>>();
		//rand.add(ArrayList<row,column>);
		random.add(computer.getMyBoard().getSquare(row, column +1));
		random.add(computer.getMyBoard().getSquare(row, column-1));
		random.add(computer.getMyBoard().getSquare((char)(row+1), column));
		random.add(computer.getMyBoard().getSquare((char)(row-1),column));
		
		for (int i=0; i<4;i++) {
			computer.fire(computer, random.get(i).getRow(), random.get(i).getColumn());
			}
		
		
	}
	
	public void uptadeGuesses(char row, int column){
		
	}
	

}

