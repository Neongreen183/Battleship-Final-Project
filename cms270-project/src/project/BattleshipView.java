package project;
import project.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BattleshipView extends Application {
	//instance variables
	Label rootLabel;
	TextField rootTF;

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
		alert.setContentText("Please enter a valid value for " + error);

		alert.showAndWait();
	}

	public void start(Stage stage) {
		VBox root = new VBox();
		rootLabel = new Label("Results of our dialog will go here");
		rootTF = new TextField();
		root.getChildren().addAll(rootLabel, rootTF);
		Scene scene = new Scene (root, 200, 200);
		stage.setTitle("Some simple dialogs");
		stage.setScene(scene);
		stage.show();
		launchConfirmDialog();
		String name = launchTextDialog();
		rootLabel.setText(rootLabel.getText() + name + "\n");
		
		Game g = new Game();
		g.setHumanPlayer(name);
		g.playGame();
		
	}
		

	public static void main(String[] args) {
		launch(args);
			
		
		
	}
}