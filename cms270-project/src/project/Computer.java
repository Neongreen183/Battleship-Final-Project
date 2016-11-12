package project;
import java.util.*;


public class Computer extends Player {

	public Computer() {
		super(); //call to superclass constructor 

	}
	
	Random rand = new Random();
	int start = 0;
	int end = 10;
	int randNum;
	char randRow;
	boolean flag;
	
	@Override
	public void getRowAndColumn(char row, int column){
		column = rand.nextInt(((end - start) +1)  + start);
		randNum = rand.nextInt(((end - start) +1)  + start);
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		randRow = options[randNum];
		row = randRow;
	}
	
	
	
	
}
