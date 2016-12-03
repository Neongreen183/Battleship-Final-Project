package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Jerry Abril, Rene Borr, Roderick Zak, Felix Ruiz
 *@version 1.0.0
 */
public class BattleView extends VBox {

	private Label prompt;
	private Label currentChoice;
	private Button fire;
	private char row;
	private int column;
	private Game g;
	private Stage stage;
	private BattleshipBoardClickable oppBoard;
	private BattleshipBoardNotClickable myBoard;
	private Label humanLabel;
	private Label computerLabel;

	/**
	 * The board that will be displayed when two players are battling
	 * 
	 * @param s The boards being utilized
	 * @param game The game being played
	 */
	public BattleView(Stage s, Game game) {
		g = game;
		stage = s;
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
			}
		});

		HBox fireBox = new HBox();
		fireBox.getChildren().addAll(fire,currentChoice);

		VBox letters = new VBox();
		Button label = new Button();
		label.setText(" ");
		//Creates the letters at the side of the bard
		for (int i =0; i<10; i++){
			label = new Button();
			String alphabet = "ABCDEFGHIJ";
			label.setText("" + alphabet.charAt(i));
			label.setMaxWidth(oppBoard.getSize());
			label.setMaxHeight(oppBoard.getSize());
			label.setMinWidth(oppBoard.getSize());
			label.setMinHeight(oppBoard.getSize());
			letters.getChildren().addAll(label);
		}
		//Creates the numbers at the top of the board.
		HBox numbers = new HBox();
		label = new Button();
		label.setText(" ");
		label.setMaxWidth(oppBoard.getSize());
		label.setMaxHeight(oppBoard.getSize());
		label.setMinWidth(oppBoard.getSize());
		label.setMinHeight(oppBoard.getSize());
		numbers.getChildren().addAll(label);
		for (int i=1 ;i<11; i++){
			label = new Button();
			label.setText("" + i);
			label.setMaxWidth(oppBoard.getSize());
			label.setMaxHeight(oppBoard.getSize());
			label.setMinWidth(oppBoard.getSize());
			label.setMinHeight(oppBoard.getSize());
			numbers.getChildren().addAll(label);
		}

		HBox oppBoardView = new HBox();
		oppBoardView.getChildren().addAll(letters,oppBoard);

		VBox letters2 = new VBox();
		label = new Button();
		label.setText(" ");
		//Generates the letters at the side of the board.
		for (int i =0; i<10; i++){
			label = new Button();
			String alphabet = "ABCDEFGHIJ";
			label.setText("" + alphabet.charAt(i));
			label.setMaxWidth(myBoard.getSize());
			label.setMaxHeight(myBoard.getSize());
			label.setMinWidth(myBoard.getSize());
			label.setMinHeight(myBoard.getSize());
			letters2.getChildren().addAll(label);
		}
		//Generates the numbers at the top of the  board.
		HBox numbers2 = new HBox();
		label = new Button();
		label.setText(" ");
		label.setMaxWidth(myBoard.getSize());
		label.setMaxHeight(myBoard.getSize());
		label.setMinWidth(myBoard.getSize());
		label.setMinHeight(myBoard.getSize());
		numbers2.getChildren().addAll(label);
		for (int i=1 ;i<11; i++){
			label = new Button();
			label.setText("" + i);
			label.setMaxWidth(myBoard.getSize());
			label.setMaxHeight(myBoard.getSize());
			label.setMinWidth(myBoard.getSize());
			label.setMinHeight(myBoard.getSize());
			numbers2.getChildren().addAll(label);
		}

		HBox myBoardView = new HBox();
		myBoardView.getChildren().addAll(letters2,myBoard);

		Label yourBoardLabel = new Label();
		yourBoardLabel.setText("This is your board.");

		Label oppBoardLabel = new Label();
		oppBoardLabel.setText("This is the computers Board, click here.");

		VBox board1 = new VBox();
		VBox board2 = new VBox();

		board1.getChildren().addAll(oppBoardLabel,humanLabel, numbers, oppBoardView);
		board2.getChildren().addAll(yourBoardLabel,computerLabel,numbers2,myBoardView);

		Label space = new Label();
		space.setText("		");
		label.setMinWidth(myBoard.getSize());

		HBox bothBoards = new HBox();
		bothBoards.getChildren().addAll(board1,space, board2);

		getChildren().addAll(prompt,bothBoards,fireBox);
	}

	/**
	 * Displays an error box when something goes wrong.
	 * 
	 * @param error What error occurred.
	 */
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);

		alert.showAndWait();
	}

	/**
	 * Displays a winner box when there is a winner.
	 * 
	 * @param winner The player that won the game.
	 */
	public void launchConfirmDialog(String winner) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("GameOver");
		alert.setHeaderText("Play!");
		alert.setContentText(winner + " has won the game.");
		alert.show();
	}

	/**
	 * Creates the fire button and makes it usable in the battle view.
	 */
	public void fire(){
		boolean success;
		char cRow = g.getComputerPlayer().getRow();
		int ccolumn = g.getComputerPlayer().getColumn();
		success = g.getComputerPlayer().fire(row, column);
		updateHumanLabel(row, column);

		if(success == true){
			updateHumanLabel(row, column);
			g.getHumanPlayer().fire(cRow, ccolumn);
			updateComputerLabel(cRow,ccolumn);
		}else{
			launchErrorDialog("You cannot fire there!");
		}
		if(g.getComputerPlayer().getShipsLeft() == 0){
			endGame(g.getHumanPlayer().getName());
		}else if(g.getHumanPlayer().getShipsLeft() == 0){
			endGame("Computer");
		}
	}

	/**
	 * Updates the user label to let them know whether they hit, missed, or sunk a ship.
	 * 
	 * @param row The row of the last square fired at
	 * @param column The column of the last square fired at.
	 */
	public void updateHumanLabel(char row, int column){
		if(g.getComputerBoard().getSquare(row, column).getChar().equalsIgnoreCase("O")){
			humanLabel.setText("You fired at " + row + column + ". Miss! Nothing was Hit.");
		}else if(g.getComputerBoard().getSquare(row, column).getChar().equalsIgnoreCase("X")){
			humanLabel.setText("You fired at " + row + column + ". Hit! A ship has been struck.");
		}else if(g.getComputerBoard().getSquare(row, column).getChar().equalsIgnoreCase("!")){
			humanLabel.setText("You fired at " + row + column + ". Hit! A ship has been sunk.");
		}
	}

	/**
	 * Updates the computer label to let them know whether they hit, missed, or sunk a ship.
	 * 
	 * @param row The row of the last square fired at
	 * @param column The column of the last square fired at.
	 */
	public void updateComputerLabel(char row, int column){
		if(g.getPlayerBoard().getSquare(row, column).getChar().equalsIgnoreCase("O")){
			computerLabel.setText("The computer Fired at " + row + column + "	Miss! Nothing was Hit.");
		}else if(g.getPlayerBoard().getSquare(row, column).getChar().equalsIgnoreCase("X")){
			computerLabel.setText("The computer Fired at " + row + column + "	Hit! A ship has been struck.");
		}else if(g.getPlayerBoard().getSquare(row, column).getChar().equalsIgnoreCase("!")){
			computerLabel.setText("The computer Fired at " + row + column + "	Hit! A ship has been sunk.");
		}
	}

	/**
	 * Launches the final box when the game is over.
	 * 
	 * @param winner The winner of the game.
	 */
	public void endGame(String winner){
		launchConfirmDialog(winner);

	}
}