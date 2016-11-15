package project;
import java.util.*;


public class Computer extends Player { 
	Random rand = new Random();
	int start = 0;
	int end = 9;
	int CColumn;
	char CRow;
	boolean flag;
	int turn;
	List<Square> random = new ArrayList<Square>();

	public Computer() {
		super();//call to superclass constructor 
		flag = false;
	}

	@Override
	public char getRow(){
		char row;
		if(turn>0){
			return CRow;
		}
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		row = options[rand.nextInt(9)];
		return row;
	}

	@Override
	public int getColumn(){
		if (turn>0) {
			return CColumn;
		}
		int column;
		column = rand.nextInt(10)+1;
		return column;
	}
	
	@Override
	public boolean fire (Player player, char row, int column){
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			//System.out.println("There is already a missle there!");
			return false;
		}
		if(charToNum(row) == -1 || column<1 || column>10){
			turn++;
			return false;
		}
		
		player.getMyBoard().getSquare(row, column).placeMissle();

		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			if(turn == 0)
			{
				updateGuesses(row,column);
			}
			
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			flag = true;
			if(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat() == false){
				System.out.println("A ship has been sunk!");
				turn = 0;
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
	
	public void updateGuesses( char row, int column) {
		if(column == 10){
			random.add(getMyBoard().getSquare(row, column- 1));
			random.add(getMyBoard().getSquare(row, column - 2));
		}
		else if(column == 1){
			random.add(getMyBoard().getSquare(row, column + 1));
			random.add(getMyBoard().getSquare(row, column + 2));
		}
		else{
			random.add(getMyBoard().getSquare(row, column +1));
			random.add(getMyBoard().getSquare(row, column-1));
		}
		
		if(row == 65 || row == 97){
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row+2),column));
			
		}
		else if(row == 74 || row == 106){
			random.add(getMyBoard().getSquare((char)(row-1), column));
			random.add(getMyBoard().getSquare((char)(row-2),column));
			//row++;
		}
		else{
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row-1),column));
		}

		
		
	}
	public void updateRowAndColumn(){
		turn++;
		CRow = random.get(turn).getRow();
		CColumn = random.get(turn).getColumn();
		if(turn == 4){
			turn = 0;
		}
	}
	
	public static int charToNum(char c){
		String alphabet = "ABCDEFGHIJ";
		for(int i=0;i<alphabet.length();i++){
			if (alphabet.charAt(i) == c){
				return i;
			}
		}
		return -1;
	} 
	

}

