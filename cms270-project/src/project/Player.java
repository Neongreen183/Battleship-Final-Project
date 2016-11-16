package project;
import java.util.Scanner;

/**
 * @author Rene Borr, Roderick Zack, Jerry Abril, Felix Ruiz
 * @version 1.0.0
 */
public class Player {

	private int shipsLeft;
	private Ship[] myShips;
	private String playerName;
	private Board myBoard;
	private Scanner scan;

	/**
	 * Creates a default player named computer and generates their ships.
	 */
	public Player() {
		playerName = "Computer";
		shipsLeft = 5;
		myBoard = new Board();
		myShips = new Ship[5];
		myShips[0]=new Ship(0,2);
		myShips[1]=new Ship(1,3);
		myShips[2]=new Ship(2,3);
		myShips[3]=new Ship(3,4);
		myShips[4]=new Ship(4,5);
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
	}

	/**
	 * Creates a player and generates the ships that they will utilize.
	 * 
	 * @param name The name for the player.
	 */
	public Player(String name) {
		this.playerName=name;
		this.shipsLeft=5; 
		myBoard = new Board();
		myShips = new Ship[5];
		myShips[0]=new Ship(0,2);
		myShips[1]=new Ship(1,3);
		myShips[2]=new Ship(2,3);
		myShips[3]=new Ship(3,4);
		myShips[4]=new Ship(4,5);
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
		return shipsLeft;
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
	 * Returns the ships that the player has
	 * 
	 * @return Ships the player has.
	 */
	public Ship[] getMyships(){
		return myShips;
	}

	/**
	 * Returns a specific ship
	 * 
	 * @param num The number of the ship
	 * @return The specified ship
	 */ 
	public Ship getShip(int num){
		return myShips[num];
	}

	/**
	 * Decrements the number of ships after one is sunk.
	 */
	public void sinkShip(){
		shipsLeft--;
	}
	
	/**
	 * Utilized by the player to fire shots to the opponent and play the game.
	 * 
	 * @param player Which player is firing the missile
	 * @param row Which row (A-J) to fire at 
	 * @param column Which column (1-10) to fire at
	 * @return Whether the shot was successful or not
	 */
	public boolean fire (Player player, char row, int column){
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			System.out.println("There is already a missle there!");
			return false;
		}

		player.getMyBoard().getSquare(row, column).placeMissle();

		//Updates the board if a ship was actually hit.
		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			if(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat() == false){
				System.out.println("A ship has been sunk!");
				player.sinkShip();
			} 
		}else{
			System.out.println("Miss! nothing was hit.");
		}
		return true; 
	}

	/**
	 * Row in which the player will place their ship
	 * 
	 * @return The row in which the player placed their ship. Between A-J.
	 */
	public char getRow(){
		String line;
		char row;
		System.out.print("Please select a row (A-J): " );
		line = scan.nextLine();
		
		row = line.toUpperCase().charAt(0);

		while(getMyBoard().charToNum(row) == -1){
			System.out.println("Sorry the row must be A-J");
			System.out.print("Please select a row (A-J): " );
			line = scan.nextLine();
			row = line.toUpperCase().charAt(0);
			
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
		System.out.print("Please select a column (1-10): ");
		stringColumn = scan.nextLine();
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
	public boolean placeShip(Player player, Ship ship, char row, int column, Boolean vert){
		char test = row;
		//Checks to see if it's out of bounds
		if(vert==true && getMyBoard().charToNum(row)+ship.getSize()>10){
			System.out.println("Sorry you cannot place this ship there");
			return false;
		}else if(vert==false && column+ship.getSize()>11){
			System.out.println("Sorry you cannot place this ship here");
			return false;
		}
		//Checks to see if there is a ship already there.
		for(int i=0;i<ship.getSize();i++){
			if(vert){

				if(player.getMyBoard().getSquare(test, column).hasShip()==true){
					System.out.println("There is already a ship there");
					return false;
				}
				test++;
			}else{
				if(player.getMyBoard().getSquare(row, column+i).hasShip()==true){
					System.out.println("There is already a ship there");
					return false;
				}
			} 
		}
		//Places the ship where the player specified.
		for(int i=0; i<ship.getSize();i++ ){
			if(vert == true){
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum(),ship);
				row++;
			}else{
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum(),ship);
				column++ ;
			}
		}
		return true; 
	}
}