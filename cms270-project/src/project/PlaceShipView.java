package project;

//import guis.SimpleMath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlaceShipView extends VBox {
	
	private Label prompt;
	private Label currentChoice;
	//private TextField input;
	private Button vertical;
	private Button place;
	BattleshipBoardView view;
	private int currentSize;
	private boolean vert;
	
	public PlaceShipView(Game g) {
		
		currentSize = 3;
		vert = true;
		BattleshipBoardView view = new BattleshipBoardView(g.getPlayerBoard());
		
		prompt = new Label("Select a square.");
		
		currentChoice = view.getLabel();
		///input = new TextField();
		vertical = new Button("Horizonal");
		vertical.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				if(vert == false){
					vert = true;
				}
				else
				{
					vert = false;
				}
		        setVertical(e);
		    }
		});
		
		place = new Button("Place");
		place.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				placeShip(g);
		    }
		});
		
		

		
		getChildren().addAll(prompt,currentChoice, view, vertical, place);
	}
	
	protected void setVertical(ActionEvent event) {
		if (vert == true){
			vertical.setText("Vertical");
		}
		else{
			vertical.setText("Horizonal");
		}
	}
	
	protected void updateBoard(Board board){
		view.updateBoard(board);
	}
	
	public char getCurrentRow(){
		return view.getCurrentRow();
	}
	
	public int getCurrentColumn(){
		return view.getCurrentColumn();
	}
	
	public Boolean getVert(){
		return vert;
	}
	
	public int getCurrentSize(){
		return currentSize;
	}
	
	
	protected void placeShip(Game g ){
		g.placeShip(g.player, getCurrentSize(), getCurrentRow(), getCurrentColumn(), getVert());
		updateBoard(g.getPlayerBoard());
		
	}
}


