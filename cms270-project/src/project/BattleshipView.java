package project;


import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BattleshipView extends Application {
	
	Label rootLabel;
	TextField rootTF;
	
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
		} else {
			dialog.close();
			return "Player1";  //default value
			
		}
		
	}
	
		
		public boolean validateName(TextField tf) {
			String n = tf.getText();
			System.out.println("\"" + n + "\"");
			if(n == null || n.equals("")) 
				return false;
			else 
				return true;
		}
		
		public void launchErrorDialog(String error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(error);
			alert.setContentText(error);

			alert.showAndWait();
		}
		
		public String getName(Scene scene) {
			String playerName = launchTextDialog("Enter Name","Please enter your name: ");
			System.out.println("Player Name: " + playerName);
			return playerName;
			
			
		}
		
		public void placeShips(Board board, Stage stage){
			PlaceShipView view1 = new PlaceShipView(board);
			Scene scene = new Scene (view1, 500, 500);
			stage.setTitle("Place Ships");
			stage.setScene(scene);
			
		
		}


		@Override
		public void start(Stage stage) throws Exception {
			VBox root = new VBox();
			Scene scene = new Scene (root, 500, 500);
			stage.setTitle("Some simple dialogs");
			stage.setScene(scene);
			Game g = new Game();
			String name = getName(scene);
			g.makeHumanPlayer(name);
			
			PlaceShipView view1 = new PlaceShipView(g.getPlayerBoard());
			Scene scene2 = new Scene (view1, 500, 500);
			stage.setTitle("Place Ships");
			stage.setScene(scene2);
			stage.show();
		}
		



	public void launchConfirmDialog() {
		//confirmation dialog
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
	
	public String launchTextDialog() {
		//Text Input from a single field
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog Demo");
		dialog.setHeaderText(" Name");
		dialog.setContentText("Please enter Player's name:");

		//add some validation for our window if the OK button is clicked
//		final Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
//		okButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				if(!validateName( dialog.getEditor() ) ) {
//					launchErrorDialog("name");
//					System.out.println("Test1");
//					e.consume();
//				}
//			}
//		});


		Optional<String> result = dialog.showAndWait();

		//because of the button's event handler, we know a non-empty String
		//has been entered.
		if ( result.isPresent() ) {
			System.out.println(result.get());
			return result.get();
		} else {
			return "Bob";  //default value
		}
	}

		

	public static void main(String[] args) {
		launch(args);

	}
}
