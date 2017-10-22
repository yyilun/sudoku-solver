# sudoku-solver

Yilun Ying, 2016

Written in Java

Inputs:

Sudoku puzzle is inputed as a .txt file named "theGrid.txt". The file contains the Sudoku puzzle to be solved, with each grid space delineated by a " ", and each blank denoted by "x". An example of the format is as follows:

8 x x x x x x x x

x x 3 6 x x x x x

x 7 x x 9 x 2 x x

x 5 x x x 7 x x x

x x x x 4 5 7 x x

x x x 1 x x x 3 x

x x 1 x x x x 6 8

x x 8 5 x x x 1 x

x 9 x x x x 4 x x


Running the program:
To run the program, input the following command into terminal

java Tester theGrid.txt

Output:

The program will print the solved Sudoku puzzle directly into terminal.

Program files:
Tester - the test driver, reads the input file, parses the input, and outputs the solved solution
SudokuSolver - contains the methods for solving the Sudoku puzzle
SudokuBox - the object that contains current values and possible values for each grid box

Program description:
The Sudoku solver will initialize a 9x9 array of SudokuBox objects when the program begins. Each SudokuBox object contains the current value (0-9, or x), and a queue of possible values if it is currently undefined.

The program will then analyze each grid, and remove possible values of each undefined grid based on the restrictions imposed by the other grids. For example, if a row already contains 1,2,3,4,6,7,8,9, then it will remove these values from the remaining undefined grid. It also keeps track of the grid with the least number of possible values.

If there are grids with only one remaining possible value, that value will be locked into the puzzle. Then the update process gets repeated again.

If there are no grids with only one remaining possible value, the program will then search for the grid with the least number of remaining value, and pick the first value off the queue of possible values. It will then temporarily lock that value into the grid and update the rest of the grids, repeating the same heuristics. 

If it encounters a situation where there are undefined grids with no possible values, then the program will backtrack and undo the last guess. It will then pick the next possible value from the queue for that gridbox and continue with the heuristics, and backtrack again if needed.

The program will continue until the entire puzzle is solved.

Exceptions:
If the puzzle given is wrong i.e. impossible puzzle, the program will terminate and throw an exception. If there are multiple solutions to the puzzle, the program will output one possible solution to the puzzle.

