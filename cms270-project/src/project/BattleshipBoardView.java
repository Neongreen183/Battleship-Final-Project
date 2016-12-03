package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author Jerry Abril, Rene Borr, Roderick Zak, Felix Ruiz
 * @version 1.0.0
 *
 */
public class BattleshipBoardView extends GridPane {
	private Button[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;
	int size;

	/**
	 * Displays the board 
	 * 
	 * @param b Which board will be displayed
	 */
	public BattleshipBoardView(Board b) {
		super();
		board = b;
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
				squares[i][j].setText(board.getSquare(i, j).getChar());
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

	/**
	 * Updates the board after a change has been made
	 * 
	 * @param b Which board will be updated
	 */
	public void updateBoard(Board b){
		board = b;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				squares[i][j].setText(board.getSquare(i, j).getChar());
				squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
			}
		}
	}

	/**
	 * Getter method for the current label
	 * 
	 * @return The latest label change
	 */
	public Label getLabel(){
		return currentChoice;
	}

	/**
	 * Getter method for the ship size
	 * 
	 * @return The ship's size
	 */
	public int getSize(){
		return size;
	}

	/**
	 * Getter method for the current column
	 * 
	 * @return The latest column selected
	 */
	public int getCurrentColumn(){
		return currentColumn;
	}

	/**
	 * Getter method for the current row
	 * 
	 * @return The latest row selected
	 */
	public char getCurrentRow(){
		return currentRow;
	}

	/**
	 * Updates the label showing the row and column
	 * @param j The latest row selected
	 * @param i The latest column selected
	 */
	public void updateRowAndColumn(char j, int i){
		currentColumn = i;
		currentRow = j;
	}

	/**
	 * 
	 * @author Felix Ruiz
	 * @version 1.0.0
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {
		public void handle (ActionEvent event) {
			for(int i = 0; i < squares.length; i++) {
				for(int j = 0; j < squares[i].length; j++) {
					if (event.getSource() == squares[i][j]) {
						updateRowAndColumn(numToChar(i),j+1);
						currentChoice.setText("Current Choice: " + currentRow + currentColumn);
						return;
					}
				}
			}
		}
	}

	/**
	 *  Converts a number to a char
	 *  
	 * @param i Number between 1-10 to switch
	 * @return Char value between A-J
	 */
	public static char numToChar(int i){
		String alphabet = "ABCDEFGHIJ";
		return alphabet.charAt(i);
	}
}