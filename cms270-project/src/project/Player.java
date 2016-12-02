package project;
import java.util.Scanner;

/**
 * @author Rene Borr, Roderick Zack, Jerry Abril, Felix Ruiz
 * @version 1.0.0
 */
public class Player {

	//private Ship[] myShips;
	private String playerName;
	protected Board myBoard;
	private Scanner scan;

	/**
	 * Creates a default player named computer and generates their ships.
	 */
	public Player() {
		playerName = "Computer";
		myBoard = new Board();
	}

	/**
	 * Creates a player with a given name and scanner to take input.
	 * 
	 * @param name The player's name.
	 * @param s Takes input throughout the code.
	 */
	public Player(String name, Scanner s) {
		this(name);
		scan = s;
		myBoard = new Board();
	}

	/**
	 * Creates a player and generates the ships that they will utilize.
	 * 
	 * @param name The name for the player.
	 */
	public Player(String name) {
		this.playerName=name;
		
		myBoard = new Board();

	}

	/**
	 * Returns the player's name.
	 * 
	 * @return The name of the player.
	 */
	public String getName(){
		return playerName;
	}

	/**
	 * Returns how many ships the player has left.
	 * 
	 * @return The number of ships the player has left.
	 */
	public int getShipsLeft(){
		return myBoard.getShipsLeft();
	}
	

	/**
	 * Returns the board
	 * 
	 * @return The board with all the ships, and fired shots.
	 */
	public Board getMyBoard(){
		return myBoard;
	}


	/**
	 * Decrements the number of ships after one is sunk.
	 */

	/**
	 * Utilized by the player to fire shots to the opponent and play the game.
	 * 
	 * @param player Which player is firing the missile
	 * @param row Which row (A-J) to fire at 
	 * @param column Which column (1-10) to fire at
	 * @return Whether the shot was successful or not
	 */
	public boolean fire (char row, int column){
		boolean success;
		success = myBoard.placeMissle(row, column);
		return success;
	}

	/**
	 * Row in which the player will place their ship
	 * 
	 * @return The row in which the player placed their ship. Between A-J.
	 */
	public char getRow(){
		String line;
		char row;
		do{
			System.out.print("Please select a row (A-J): " );
			line = scan.nextLine();
		}while (line.isEmpty());
		row = line.toUpperCase().charAt(0);

		while(getMyBoard().charToNum(row) == -1){
			System.out.println("Sorry the row must be A-J");
			System.out.print("Please select a row (A-J): " );
			row = scan.nextLine().toUpperCase().charAt(0);
		}
		return row;
	}

	/**
	 * Column in which the player will place their ship
	 * 
	 * @return The column in which the player placed their ship. Between 1-10.
	 */
	public int getColumn(){
		String stringColumn;
		int column;
		boolean flag = true;
		do{
			System.out.print("Please select a column (1-10): ");
			stringColumn = scan.nextLine();
		}while(stringColumn.isEmpty());
		Character c = stringColumn.charAt(0);

		if(c.isDigit(c) == false){
			flag = false;
			column = -1;
		}
		else{

			column = Integer.parseInt(stringColumn.substring(0, 1));
			if(stringColumn.length()>1){
				column = Integer.parseInt(stringColumn.substring(0,2));
			}
			if(column>10 || column <1){
				flag = false;
			}
		}
		while(flag == false){
			flag = true;
			System.out.println("Sorry, the column must be a number 1-10");
			System.out.print("Please select a column (1-10): " );
			stringColumn = scan.nextLine();
			c = stringColumn.charAt(0);
			if(c.isDigit(c) == false){
				flag = false;
				column = -1;
			}
			else{
				column = Integer.parseInt(stringColumn.substring(0, 1));
				if(stringColumn.length()>1){
					c = stringColumn.charAt(1);
					if(c.isDigit(c)){
						column = Integer.parseInt(stringColumn.substring(0,2));
					}
				}
				if(column>10 || column <1){
					flag = false;
				}
			}
		}
		return column;
	}
	
	public boolean getVert(){
		boolean vert;
		System.out.print("Vertical or Horizontal? Please enter V or H: ");
		char playerAns = scan.nextLine().toLowerCase().charAt(0);
		//Checks to make sure that the player doesn't put wrong input.
		while(playerAns != 'h' && playerAns != 'v'){
			System.out.print("Please enter the letter 'V' or 'H': ");
			playerAns = scan.nextLine().toLowerCase().charAt(0);
		}
		if(playerAns == 'v'){
			vert = true;
		}else{
			vert = false;
		}
		return vert;
	}
	
	

	

	/**
	 * Places a ship for a player where they desire it.
	 * 
	 * @param player The player that is placing the ship
	 * @param ship Ship number being placed
	 * @param row The row where it's being placed. Between A-J.
	 * @param column The column where it's being placed. Between 1-10.
	 * @param vert Whether the ship is vertical or horizontal.
	 * @return Whether it was successful or not to place the ship.
	 */
	public boolean placeShip(int size){
		char row = getRow();
		int column = getColumn();
		boolean vert = getVert();
		boolean success;
		success = myBoard.placeShip(row, column, vert, size);
		if(success == true){
			myBoard.displayWithShips();
		}
		return success;
	}
	
	public boolean placeShip(int size, char row, int column, boolean vert){
		boolean success;
		success = myBoard.placeShip(row, column, vert, size);
		return success;
	}
	
	//Test
}