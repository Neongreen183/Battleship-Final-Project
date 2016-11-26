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
	Board board = new Board();
	BattleshipBoardView view;
	
	
	
	boolean vert = false;
	
	public PlaceShipView(Board board) {
		
		
		BattleshipBoardView view = new BattleshipBoardView( board );
		
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
		        updateCurrentChoice(view.getCurrentRow(), view.getCurrentColumn());
		    }
		});
		
		place = new Button("Place");
		
		

		
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
	
	protected void updateCurrentChoice(char row, int column){
		currentChoice.setText("Current choice: " + view.getCurrentRow() + view.getCurrentColumn());
	}
	
}


