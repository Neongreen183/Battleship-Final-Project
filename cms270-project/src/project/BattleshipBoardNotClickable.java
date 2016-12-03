package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
/**
 * 
 * @author Jerry Abril, Roderick Zak, Felix Ruiz, Rene Borr
 * @version 1.0.0
 */
public class BattleshipBoardNotClickable extends GridPane {
	private Button[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;
	private int size;

	/**
	 * Generates the board that will not be clickable
	 * 
	 * @param b Which board will not be clickable
	 */
	public BattleshipBoardNotClickable(Board b) {
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

			}
		}
	}

	/**
	 * Updates the board after a change has been made to it.
	 * 
	 * @param b Which board has to be changed.
	 */
	public void updateBoard(Board b){
		board = b;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				squares[i][j].setText(board.getSquare(i, j).getChar());
				squares[i][j].setStyle("-fx-background-color:" + board.getSquare(i, j).getSquareColor());
				squares[i][j].setMaxWidth(size);
				squares[i][j].setMaxHeight(size);
				squares[i][j].setMinHeight(size);
				squares[i][j].setMinWidth(size);
			}
		}
	}

	/**
	 * Getter method for last label with choice made
	 * 
	 * @return The last change made
	 */
	public Label getLabel(){
		return currentChoice;
	}

	/**
	 * Getter for the current column selected
	 * 
	 * @return The last column selected
	 */
	public int getCurrentColumn(){
		return currentColumn;
	}

	/**
	 * Getter for the current row selected
	 * 
	 * @return The last row selected.
	 */
	public char getCurrentRow(){
		return currentRow;
	}

	/**
	 * Getter method for ship size
	 * 
	 * @return Size of the ship
	 */
	public int getSize(){
		return size;
	}

	/**
	 * Updates the row and the columb based on last input
	 * 
	 * @param j The new row.
	 * @param i The new column.
	 */
	public void updateRowAndColumn(char j, int i){
		currentColumn = i;
		currentRow = j;
	}
}