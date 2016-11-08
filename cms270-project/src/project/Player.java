package project;

public class Player {
	
	private int shipsLeft;
	private Ship[] myShips;
	private String playerName;
	private Board myBoard;

	public Player(String name) {
		this.playerName=name;
		this.shipsLeft=4;
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
	
	public void fire (Player player, char row, int column){
		player.getMyBoard().getSquare(row, column).placeMissle();
	}
}


