/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description:  The Sudoku class actually solves a puzzle. It contains a constructor
                * that can create a board with some initial values, a solve method, and a main method. 

*/
import java.util.Random;

public class Sudoku {

    // field for the Board object. 
    private Board sudoku_Board;
    LandscapeDisplay ld;



    // Make a constructor that creates a new Board with some number of pre-determined randomly placed values.
    public Sudoku (int numLockedCells){
        this.sudoku_Board = new Board(numLockedCells);  
        ld = new LandscapeDisplay(sudoku_Board);  

    }

    /**
    * Randomly permutes the symbols used in the solution.
     * @return 
    */
    @SuppressWarnings("unused") void randomPermute(){
        int[] permutation = new int[sudoku_Board.getRows()];
        Random rand = new Random();
        for(int i = 0; i < sudoku_Board.getRows(); i++){
            int swapIndex = rand.nextInt(i + 1);
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = i;
        }

        for(int r = 0; r < sudoku_Board.getRows(); r++){
            for(int c = 0; c < sudoku_Board.getCols(); c++){
                sudoku_Board.set(r, c, permutation[sudoku_Board.value(r, c) - 1] + 1);
            }
        }
    }

    public int numSolutions(){
        Board copyBoard = new Board();
        for (int row = 0; row < Board.size; row++) {
            for (int col = 0; col < Board.size; col++) {
                copyBoard.set(row, col, sudoku_Board.value(row, col));;
            }
    
        for (int row_ = 0; row_ < Board.size; row_++) {
            for (int col = 0; col < Board.size; col++) {
                if (copyBoard.get(row_,col).getValue() != 0) {
                    copyBoard.get(row_,col).setLocked(true);
                }
            }
        }

        // Create a stack to store cells that need to be explored
    Stack<Cell> stack = new LinkedList<>();
    stack.push(copyBoard.get(0, 0)); // Start with the first cell

    int solutions = 0;

    while (stack.size()!=0) {
        Cell currentCell = stack.pop();

        if (currentCell.getValue() == 0) {
            for (int value = 1; value <= Board.size; value++) {
                if (copyBoard.validValue(currentCell.getRow(), currentCell.getCol(), value)) {
                    currentCell.setValue(value);

                    if (copyBoard.findNextCell() == null) {
                        solutions++; // Found a solution
                    } else {
                        stack.push(copyBoard.findNextCell(currentCell));
                    }

                    currentCell.setValue(0); // Reset the cell's value for backtracking
                }
            }
            return solutions; // Return the number of solutions found so far
        }
    }

    return solutions;
}
}


    // Method to remove values from the board one by one, ensuring the number of solutions doesn't increase
public void removeValues() {
    Random random = new Random();
    int numLockedCells = sudoku_Board.numLocked();

    while (sudoku_Board.numLocked() > numLockedCells / 2) {
        int row = random.nextInt(Board.size);
        int col = random.nextInt(Board.size);
        int value = sudoku_Board.get(row, col).getValue();

        if (value != 0) {
            // Temporarily remove the value
            sudoku_Board.set(row, col, 0);

            // Check if the number of solutions remains the same after removal
            if (numSolutions() != 1) {
                // If the number of solutions increases, restore the value
                sudoku_Board.set(row, col, value);
            }
        }
    }
}




    // Method determine if there is a valid value for this cell that we haven't tried
    public int  findNextValue(Cell cell){

        int currentValue = cell.getValue();
        for (int value = currentValue + 1; value < Board.size +1; value++) {
            if (sudoku_Board.validValue(cell.getRow(), cell.getCol(), value)) {
                return value;
            }
        }

        return 0;
    }   

    // Method to find the next empty cell and set a valid value for it
    public Cell findNextCell() {
        for (int row =0; row < Board.size ;row++){
            for(int col = 0; col < Board.size ;col++){
                Cell currentCell = sudoku_Board.get(row, col);
                if (currentCell.getValue() == 0) {
                    int nextValue = findNextValue(currentCell);
                    if (nextValue != 0) {
                        currentCell.setValue(nextValue);
                        return currentCell;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public boolean solve(int delay){
        Stack<Cell> sudoStack = new LinkedList<>();
        int numUnspecifiedCells = Board.size * Board.size - sudoku_Board.numLocked();
        while (sudoStack.size() < numUnspecifiedCells ){
            Cell next = findNextCell();

            if (delay > 0)
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            if (ld != null)
                ld.repaint();
            
            while(next==null && sudoStack.size() > 0){
                Cell cur_Cell = sudoStack.pop();
                int nextValue = findNextValue(cur_Cell);
                cur_Cell.setValue(nextValue);
            
                if(nextValue!= 0){
                    next = cur_Cell;
                }

            }
            if (next == null){
                return false;
            }
            else {
                sudoStack.push(next);
            }

        }
        sudoku_Board.finished = true;
        return sudoku_Board.finished;
    }

    
    public boolean validSolution() {
        return sudoku_Board.validSolution();
    }

    public String toString() {
        return sudoku_Board.toString();
    }

   
}
    
