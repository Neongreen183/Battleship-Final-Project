package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
//import project.BattleshipBoardView.ButtonHandler;

public class BattleshipBoardNotClickable extends GridPane {
	//private BoardGame game;
	private Label[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;

	public BattleshipBoardNotClickable(Board b) {
		super();
		board = b;
		//game = bg;
		currentRow = 'A';
		currentColumn = 1;
		
		currentChoice = new Label();
		currentChoice.setText("Current Choice: A1");

		int rows = 10;
		int cols = 10;

		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100.0 / rows);

		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(100.0/cols);

		for(int i = 0; i < rows; i++) {
			getRowConstraints().add(row);
		}
		for(int i = 0; i < cols; i++) {
			getColumnConstraints().add(col);
		}
		squares = new Label[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				squares[i][j] = new Label();
				//squares[i][j].setStyle("-fx-background-color:" + game.getSquareColor(i,j));
				squares[i][j].setText(" " + board.getSquare(i, j).getChar());
				add(squares[i][j], j, i);

				squares[i][j].setMaxWidth(Double.MAX_VALUE);
				squares[i][j].setMaxHeight(Double.MAX_VALUE);

			}
		}
	}
	
	
	public void updateBoard(Board b){
		board = b;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				
				squares[i][j].setText(" " + board.getSquare(i, j).getChar());
				
			}
		}
	}
	
	public Label getLabel(){
		return currentChoice;
	}
		
	
	
	public int getCurrentColumn(){
		return currentColumn;
	}
	
	public char getCurrentRow(){
		return currentRow;
	}
	
	public void updateRowAndColumn(char j, int i){
		currentColumn = i;
		currentRow = j;
	}
	
	
}