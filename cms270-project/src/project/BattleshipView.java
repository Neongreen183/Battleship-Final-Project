package project;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 * @author Felix Ruiz, Roderick Zak, Rene Borr, Jerry Abril
 * @version 1.0.0
 *
 */
public class BattleshipView extends Application {

	Label rootLabel;
	TextField rootTF;
	/**
	 * Opens up the beginning of the game
	 * 
	 * @param title Title that will be displayed
	 * @param message Message that will be displayed
	 * @return Text of the game
	 */
	public String launchTextDialog(String title, String message) {
		//Text Input from a single field
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("battleship");
		dialog.setHeaderText(title);
		dialog.setContentText(message);

		//add some validation for our window if the OK button is clicked
		final Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
		okButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(!validateName( dialog.getEditor() ) ) {
					launchErrorDialog("please enter a Valid Name");
					e.consume();
				}
			}
		});

		Optional<String> result = dialog.showAndWait();

		//because of the button's event handler, we know a non-empty String
		//has been entered.
		if ( result.isPresent() ) {
			dialog.close();
			return result.get();
		}else {
			dialog.close();
			return "Player1";  //default value
		}
	}

	/**
	 * Make sure that the player enters their name correctly
	 * 
	 * @param tf The box that will accept the player's entry
	 * @return The player's name if successfully placed
	 */
	public boolean validateName(TextField tf) {
		String n = tf.getText();
		System.out.println("\"" + n + "\"");
		if(n == null || n.equals("")){
			return false;
		}else 
			return true;
	}

	/**
	 * Launches error box if something goes wrong
	 * 
	 * @param error The error that was made
	 */
	public void launchErrorDialog(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(error);
		alert.setContentText(error);
		alert.showAndWait();
	}

	/**
	 * Launches box to have players enter their name
	 * 
	 * @param scene The opener of the game
	 * @return The player's input name
	 */
	public String getName(Scene scene) {
		String playerName = launchTextDialog("Enter Name","Please enter your name: ");
		System.out.println("Player Name: " + playerName);
		return playerName;


	}

	/**
	 * Launches mode for the player to place their ships
	 * 
	 * @param board The board in which the ships will be placed
	 * @param g The game in which the ships will be registered.
	 */
	public void placeShips(Board board, Game g){
		Stage stage1 = new Stage();
		PlaceShipView view1 = new PlaceShipView(stage1,g);

		Scene scene = new Scene (view1, 500, 500);
		stage1.setTitle("Place Ships");
		stage1.setScene(scene);
		stage1.showAndWait();
	}

	/**
	 * Launches mode for the player to battle against computer
	 * @param stage The stage of the game they are in
	 * @param g The game that is being played
	 */
	public void battle(Stage stage, Game g){
		BattleView view2 = new BattleView(stage, g);
		Scene scene = new Scene(view2, 925, 500);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Plays the game
	 */
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();

		Scene scene = new Scene (root, 500, 500);
		Stage stage1 = new Stage();
		stage1.setTitle("Some simple dialogs");
		stage1.setScene(scene);
		Game g = new Game();
		String name = getName(scene);
		g.setHumanPlayer(name);
		g.setComputerPlayer();
		placeShips(g.getPlayerBoard(), g);
		battle(stage1,g);
	}

	/**
	 * Launches a dialogue that prompts the player to play the game
	 */
	public void launchConfirmDialog() {
		//Confirmation dialog
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Play!");
		alert.setContentText("Are you ready to start the game?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			rootLabel.setText("User is ready to play!");
		} else {
			rootLabel.setText("User DOESN'T want to start the game.");
		}
	}

	/**
	 * Launches dialogue to have player input their name
	 * 
	 * @return Name of the player
	 */
	public String launchTextDialog() {
		//Text Input from a single field
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog Demo");
		dialog.setHeaderText(" Name");
		dialog.setContentText("Please enter Player's name:");

		Optional<String> result = dialog.showAndWait();

		//Because of the button's event handler, we know a non-empty String
		//has been entered.
		if ( result.isPresent() ) {
			System.out.println(result.get());
			return result.get();
		} else {
			return "Player";  //default value
		}
	}

	/**
	 * Initiates the game
	 * 
	 * @param args Argument to start game.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
