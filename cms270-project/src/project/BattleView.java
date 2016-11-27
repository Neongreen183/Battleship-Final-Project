package project;

//import guis.SimpleMath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
	private Button fire;
	private char row;
	private int column;
	private Game g;
	private int turn = 0;
	private Stage stage;
	private BattleshipBoardClickable oppBoard;
	private BattleshipBoardNotClickable myBoard;
	private Label humanLabel;
	private Label computerLabel;
	
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
		humanLabel = new Label(" ");
		computerLabel = new Label(" ");
		
		currentChoice = oppBoard.getLabel();
		currentChoice.setAlignment(Pos.BASELINE_RIGHT);
		
		
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
		fireBox.setAlignment(Pos.BASELINE_CENTER);
		
		
	
		
		

		
		getChildren().addAll(prompt,humanLabel, oppBoard,fireBox,computerLabel, myBoard);
	}

	
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);

		alert.showAndWait();
	}
	public void fire(){
		char cRow = g.getComputerPlayer().getRow();
		int ccolumn = g.getComputerPlayer().getColumn();
		g.getComputerPlayer().fire(row, column);
		updateHumanLabel();
		g.getHumanPlayer().fire(cRow, ccolumn);
		updateComputerLabel(cRow,ccolumn);
		
	}
	
	public void updateHumanLabel(){
		if(g.getComputerBoard().getSquare(row, column).getChar() == 'O'){
			humanLabel.setText("Miss! Nothing was Hit.");
		}
		else if(g.getComputerBoard().getSquare(row, column).getChar() == 'X'){
			humanLabel.setText("Hit! A ship has been struck.");
		}
		else if(g.getComputerBoard().getSquare(row, column).getChar() == '!'){
			humanLabel.setText("Hit! A ship has been sunk.");
		}
	}
	
	public void updateComputerLabel(char row, int column){
		if(g.getPlayerBoard().getSquare(row, column).getChar() == 'O'){
			computerLabel.setText("The computer Fired at " + row + column + "	Miss! Nothing was Hit.");
		}
		else if(g.getPlayerBoard().getSquare(row, column).getChar() == 'X'){
			computerLabel.setText("The computer Fired at " + row + column + "	Hit! A ship has been struck.");
		}
		else if(g.getPlayerBoard().getSquare(row, column).getChar() == '!'){
			computerLabel.setText("The computer Fired at " + row + column + "	Hit! A ship has been sunk.");
		}
		
	}

}