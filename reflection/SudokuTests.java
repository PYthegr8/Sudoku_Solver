/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description: The SudokuTests test the functionality of the various constructors and methods in the Sudoku class

*/

public class SudokuTests {

    public static void constructor() {
        Sudoku sudoku = new Sudoku(10);
        System.out.println("Constructor: " + (sudoku != null));
    }

    public static void findNextValue() {
        Sudoku sudoku = new Sudoku(0);
        Cell cell = new Cell(1, 1, 1); // hypothetical Cell initialization 
        int nextValue = sudoku.findNextValue(cell);
        System.out.println("FindNextValue: " + (nextValue > 1 && nextValue < 10));
    }

    public static void findNextCell() {
        Sudoku sudoku = new Sudoku(10);
        Cell nextCell = sudoku.findNextCell();
        boolean valid = (nextCell != null && nextCell.getValue() > 0);
        System.out.println("FindNextCell: " + valid);
    }

    public static void solve() {
        Sudoku sudoku = new Sudoku(10);
        boolean solutionFound = sudoku.solve(10);
        System.out.println("Solve: " + solutionFound);
    }

    public static void main(String[] args) {
        constructor();
        findNextValue();
        findNextCell();
        solve();
    }
}