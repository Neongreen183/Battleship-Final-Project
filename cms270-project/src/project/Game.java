package project;
import java.util.*;

/**
 * @author Rene Borr, Roderick Zak, Felix Ruiz, Jerry Abril
 * @version 1.0.0
 */
public class Game {
	Player player;
	Player computer = new Computer();

	/**
	 * Establishes that this will be a human player to be utilized later.
	 * 
	 * @param name The name of the user.
	 */
	public void setHumanPlayer(String name) {
		player = new Player(name);
	}

	/**
	 * Establishes that this will be the computer player to be utilized later.
	 */
	public void setComputerPlayer(){
		placeShips(computer,false);
	}

	/**
	 * Getter for the player board
	 * 
	 * @return The player board
	 */
	public Board getPlayerBoard(){
		return player.getMyBoard();
	}

	/**
	 *  Getter for the human player.
	 *  
	 * @return The human player.
	 */
	public Player getHumanPlayer(){
		return player;
	}
	
	/**
	 * Getter for the computer.
	 * 
	 * @return The computer
	 */
	public Player getComputerPlayer(){
		return computer;
	}

	/**
	 * Getter for the computer board.
	 * 
	 * @return The computer board.
	 */
	public Board getComputerBoard(){
		return computer.getMyBoard();
	}

	/**
	 * Plays the game
	 */
	public void playGame(){
		Boolean winner;

		System.out.println(player.getName() + " VS. " + computer.getName() + ". Prepare to battle!");
		System.out.println("This is what your board looks like:");
		player.getMyBoard().displayWithShips();

		System.out.println ("For this board:  S = ship");
		System.out.println ("\t\t X = hit");
		System.out.println ("\t\t O = Miss");
		System.out.println ("\t\t - = Empty");
		System.out.println("Specify the row (A-J) and column (1-10) for each of your five ships.");
		//Randomly places the ships across the board for the Computer.
		placeShips(computer, false);
		//computer.getMyBoard().displayWithShips();
		//Loops until player places all of their ships.
		placeShips(player,true);
		//Checks for a winner.
		winner = battle(player,computer);
		System.out.println("Game over!");
		if(winner == true){
			System.out.println(player.getName() + " Has won!");
		}else{
			System.out.println(computer.getName() + " Has won!");
		}		
	}

	/**
	 * Loops through until the player has placed each of their ships on the board.
	 * 
	 * @param player The individual that is placing the ships on their board.
	 * @param error Whether there have been any errors with the code so far.
	 */
	public void placeShips(Player player, Boolean errors){
		boolean success;
		if(errors){
			System.out.println("Placing ship 1");
		}
		success = player.placeShip(2);
		while(success == false){
			if(errors){
				System.out.println("You cannot place a ship there");
			}
			success = player.placeShip(2);
		}

		if(errors){
			System.out.println("Placing ship 1");
		}
		success = player.placeShip(3);
		while(success == false){
			if(errors){
				System.out.println("You cannot place a ship there");
			}
			success = player.placeShip(3);
		}

		if(errors){
			System.out.println("Placing ship 1");
		}
		success = player.placeShip(3);
		while(success == false){
			if(errors){
				System.out.println("You cannot place a ship there");
			}
			success = player.placeShip(3);
		}

		if(errors){
			System.out.println("Placing ship 1");
		}
		success = player.placeShip(4);
		while(success == false){
			if(errors){
				System.out.println("You cannot place a ship there");
			}
			success = player.placeShip(4);
		}

		if(errors){
			System.out.println("Placing ship 1");
		}
		success = player.placeShip(5);
		while(success == false){
			if(errors){
				System.out.println("You cannot place a ship there");
			}
			success = player.placeShip(5);
		}
	}

	/**
	 * Calls the placeShip method from each player and checks to see if it was successful
	 * 
	 * @param player Which player's ships are being called
	 * @param size How big the ship is
	 * @param row Which row the ship goes on
	 * @param column Which column the ship goes on
	 * @param vert Whether it's vertical or not
	 * @return Whether it was succesful or not.
	 */
	public boolean placeShip(Player player, int size, char row, int column, boolean vert){
		return player.placeShip(size, row, column, vert);
	}

	/**
	 * Plays the game by letting each player fire against each other.
	 * 
	 * @param player1 The first player of the game.
	 * @param player2 The second player of the game.
	 * @return Value that will determine who the winner is.
	 */
	public static boolean battle(Player player1, Player player2){
		Random rand = new Random();
		int turnCounter = rand.nextInt(1);
		char row = 'A';
		int column = 1;
		boolean success;

		/*Loops through the entire game letting each player fire against each other
		 * until there is a winner.
		 */
		while(player1.getShipsLeft() != 0 && player2.getShipsLeft() != 0){
			if(turnCounter == 0){
				do{
					System.out.println("Its " + player1.getName() + "'s turn!");
					row = player1.getRow();
					column = player1.getColumn();
					success = player2.fire(row, column);
					turnCounter = 1;
					player2.getMyBoard().displayWithoutShips();
					if(success == false){
						System.out.println("You cant place a missle there");
					}

				}while(success == false);
			}else{
				System.out.println("Its " + player2.getName() + "'s turn!");
				do{
					row = player2.getRow();
					column = player2.getColumn();
					success = player1.fire(row, column);
					turnCounter = 0;
				}while(success == false);

				player1.getMyBoard().displayWithShips();
			} 
		}
		if(player1.getShipsLeft()==0){
			return false;
		}
		return true;
	}

	/**
	 * Creates a human player 
	 * 
	 * @param name The name of the player
	 */
	public void makeHumanPlayer(String name){
		Scanner scan = new Scanner(System.in);
		player = new Player(name,scan);
	}

}
