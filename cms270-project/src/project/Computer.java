package project;
import java.util.*;

/**
 * @author Felix Ruiz, and Rene Borr
 * @version 1.0.0
 */
public class Computer extends Player { 
	private Random rand = new Random();
	private int CColumn;
	private char CRow;
	private int turn;
	private List<Square> random = new ArrayList<Square>();

	/**
	 * Initializes the computer class by calling the superclass: Player
	 */
	public Computer() {
		super();
		turn = 0;
	}

	/**
	 * Generates random row selection (between A-J) for the Computer's ships.
	 * 
	 * @return row The row the computer selected randomly.
	 */
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

	/**
	 * Generates random column selection (between 1-10) for the Computer's ships.
	 * 
	 * @return column The column the computer selected randomly.
	 */
	@Override
	public int getColumn(){
		if (turn>0) {
			return CColumn;
		}
		int column;
		column = rand.nextInt(10)+1;
		return column;
	}

	/**
	 * Generates random square for the computer to fire to.
	 * 
	 * @param player The player that is firing the missile.
	 * @param row The row the missile is going to.
	 * @param column The column the missile is going to.
	 * @return Whether the a ship was hit or not.
	 */
	@Override
	public boolean fire (Player player, char row, int column){
		//Checks for out of bounds
		if(turn > 0){
			updateRowAndColumn();
		}
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			return false;
		}
		if(charToNum(row) == -1 || column<1 || column>10){

			return false;
		}
		//Intelligence for the computer to know where to fire to next
		player.getMyBoard().getSquare(row, column).placeMissle();
		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			if(turn == 0){

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

	/**
	 * Randomly places the ships across the board for the computer.
	 * 
	 * @param player Which player is placing the ships.
	 * @param ship Which ship is being placed.
	 * @param row What row the ship is going to.
	 * @param column What column the ship is going to
	 * @param vert Whether the board is vertical or not
	 * @return Whether the placing of the ship was successful.
	 */
	public boolean placeShip(Player player, Ship ship, char row, int column, Boolean vert){
		char test = row;
		//Checks for out of bounds exceptions.
		if(vert==true && getMyBoard().charToNum(row)+ship.getSize()>10){
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

	/**
	 * Remembers the guesses the computer has made so that it will intelligently
	 * hit the right square next once it's found a ship.
	 * 
	 * @param row The row in which a ship was struck.
	 * @param column The column in which a ship was struck.
	 */
	public void updateGuesses(char row, int column) {
		random = new ArrayList<Square>();

		if(column == 10){
			random.add(getMyBoard().getSquare(row, column - 1));
			random.add(getMyBoard().getSquare(row, column - 2));
		}else if(column == 1){
			random.add(getMyBoard().getSquare(row, column + 1));
			random.add(getMyBoard().getSquare(row, column + 2));
		}else{
			random.add(getMyBoard().getSquare(row, column +1));
			random.add(getMyBoard().getSquare(row, column-1));
		}

		if(row == 65 || row == 97){
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row+2),column));

		}else if(row == 74 || row == 106){
			random.add(getMyBoard().getSquare((char)(row-1), column));
			random.add(getMyBoard().getSquare((char)(row-2),column));

		}else{
			random.add(getMyBoard().getSquare((char)(row+1), column));
			random.add(getMyBoard().getSquare((char)(row-1),column));
		}
	}

	/**
	 * Updates which row and column is being fired to.
	 */
	public void updateRowAndColumn(){

		CRow = random.get(turn).getRow();
		CColumn = random.get(turn).getColumn();
		turn++;

		if(turn == 4){
			turn = 0;

		}
	}

	/**
	 * Changes the char (between A-J) to a number.
	 * 
	 * @param c The letter that is being input (between A-J).
	 * @return Number between 1-10 for the location of the row.
	 */
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