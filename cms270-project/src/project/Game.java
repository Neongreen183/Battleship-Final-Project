package project;
import java.util.*;
public class Game {
	
	
	//Play game creates both players, and 
	public static void playGame(){
		Boolean winner;
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");

		String name = scan.nextLine();
		
		Player player = new Player(name,scan);
		Computer computer = new Computer();
		
		System.out.println(player.getName() + " VS. " + computer.getName());
		
		placeShips(computer);
		placeShips(player, scan);
		winner = battle(player,computer);
		System.out.println("Game over!");
		if(winner == true){
			System.out.println(player.getName() + " Has won!");
		}
		else{
			System.out.println(computer.getName() + " Has won!");
		}
				
	}

	public static void placeShips(Player player, Scanner scan){
		char row;
		int  column;
		char playerAns;
		boolean vert;
		boolean success;

		for(int i=0; i<5; i++){
			do{
				System.out.println("Placing ship number " + (i+1) 
						+ " (length " + player.getShip(i).getSize() + ")");
				row = player.getRow();
				column = player.getColumn();
				System.out.print("Should the ship be vertical?(V/H); ");
				playerAns = scan.nextLine().toLowerCase().charAt(0);
				
				while(playerAns != 'h' && playerAns != 'v'){
					System.out.print("Please enter the letter 'V' or 'H': ");
					playerAns = scan.nextLine().toLowerCase().charAt(0);
				}
				if(playerAns == 'v'){
					vert = true;
				}
				else{
					vert = false;
				}
				success = player.placeShip(player, player.getShip(i), row, column, vert);
			}while(success == false);
			player.getMyBoard().displayWithShips();
		}
	}
	
	public static void placeShips(Computer computer){

		Random rand = new Random();
		char row;
		int  column;
		int intVert;
		boolean vert = false;
		boolean success;
		for(int i=0; i<5; i++){
			do{
				
				row = computer.getRow();
				column = computer.getColumn();
				intVert = rand.nextInt(2);
				if(intVert == 0){
					vert = true;
				}
				else{
					vert = false;
				}
				success = computer.placeShip(computer, computer.getShip(i), row, column, vert);
			}while(success == false);
		}
	}

	public static boolean battle(Player player1, Player player2){
		int turnCounter = 0;
		Random rand = new Random();
		char row = 'A';
		int column = rand.nextInt(1);
		boolean success;
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
			} 
			else{
				System.out.println("Its " + player2.getName() + "'s turn!");
				row = numToChar(rand.nextInt(9));
				column = rand.nextInt(9)+1;
				System.out.println("The computer fired at " + row + column);
				player2.fire(player1, row, column);
				turnCounter = 0;
				player1.getMyBoard().displayWithShips();
			} 
		}
		if(player1.getShipsLeft()==0){
			return false;
		}
		return true;
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

	public static char numToChar(int i){
		String alphabet = "ABCDEFGHIJ";

		return alphabet.charAt(i);
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
