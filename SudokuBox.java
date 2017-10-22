import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class SudokuBox {
	public Integer value;
	public Queue<Integer> possibleValues;

	
	public SudokuBox(Integer inputValue){
		value = inputValue;
		possibleValues = new LinkedList<Integer>();
	}
	

}
