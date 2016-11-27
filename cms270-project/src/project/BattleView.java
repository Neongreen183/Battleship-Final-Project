package project;

//import guis.SimpleMath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BattleView extends VBox {
	
	private Label prompt;
	private Label currentChoice;
	//private Label currentShip;
	//private TextField input;
	//private Button vertical;
	private Button fire;
	//private Button playGame;
	//private BattleshipBoardView view;
	//private int currentSize;
	//private boolean vert;
	private char row;
	private int column;
	private Game g;
	private int turn = 0;
	private Stage stage;
	BattleshipBoardClickable oppBoard;
	BattleshipBoardNotClickable myBoard;
	
	public BattleView(Stage s, Game game) {
		g = game;
		stage = s;
		g.placeShips(g.getComputerPlayer(), false);
		g.getComputerBoard().displayWithShips();
		

		myBoard = new BattleshipBoardNotClickable(g.getPlayerBoard());
		oppBoard = new BattleshipBoardClickable(g.getComputerBoard());
		
		row = oppBoard.getCurrentRow();
		column = oppBoard.getCurrentColumn();
		prompt = new Label("Select a square you want to fire uppon.");
		
		currentChoice = oppBoard.getLabel();
		///input = new TextField();
		
		fire = new Button("Fire");
		fire.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				row = oppBoard.getCurrentRow();
				column = oppBoard.getCurrentColumn();
				fire();
				oppBoard.updateBoard(g.getComputerBoard());
				myBoard.updateBoard(g.getPlayerBoard());
				//myBoard.updateBoard(g.getPlayerBoard());
		    }
		});
		
		HBox fireBox = new HBox();
		fireBox.getChildren().addAll(fire,currentChoice);
		
	
		
		

		
		getChildren().addAll(prompt, oppBoard,fireBox, myBoard);
	}

	
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);

		alert.showAndWait();
	}
	public void fire(){
		g.getComputerPlayer().fire(row, column);
		g.getHumanPlayer().fire(g.getComputerPlayer().getRow(), g.getComputerPlayer().getColumn());
		
	}
	public int getTurn(){
		return turn;
		
	}
}