/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description:  The Sudoku class actually solves a puzzle. It contains a constructor
                * that can create a board with some initial values, a solve method, and a main method. 

*/
import java.util.Scanner;
public class Sudokudiff_sizes {

    // field for the Board object.
    private Board_diffsizes sudoku_Board;
   

    // Constructor with number of locked cells parameter
    public Sudokudiff_sizes(int numLockedCells) {
        this.sudoku_Board = new Board_diffsizes(numLockedCells);
        // ld = new LandscapeDisplay(sudoku_Board);
    }

    // Constructor with both number of locked cells and size parameters
    public Sudokudiff_sizes(int numLockedCells, int size) {
        this.sudoku_Board = new Board_diffsizes(numLockedCells, size);
        // ld = new LandscapeDisplay(sudoku_Board);
    }

    // Method to check if a number is a perfect square
    private static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    // Method to find the next empty cell and set a valid value for it
    public Cell findNextCell() {
        for (int row = 0; row < sudoku_Board.getRows(); row++) {
            for (int col = 0; col < sudoku_Board.getCols(); col++) {
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

    // Method to check if there is a valid value for this cell that we haven't tried
    public int findNextValue(Cell cell) {
        int currentValue = cell.getValue();
        for (int value = currentValue + 1; value <= sudoku_Board.size; value++) {
            if (sudoku_Board.validValue(cell.getRow(), cell.getCol(), value)) {
                return value;
            }
        }
        return 0;
    }

    // Method to solve the Sudoku puzzle
    public boolean solve(int delay) {
        Stack<Cell> sudoStack = new LinkedList<>();
        int numUnspecifiedCells = sudoku_Board.getRows() * sudoku_Board.getCols() - sudoku_Board.numLocked();
        while (sudoStack.size() < numUnspecifiedCells) {
            Cell next = findNextCell();

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            while (next == null && sudoStack.size() > 0) {
                Cell cur_Cell = sudoStack.pop();
                int nextValue = findNextValue(cur_Cell);
                cur_Cell.setValue(nextValue);

                if (nextValue != 0) {
                    next = cur_Cell;
                }
            }
            if (next == null) {
                return false;
            } else {
                sudoStack.push(next);
            }
        }
        sudoku_Board.finished = true;
        return sudoku_Board.finished;
    }

    // Method to check if the solution is valid
    public boolean validSolution() {
        return sudoku_Board.validSolution();
    }

    // Method to convert Sudoku puzzle to string
    public String toString() {
        return sudoku_Board.toString();
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the gridsize for the Sudoku:");
        int size = scanner.nextInt();

        if(!isPerfectSquare(size)){
            System.out.println("Number must be perfect square");
        }

        System.out.println("Enter the number of lockedcells for the Sudoku:");
        int numLockedCells = scanner.nextInt();
        if(!isPerfectSquare(size)){
            System.out.println("Number must be perfect square");
        }

        Sudokudiff_sizes sudoku = new Sudokudiff_sizes(numLockedCells, size);

        System.out.println("Initial Board:");
        System.out.println(sudoku.toString());

        if (sudoku.solve(10)) {
            System.out.println("Solution found:");
            System.out.println(sudoku.toString());
        } else {
            System.out.println("No solution found!");
        }
        
        scanner.close(); // Remember to close the scanner to prevent resource leak
    }
}
