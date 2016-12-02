package project;


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
/**
 * 
 * @author Rene Borr, Felix Ruiz, Roderick Zak, Jerry Abril
 * @version 1.0.0
 */
public class PlaceShipView extends VBox {

	private Label prompt;
	private Label currentChoice;
	private Label currentShip;
	private Label prompt2;
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

	/**
	 * Opens up board for player to place ships
	 * 
	 * @param s The board that will be opened
	 * @param game The game that will be played
	 */
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

		//Adds the letters to the side of the board
		VBox letters = new VBox();
		Button label = new Button();
		label.setText(" ");
		for (int i=0;i<10;i++){
			label = new Button();
			String alphabet = "ABCDEFGHIJ";
			label.setText("" + alphabet.charAt(i));
			label.setMaxWidth(view.getSize());
			label.setMaxHeight(view.getSize());
			label.setMinWidth(view.getSize());
			label.setMinHeight(view.getSize());
			letters.getChildren().addAll(label);
		}

		//Adds the numbers to the top of the board
		HBox numbers = new HBox();
		label = new Button();
		label.setText(" ");
		label.setMaxWidth(view.getSize());
		label.setMaxHeight(view.getSize());
		label.setMinWidth(view.getSize());
		label.setMinHeight(view.getSize());
		numbers.getChildren().addAll(label);
		for (int i = 1;i<11;i++){
			label = new Button();
			label.setText("" + i);
			label.setMaxWidth(view.getSize());
			label.setMaxHeight(view.getSize());
			label.setMinWidth(view.getSize());
			label.setMinHeight(view.getSize());
			numbers.getChildren().addAll(label);
		}
		HBox board = new HBox();
		board.getChildren().addAll(letters,view);
		getChildren().addAll(prompt,currentChoice,currentShip, numbers, board, prompt2, vertical, place);
	}

	/**
	 * Changes the text in the box depending on the action chose by player.
	 * 
	 * @param event Whether the player clicks on the button.
	 */
	protected void setVertical(ActionEvent event) {
		if (vert == true){
			vertical.setText("Vertical");
		}else{
			vertical.setText("Horizonal");
		}
	}

	/**
	 * Getter for whether ship is vertical or not.
	 * 
	 * @return Whether the ship is vertical or not.
	 */
	public Boolean getVert(){
		return vert;
	}

	/**
	 * Getter for the ship's size.
	 * 
	 * @return What the size of the ship is.
	 */
	public int getCurrentSize(){
		return currentSize;
	}

	/**
	 * Updates the display to show which ship is currently being placed.
	 * 
	 * @param num The number of the ship
	 * @param size How big the ship is
	 */
	private void updateCurrentShip(int num, int size){
		currentShip.setText("Placing ship " + num + " (size " + size + ")");
	}

	/**
	 * Launches an error box when one occurs
	 * 
	 * @param error Error display after one occurs.
	 */
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);
		alert.showAndWait();
	}

	/**
	 * Calls the player class to place the ship in the location they desire.
	 * 
	 * @param g The game being played
	 */
	public void placeShip(Game g ){
		int [] shipSize = {2,3,3,4,5};
		boolean success;
		//Whether the placing of the ship in the location desired was valid. 
		success = g.placeShip(g.getHumanPlayer(),shipSize[turn], row, column, vert);
		if(success == true){
			turn++;
		}else{
			launchErrorDialog("You Cannot place a ship there");
		}
		if(turn != 5){
			updateCurrentShip(turn+1,shipSize[turn]);
		}else{
			stage.close();
		}
		

	} 
	
	/**
	 * Returns the current turn
	 * 
	 * @return Who's turn it is.
	 */
	public int getTurn(){
		return turn;

	}

	/**
	 * Returns the appropriate board view for the placing ships
	 * 
	 * @return The board that the player will place ships on.
	 */
	public BattleshipBoardView getView() {
		return view;
	}

	/**
	 * Sets the view to the appropriate size of the board.
	 * 
	 * @param view How big the view is for the board to fit.
	 */
	public void setView(BattleshipBoardView view) {
		this.view = view;
	}
}