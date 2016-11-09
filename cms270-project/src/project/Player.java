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
	
	public Ship[] getMyships(){
		return myShips;
	}
	
	public Ship getShip(int num){
		return myShips[num];
	}
	
	public void sinkShip(){
		shipsLeft--;
	}
	
	public void fire (Player player, char row, int column){
		player.getMyBoard().getSquare(row, column).placeMissle();
		if(player.getMyBoard().getSquare(row, column).hasShip()){
			System.out.println("A ship has been struck!");
			player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).hit();
		}
		if(!(player.getShip(player.getMyBoard().getSquare(row, column).getShipNum()).isAfloat())){
			System.out.println("A ship has been sunk!");
			player.sinkShip();
		} 
	} 
}


