package project;
import java.util.*;
public class Game {
	
	public static void playGame(){
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		Boolean winner;
		Player player = new Player(name);
		Player computer = new Player("computer");
		
		placeShips(player);
		placeShips(computer);
		winner = battle(player,computer);
		System.out.println("Game over!");
		if(winner == true){
			System.out.println(player.getName() + " Has won!");
		}
		else{
			System.out.println(computer.getName() + " Has won!");
			
		}
		
		
	}
	
	public static void placeShips(Player player){
		Scanner scan = new Scanner(System.in);
		char row;
		int  column;
		char playerAns;
		boolean vert;
		boolean success;
		for(int i=0; i<5; i++){
			do{
				System.out.println("Placeing ship number " + i);
				System.out.println("Please select a row (A-J): " );
				row = scan.next().toUpperCase().charAt(0);
				while(charToNum(row) == -1){
					System.out.println("Sorry the row must be A-J");
					System.out.println("Please select a row (A-J): " );
					row = scan.next().toUpperCase().charAt(0);
				}
				System.out.println("Please select a colomn (1-10)");
				column = scan.nextInt();
				while(column>10 || column<1){
					System.out.println("Sorry the row must be 1-10");
					System.out.println("Please select a colomn(1-10)");
					column = scan.nextInt();
				}
				System.out.println("Should the ship be vertical?(y/n); ");
				playerAns = scan.next().toLowerCase().charAt(0);
				while(playerAns != 'y' && playerAns != 'n'){
					System.out.println("Please enter the letter 'y' or 'n': ");
					playerAns = scan.next().toLowerCase().charAt(0);
				}
				if(playerAns == 'y'){
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
	
	public static boolean battle(Player player1, Player player2){
		int turnCounter = 0;
		Random rand = new Random();
		char row = 'A';
		int column = 0;
		while(player1.getShipsLeft() != 0 && player2.getShipsLeft() != 0){
			if(turnCounter == 0){
				System.out.println("Its " + player1.getName() + "'s turn!");
				row = player1.getRow();
				column = player1.getColumn();
				player1.fire(player2, row, column);
				turnCounter = 1;
				player2.getMyBoard().displayWithoutShips();
				//player1.getMyBoard().displayWithShips();
				
			}
			else{
				System.out.println("Its " + player2.getName() + "'s turn!");
				row = numToChar(rand.nextInt(9));
				column = rand.nextInt(9)+1;
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
		 
		return alphabet.charAt(i-1);
	}
	
	
	
	

	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
