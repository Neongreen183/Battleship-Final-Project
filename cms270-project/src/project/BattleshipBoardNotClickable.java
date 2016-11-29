package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BattleshipBoardNotClickable extends GridPane {
	private Button[][] squares;
	private Label currentChoice;
	private char currentRow = 'A';
	private int currentColumn = 1;
	private Board board;
	private int size;

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
	
	public Label getLabel(){
		return currentChoice;
	}
		
	
	
	public int getCurrentColumn(){
		return currentColumn;
	}
	
	public char getCurrentRow(){
		return currentRow;
	}
	public int getSize(){
		return size;
	}
	
	public void updateRowAndColumn(char j, int i){
		currentColumn = i;
		currentRow = j;
	}
	
	
}