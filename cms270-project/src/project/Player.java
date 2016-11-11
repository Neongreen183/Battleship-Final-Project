package project;

public class Player {
	
	private int shipsLeft;
	private Ship[] myShips;
	private String playerName;
	private Board myBoard;

	public Player(String name) {
		this.playerName=name;
		this.shipsLeft=4;
		myBoard = new Board();
		myShips = new Ship[5];
		myShips[0]=new Ship(0,2);
		myShips[1]=new Ship(1,3);
		myShips[2]=new Ship(2,3);
		myShips[4]=new Ship(3,4);
		myShips[5]=new Ship(4,5);
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
	
	public void placeShips(Player player, Ship ship, char row, int column, Boolean vert){
		if(player.getMyBoard().getSquare(row, column).hasShip()==true){
			System.out.println("There is already a ship there");
		}
		else{
			for(int i=0; i<ship.getSize();i++ ){
				if(vert == true){
					player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
					column++;
				}
				else{
					player.getMyBoard().getSquare(row, column).placeShip(ship.getShipNum());
					row++;
				}
			}
		}
	}
}


