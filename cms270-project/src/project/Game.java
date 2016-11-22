package project;
import java.util.*;

/**
 * @author Rene Borr, Roderick Zak, Felix Ruiz, Jerry Abril
 * @version 1.0.0
 */
public class Game {
	Player player;
	Player computer;

	/**
	 * Creates both players and loops through the game until there is a winner.
	 */
	public void playGame(){
		Boolean winner;
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");

		String name = scan.nextLine();
		player =  new Player(name,scan);
		computer = new Computer();

		
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
	 * @param scan Takes input from the individual for where the ship will go.
	 */
	public static void placeShips(Player player, Boolean errors){
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
	 * Main method that will play the game.
	 * 
	 * @param args Main method of the code.
	 */
	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
