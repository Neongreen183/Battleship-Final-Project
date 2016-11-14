package project;
import java.util.*;


public class Computer extends Player { 
	Random rand = new Random();
	int start = 0;
	int end = 10;
	int randNum;
	char randRow;
	boolean flag;
	

	public Computer() {
		super();//call to superclass constructor 

	}
	
	
	
	@Override
	public char getRow(){
		char row;
		randNum = rand.nextInt(((end - start) +1)  + start);
		char[] options = {'A','B','C','D','E','F','G','H','I','J'};
		row = options[randNum];
		return row;
	}
	
	@Override
	public int getColumn(){
		int column;
		column = rand.nextInt(((end - start) +1)  + start);
		return column;
		
	}
	
	
	
	
	
}
