package project;
import java.util.*;
public class Game {
	
	public static void playGame(){
		System.out.println("Please Enter your Name: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		Player player = new Player(name);
		Player computer = new Player("Computer");
		
		
		
	}

	public static void main(String[] args) {
		Board boar = new Board ();
		boar.displayWithoutShips();
	}
}
