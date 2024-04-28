/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description:  The Sudoku class` actually solves a puzzle. It contains a constructor
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

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku(10);
    
        System.out.println("Initial Board:");
        System.out.println(sudoku);

        if (sudoku.solve(10)) {
            System.out.println("Solution found:");
            System.out.println(sudoku);
    }
}
}
    
