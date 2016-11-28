package project;

//import guis.SimpleMath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlaceShipView extends VBox {
	
	private Label prompt;
	private Label currentChoice;
	private Label currentShip;
	private Label prompt2;
	//private TextField input;
	private Button vertical;
	private Button place;
	private Button playGame;
	private BattleshipBoardView view;
	private int currentSize;
	private boolean vert;
	private char row;
	private int column;
	private Game g;
	private int turn = 0;
	private Button continueOn;
	Stage stage;
	
	public PlaceShipView(Stage s, Game game) {
		g = game;
		stage = s;
		
		currentSize = 3;
		vert = false;
		BattleshipBoardView view = new BattleshipBoardView(g.getPlayerBoard());
		
		row = view.getCurrentRow();
		column = view.getCurrentColumn();
		prompt = new Label("Select a square.");
		currentShip = new Label("Placing ship 1 (size 2)");
		

		currentChoice = view.getLabel();
		///input = new TextField();
		
		prompt2 = new Label ("Your ship will be placed the way the Button says. If you wish to change it\n"
				+ "click on the button. ");
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
		
		
		continueOn = new Button("Play game");
		continueOn.setOnAction(new EventHandler<ActionEvent> () {
		@Override public void handle(ActionEvent e) {
			if(turn != 5){
				launchErrorDialog("You must place all your ships before continueing");
			}
			else{
				return;
			}
	    }
	});
		
		

		
		getChildren().addAll(prompt,currentChoice,currentShip, view, prompt2, vertical, place);
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
	
	private void updateCurrentShip(int num, int size){
		currentShip.setText("Placing ship " + num + " (size " + size + ")");
	}
	
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);

		alert.showAndWait();
	}
	
	
	public void placeShip(Game g ){
		int [] shipSize = {2,3,3,4,5};
		boolean success;
		success = g.placeShip(g.getHumanPlayer(),shipSize[turn], row, column, vert);
		if(success == true){
			turn++;
			//updateCurrentShip(turn+1,shipSize[turn]);
		}
		else{
			launchErrorDialog("You Cannot place a ship there");
		}
		if(turn != 5){
			updateCurrentShip(turn+1,shipSize[turn]);
		}
		else{
			
			stage.close();
		}
		
		
	} 
	public int getTurn(){
		return turn;
		
	}
}


