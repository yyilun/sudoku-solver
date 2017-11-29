import java.io.File;
import java.util.Scanner;
import java.util.HashSet;

// method to test the program
// takes the input text file, invokes the solver function, and outputs the results
public class Tester {
	
	
	public static void main(String[] args) {
		String fileName = new String();
		fileName = args[0];
		String lineByLine;
		int lineCount = 0;
		
		SudokuSolver solveIt = new SudokuSolver();


		// parses the input text file
		try {
			File inFile = new File(fileName);
			Scanner input = new Scanner(inFile);
			while (input.hasNext()) {
				lineByLine = input.nextLine();
				String[] values = lineByLine.split("\\s+");// http://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java
				for (int i = 0; i < values.length; i++) {
					solveIt.addElement(lineCount, i, values[i]);
				}
				lineCount++;
			}
			input.close();
		} catch (Exception e) {
			System.out.println(e);
		}


		// invokes the solver function
		solveIt.solve();
		
		//outputs the results
		for (int j = 0; j < 9; j++) {
		for (int k = 0; k < 9; k++) {
			solveIt.printElement(j,k);
		}
		System.out.println();
	}	
	}


}
