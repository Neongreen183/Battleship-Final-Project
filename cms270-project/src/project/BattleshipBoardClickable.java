package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Rene Borr, Felix Ruiz, Roderick Zak, Jerry Abril
 * @version 1.0.0
 */
public class BattleshipBoardClickable extends GridPane {
	private Button[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;
	private int size;

	/**
	 * Generates the board that the user will click to fire at ships.
	 * 
	 * @param b The board being utilized
	 */
	public BattleshipBoardClickable(Board b) {
		super();
		board = b;
		currentRow = 'A';
		currentColumn = 1;
		size = 35;

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
		//Nested for loops that generate the board up of clickable squares.
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				squares[i][j] = new Button();
				if(board.getSquare(i, j).getChar().equalsIgnoreCase("S")){
					squares[i][j].setText("-");
					squares[i][j].setStyle("-fx-background-color:" + "#2B65EC");
				}
				else{
					squares[i][j].setText(" " + board.getSquare(i, j).getChar());
					squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
				}
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
	 * Updates the board after shots have been fired from it.
	 * 
	 * @param b The board that is being updated.
	 */
	public void updateBoard(Board b){
		board = b;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(board.getSquare(i, j).getChar().equalsIgnoreCase("S")){
					squares[i][j].setText("-");
				}else{
					squares[i][j].setText(" " + board.getSquare(i, j).getChar());
					squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
					squares[i][j].setMaxWidth(size);
					squares[i][j].setMaxHeight(size);
					squares[i][j].setMinHeight(size);
					squares[i][j].setMinWidth(size);
				}
			}
		}
	}

	/**
	 * Getter method for labels
	 * 
	 * @return The label of the latest choice.
	 */
	public Label getLabel(){
		return currentChoice;
	}

	/**
	 * Getter method for the current column
	 * 
	 * @return The last column selected by the user.
	 */
	public int getCurrentColumn(){
		return currentColumn;
	}

	/**
	 * Getter method for the current row
	 * 
	 * @return The last row selected by the user.
	 */
	public char getCurrentRow(){
		return currentRow;
	}

	/**
	 * Getter method for the size of the ship.
	 * 
	 * @return The size of the ship.
	 */
	public int getSize(){
		return size;
	}

	/**
	 * Updates the row and column after last selection
	 * 
	 * @param j The last row selected
	 * @param i The last column selected
	 */
	public void updateRowAndColumn(char j, int i){
		currentColumn = i;
		currentRow = j;
	}

	/**
	 * 
	 * @author Rene Borr
	 * Handles the event of telling players what their current choice is.
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
	 * Changes a number into a char
	 * 
	 * @param i Number between 1-10.
	 * @return The created char
	 */
	public static char numToChar(int i){
		String alphabet = "ABCDEFGHIJ";
		return alphabet.charAt(i);
	}
}