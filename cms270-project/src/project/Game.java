package project;
import java.util.*;

/**
 * @author Rene Borr, Roderick Zak, Felix Ruiz, Jerry Abril
 * @version 1.0.0
 */
public class Game {

	/**
	 * Creates both players and loops through the game until there is a winner.
	 */
	public void playGame(){
		Boolean winner;
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");

		String name = scan.nextLine();

		Player player = new Player(name,scan);
		Computer computer = new Computer();
		computer.getMyBoard().displayWithShips();

		System.out.println(player.getName() + " VS. " + computer.getName());

		//Randomly places the ships across the board for the Computer.
		placeShips(computer);
		//Loops until player places all of their ships.
		placeShips(player, scan);
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
	 * @param scan Takes input from the individual for where the ship will go.
	 */
	public static void placeShips(Player player, Scanner scan){
		char row;
		int  column;
		char playerAns;
		boolean vert;
		boolean success;

		//Loops through five times for each ship so that the player can place them where they wish
		for(int i=0; i<5; i++){
			do{
				System.out.println("Placing ship number " + (i+1) 
						+ " (length " + player.getShip(i).getSize() + ")");
				row = player.getRow();
				column = player.getColumn();
				System.out.print("Vertical or Horizontal? Please enter V or H: ");
				playerAns = scan.nextLine().toLowerCase().charAt(0);
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
				success = player.placeShip(player, player.getShip(i), row, column, vert);
			}while(success == false);
			player.getMyBoard().displayWithShips();
		}
	}

	/**
	 * Places the ships randomly for the computer.
	 * 
	 * @param computer The opponent that will get their ships on their board randomly placed.
	 */
	public static void placeShips(Computer computer){
		Random rand = new Random();
		char row;
		int  column;
		int intVert;
		boolean vert = false;
		boolean success;
		//Loops through five times to place all the ships for the computer randomly
		for(int i=0; i<5; i++){
			do{
				row = computer.getRow();
				column = computer.getColumn();
				intVert = rand.nextInt(2);
				if(intVert == 0){
					vert = true;
				}else{
					vert = false;
				}
				success = computer.placeShip(computer, computer.getShip(i), row, column, vert);
			}while(success == false);
		}
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
					success = player1.fire(player2, row, column);
					turnCounter = 1;
					player2.getMyBoard().displayWithoutShips();

				}while(success == false);
			}else{
				do{
					System.out.println("Its " + player2.getName() + "'s turn!");
					row = player2.getRow();
					column = player2.getColumn();
					System.out.println("The computer fired at " + row + column);
					success = player2.fire(player1, row, column);
					turnCounter = 0;
					player1.getMyBoard().displayWithShips();
				}while(success == false);
			} 
		}
		if(player1.getShipsLeft()==0){
			return false;
		}
		return true;
	}

	/**
	 * Takes a char and returns an int that is correspondent to the letter of the row.
	 * 
	 * @param c The letter of the row.
	 * @return The int value of the row.
	 */


	/**
	 * Takes an int and returns a char in the row that it corresponds.
	 * 
	 * @param i The number that corresponds to a letter.
	 * @return The char based on the given int.
	 */
	

	/**
	 * Main method that will play the game.
	 * 
	 * @param args Main method of the code.
	 */
	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
