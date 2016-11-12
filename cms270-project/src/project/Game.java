package project;
import java.util.*;
public class Game {
	
	public static void playGame(){
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		Player player = new Player(name);
		Player computer = new Player();
		
		placeShips(player);
		
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
	
	public static int charToNum(char c){
		String alphabet = "ABCDEFGHIJ";
		for(int i=0;i<alphabet.length();i++){
			if (alphabet.charAt(i) == c){
				return i;
			}
		}
		return -1;
	} 
	
	

	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
