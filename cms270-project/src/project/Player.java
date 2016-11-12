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
	
	public String getName(){
		return playerName;
	}
	
	public int getShipsLeft(){
		return shipsLeft;
	}
	
	public Board getMyBoard(){
		return myBoard;
	}
	
	public Ship[] getMyships(){
		return myShips;
	}
	
	public Ship getShip(int num){
		return myShips[num];
	}
	
	public void sinkShip(){
		shipsLeft--;
	}
	
	public boolean fire (Player player, char row, int column){
		if(player.getMyBoard().getSquare(row, column).hasMissle()){
			System.out.println("There is already a missle there!");
			return false;
		}
		player.getMyBoard().getSquare(row, column).placeMissle();
		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
			
		}
		if(!(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat())){
			System.out.println("A ship has been sunk!");
			player.sinkShip();
			
		} 
		return true; 
	}
	
	public void getRowAndColomn(char row, int column){
		Scanner scan = new Scanner(System.in);
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
		
		
		if(vert==true && charToNum(row)+ship.getSize()>10){
			System.out.println("Sorry you cannot place this ship there");
			return false;
		}
		else if(vert==false && column+ship.getSize()>10){
			System.out.println("Sorry you cannot place this ship here");
			return false;
		}
		else{
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
}


