package project;
import java.util.*;

/**
 * @author Felix Ruiz, and Rene Borr
 * @version 1.0.0
 */
public class Computer extends Player { 
	Random rand = new Random();
	int CColumn;
	char CRow;

	int turn;
	List<Square> random = new ArrayList<Square>();

	public Computer() {
		super();//call to superclass constructor 
		turn = 0;
	}

	@Override
	public char getRow(){
		//updateRowAndColumn();
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
		//updateRowAndColumn();
		if (turn>0) {
			return CColumn;
		}
		int column;
		column = rand.nextInt(10)+1;
		return column;
	}

	@Override
	public boolean fire (Player player, char row, int column){
		if(turn > 0){
			updateRowAndColumn();
		}
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			return false;
		}
		if(charToNum(row) == -1 || column<1 || column>10){

			return false;
		}

		player.getMyBoard().getSquare(row, column).placeMissle();

		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			if(turn == 0){
				random = new ArrayList<Square>();
				updateGuesses(row,column);
				updateRowAndColumn();
			}



			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();

			if(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat() == false){
				System.out.println("A ship has been sunk!");
				turn = 0;
				player.sinkShip();

			} 
		}else{
			System.out.println("Miss! nothing was hit.");

		}

		return true; 
	}


	public boolean placeShip(Player player, Ship ship, char row, int column, Boolean vert){
		char test = row;
		if(vert==true && charToNum(row)+ship.getSize()>10){

			return false;
		}else if(vert==false && column+ship.getSize()>11){

			return false;

		}
		for(int i=0;i<ship.getSize();i++){
			if(vert){

				if(player.getMyBoard().getSquare(test, column).hasShip()==true){

					return false;
				}
				test++;
			}else{
				if(player.getMyBoard().getSquare(row, column+i).hasShip()==true){

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

	public void updateGuesses(char row, int column) {

		if(column == 10){
			random.add(getMyBoard().getSquare(row, column - 1));
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

		}
		else{
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row-1),column));
		}
	}

	public void updateRowAndColumn(){

		CRow = random.get(turn).getRow();
		CColumn = random.get(turn).getColumn();
		turn++;
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