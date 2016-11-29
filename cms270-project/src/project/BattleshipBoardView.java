package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BattleshipBoardView extends GridPane {
	//private BoardGame game;
	private Button[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;
	int size;

	public BattleshipBoardView(Board b) {
		super();
		board = b;
		//game = bg;
		currentRow = 'A';
		currentColumn = 1;
		size = 30;
		
		
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
		squares = new Button[rows][cols];
		ButtonHandler bh = new ButtonHandler();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				squares[i][j] = new Button();
				//squares[i][j].setStyle("-fx-background-color:" + game.getSquareColor(i,j));
				squares[i][j].setText(" " + board.getSquare(i, j).getChar());
				squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
				add(squares[i][j], j, i);

				squares[i][j].setMaxWidth(size);
				squares[i][j].setMaxHeight(size);
				squares[i][j].setMinHeight(size);
				squares[i][j].setMinWidth(size);

				squares[i][j].setOnAction(bh);
				
			}
		}


	}
	
	
	public void updateBoard(Board b){
		board = b;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				squares[i][j].setText(" " + board.getSquare(i, j).getChar());
				squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
			}
		}
	}
	
	public Label getLabel(){
		return currentChoice;
	}
	
	public int getSize(){
		return size;
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
	
	
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		public void handle (ActionEvent event) {
			for(int i = 0; i < squares.length; i++) {
				for(int j = 0; j < squares[i].length; j++) {
					if (event.getSource() == squares[i][j]) {
						//squares[i][j].setText(game.getSquareText(i,j));
						updateRowAndColumn(numToChar(i),j+1);
						currentChoice.setText("Current Choice: " + currentRow + currentColumn);
						return;
					}
				}
			}
		}
	}
	
	public static char numToChar(int i){
		String alphabet = "ABCDEFGHIJ";
		return alphabet.charAt(i);
	}
	
	
}