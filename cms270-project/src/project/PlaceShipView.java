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
	private BattleshipBoardView view;
	private int currentSize;
	private boolean vert;
	private char row;
	private int column;
	private Game g;
	
	public PlaceShipView(Game game) {
		g = game;
		
		currentSize = 3;
		vert = false;
		BattleshipBoardView view = new BattleshipBoardView(g.getPlayerBoard());
		
		row = view.getCurrentRow();
		column = view.getCurrentColumn();
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
				row = view.getCurrentRow();
				column = view.getCurrentColumn();
				placeShip(g);
				view.updateBoard(g.getPlayerBoard());
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
	
	public Boolean getVert(){
		return vert;
	}
	
	public int getCurrentSize(){
		return currentSize;
	}
	
	
	public void placeShip(Game g ){
		g.placeShip(g.getHumanPlayer(),currentSize, row, column, vert);
		//view.updateBoard(g.getPlayerBoard());
		g.getPlayerBoard().displayWithShips();
		
	}
}


