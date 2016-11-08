package project;

public class Board {

	private Square[][] board;

	public Board() {
		board = new Square[10][10];
	}


	// for this board: 
	// S = ship
	// X = hit
	// O = miss
	// - = empty piece


	// returns square associated with that location in board
	public Square getSquare(char row, int column){
		return board[charToNum(row)][column-1];
	}
	
	// returns an array of squares to the specified parameters
	public Square[] getSquares(char row, int column, Boolean vert, int length){
		Square[] array = new Square[length];
		if(vert){
			for(int i = 0; i<length; i++){
				array[i] = board[charToNum(row)+i][column-1];
			}
		}
		
		else{
			for(int i = 0; i<length; i++){
				array[i] = board[charToNum(row)][column+i-1];
			}
		} 
		return array;
	}

	// displayes the board for the owner
	public void displayWithShips(){
		for(int i = 0;i<10;i++){
			for(int j = 0;j<10;j++){
				if(board[i][j].hasMissle() && board[i][j].hasShip()){
					System.out.print("X");
				}

				else if(board[i][j].hasMissle()){
					System.out.print("O");
				}

				else if(board[i][j].hasShip()){
					System.out.print("S");
				}

				else{
					System.out.print("-");
				}
			}
			System.out.println("");

		}
	}

	
	// displayes board for opponent view
	public void displayWithoutShips(){
		for(int i = 0;i<10;i++){
			for(int j = 0;j<10;j++){
				if(board[i][j].hasMissle() && board[i][j].hasShip()){
					System.out.print("X");
				}

				else if(board[i][j].hasMissle()){
					System.out.print("O");
				}

				else{
					System.out.print("-");
				}
			}
			System.out.println("");

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

}
