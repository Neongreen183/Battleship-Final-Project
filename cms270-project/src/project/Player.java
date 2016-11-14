package project;

import java.util.Scanner;

public class Player {

	private int shipsLeft;
	private Ship[] myShips;
	private String playerName;
	private Board myBoard;

	public Player() {
		playerName = "unknown";
		shipsLeft = 4;
		myBoard = new Board();
		myShips = new Ship[5];
		myShips[0]=new Ship(0,2);
		myShips[1]=new Ship(1,3);
		myShips[2]=new Ship(2,3);
		myShips[3]=new Ship(3,4);
		myShips[4]=new Ship(4,5);
	}

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
	//returns the player name
	public String getName(){
		return playerName;
	}
	//returns the number of ships that are still alive
	public int getShipsLeft(){
		return shipsLeft;
	}
	//returns player board
	public Board getMyBoard(){
		return myBoard;
	}
	//returns the players ships in a array
	public Ship[] getMyships(){
		return myShips;
	}
	//returns the ship in the array the the integer index
	public Ship getShip(int num){
		return myShips[num];
	}
	//decrements the number of ships when one is sunk
	public void sinkShip(){
		shipsLeft--;
	}
	//changes the hasMissile value in a square in the board to to true if it is false
	//and if there is a ship it augments the hits of the ship and if it sinks the ship\
	//it displays that the ship has 
	public boolean fire (Player player, char row, int column){
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			System.out.println("There is already a missle there!");
			return false;
		}
		player.getMyBoard().getSquare(row, column).placeMissle();

		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			if(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat() == false){
				System.out.println("A ship has been sunk!");
				player.sinkShip();

			} 

		}
		else{
			System.out.println("Miss! nothing was hit.");
		}

		return true; 
	}


	public char getRow(){
		char row;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select a row (A-J): " );
		row = scan.next().toUpperCase().charAt(0);
		while(charToNum(row) == -1){
			System.out.println("Sorry the row must be A-J");
			System.out.println("Please select a row (A-J): " );
			row = scan.next().toUpperCase().charAt(0);
		}
		return row;
	}
	public int getColumn(){
		Scanner scan = new Scanner(System.in);
		int column;
		System.out.println("Please select a colomn (1-10)");
		column = scan.nextInt();
		while(column>10 || column<1){ 
			System.out.println("Sorry the row must be 1-10");
			System.out.println("Please select a colomn(1-10)");
			column = scan.nextInt();
		}
		return column;

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


	public boolean placeShip(Player player, Ship ship, char row, int column, Boolean vert){
		char test = row;



		if(vert==true && charToNum(row)+ship.getSize()>10){
			System.out.println("Sorry you cannot place this ship there");
			return false;
		} 
		else if(vert==false && column+ship.getSize()>11){
			System.out.println("Sorry you cannot place this ship here");
			return false;
		}

		for(int i=0;i<ship.getSize();i++){
			if(vert){

				if(player.getMyBoard().getSquare(test, column).hasShip()==true){
					System.out.println("There is already a ship there");
					return false;
				}
				test++;
			}
			else{
				if(player.getMyBoard().getSquare(row, column+i).hasShip()==true){
					System.out.println("There is already a ship there");
					return false;
				}
			} 

		}


		for(int i=0; i<ship.getSize();i++ ){
			if(vert == true){
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
				row++;
			}
			else{
				player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
				column++ ;
			}
		}
		return true;

	}
}


