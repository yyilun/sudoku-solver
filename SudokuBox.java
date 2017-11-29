import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// This is the SudokuBox object that contain the actual value and possible values for each grid box. 
//This object will be instantiated 9x9 times when the program runs.

public class SudokuBox {
	public Integer value;
	public Queue<Integer> possibleValues;

	
	public SudokuBox(Integer inputValue){
		value = inputValue;
		possibleValues = new LinkedList<Integer>();
	}
	

}
