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
			return result.get();
		} else {
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


		@Override
		public void start(Stage stage) throws Exception {
			VBox root = new VBox();
			Scene scene = new Scene (root, 500, 500);
			stage.setTitle("Some simple dialogs");
			stage.setScene(scene);
			Game g = new Game();
			String name = getName(scene);
			g.makeHumanPlayer(name);
			g.playGame();
		}
		
		public static void main(String[] args) {
			launch(args);
		}

}
