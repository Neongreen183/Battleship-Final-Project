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
	int randNum2;
	char randColumn;
	
	public int returnRandomRow() { 
		randNum = rand.nextInt(((end - start) +1)  + start);
		return randNum;
	}
	
	public char returnRandColumn() {
		randNum2 = rand.nextInt(((end - start) +1)  + start);
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		randColumn = options[randNum2];
		return randColumn;
	}
	
	
	
}
