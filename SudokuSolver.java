import java.util.Stack;

public class SudokuSolver {
	
	SudokuBox[][] sudokuGrid; 
	int lowestCount;
	Stack<SudokuBox> sequence;
	SudokuBox currentLowest;
	int remainingGrids;
	int runCount;
	
	//instantiates a grid of 9x9 SudokuBox objects
	public SudokuSolver(){
		sudokuGrid = new SudokuBox[9][9];
		lowestCount = 10;
		sequence = new Stack<SudokuBox>();
		currentLowest = null;
		remainingGrids = 0;
		runCount = 1;

	}
	
	//fills each grid with a number
	public void addElement(int x, int y, String value){
		Integer currentValue;
		currentValue = checkValue(value);
		sudokuGrid[x][y] = new SudokuBox(currentValue);
	}
	
	//check if there is a value inputed by the user for a grid, return null if the grid is empty
	private static Integer checkValue(String n) {
		if (n.equals("x")) {
			return null;
		} else
			return Integer.parseInt(n);
	}
	
	//prints the value of each grid box
	public void printElement(int x, int y){
		System.out.print(sudokuGrid[x][y].value+" ");
	}
	
	//function to invoke to solve the puzzle
	public void solve(){
		
		// loop until all the grids are solved
		do{
			lowestCount = 10;
			// check if there is a need to backtrack
			boolean noUndo = updateBoard();
			
			//if there is a need to backtrack, then undo the last heuristic guess
			if(!noUndo){
				undoLast();
				
			//if there is no need to backtrack, continue with the heuristic
			} else {
				heuristics();
			}
		} while(remainingGrids>1);
		} 

	// set the grid with the lowest possible numbers of numbers to be filled in
	private void heuristics(){
		setElementOnce(currentLowest);
	}
	
	// method to backtrack the previous heuristic
	private void undoLast(){
		boolean onOff = true;
		while(onOff){
			SudokuBox currentElement = sequence.pop();
			if(currentElement.possibleValues.isEmpty()){
				currentElement.value=null;
			} else {
				setElementOnce(currentElement);
				onOff = false;
			}
		}
	}
	
	// check if there are any grids that does not have any possible values
	// if there are boxes with no possible values, then the program has to backtrack
	private boolean updateBoard(){
		remainingGrids = 0;

		boolean trueFalse=true;
		for(int x = 0; x<9; x++){
			for(int y = 0; y < 9; y++){
				if(!this.evaluatePossible(x, y)){
					trueFalse = false;
				}
			}
		}
		return trueFalse;
	}
	
	// set a possible value from the list of possible values of a grid
	private void setElementOnce(SudokuBox prevElement){
		prevElement.value = prevElement.possibleValues.poll();
		sequence.push(prevElement);
	}

	// evaluate the possible values for each unfilled grid given the restrictions imposed by other neighboring grids
	private boolean evaluatePossible(int i, int j) {
		if (sudokuGrid[i][j].value == null) {
			remainingGrids++;
			sudokuGrid[i][j].possibleValues.clear();
			for (int k = 1; k < 10; k++) {
				sudokuGrid[i][j].possibleValues.add(k);
			}
			for (int l = 0; l < 9; l++) {
				if (sudokuGrid[i][j].possibleValues.contains(sudokuGrid[l][j].value)) {
					sudokuGrid[i][j].possibleValues.remove(sudokuGrid[l][j].value);
				}
				if (sudokuGrid[i][j].possibleValues.contains(sudokuGrid[i][l].value)) {
					sudokuGrid[i][j].possibleValues.remove(sudokuGrid[i][l].value);
				}
			}
			int hori = i/3;
			int verti = j/3;
			for(int m = hori*3; m<hori*3+3; m++){
				for(int n = verti*3; n<verti*3+3; n++){
					if (sudokuGrid[i][j].possibleValues.contains(sudokuGrid[m][n].value)) {
						sudokuGrid[i][j].possibleValues.remove(sudokuGrid[m][n].value);
					}
				}
				
			}
			if(sudokuGrid[i][j].possibleValues.isEmpty()){
				lowestCount = 10;
				return false;
			}
			if(sudokuGrid[i][j].possibleValues.size()==lowestCount){
				currentLowest = sudokuGrid[i][j];
			} else if(sudokuGrid[i][j].possibleValues.size()<lowestCount){
				lowestCount = sudokuGrid[i][j].possibleValues.size();
				currentLowest = sudokuGrid[i][j];
			}
		}
		return true;
	}

}
