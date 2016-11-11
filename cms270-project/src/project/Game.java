package project;
import java.util.*;
public class Game {
	
	public static void playGame(){
		System.out.print("Welcome to Battleship! "
				+ "Please enter Player 1's name: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		Player player = new Player(name);
		Player computer = new Player("Computer");
		
		Board board = new Board ();
		System.out.println("This is your board. Choose where you want to place ships");
		board.displayWithoutShips();
		
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}
}
