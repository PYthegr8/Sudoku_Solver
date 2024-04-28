/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description: The Board Tests test the functionality of the various constructors and methods in the Board class

*/
public class BoardTests {

    public static void main(String[] args) {
        constructor();
        constructorWithFilled();
        getRowsCols();
        numLocked();
        value();
        validValue();
        validSolution();
    }

    public static void constructor() {
        Board board = new Board();
        System.out.println("Constructor: " + (board.getRows() == 9 && board.getCols() == 9));
    }

    public static void constructorWithFilled() {
        Board board = new Board(10);
        int lockedCount = 0;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if (board.isLocked(i, j))
                    lockedCount++;
            }
        }
        System.out.println("ConstructorWithFilled: " + (lockedCount == 10));
    }

    public static void getRowsCols() {
        Board board = new Board();
        System.out.println("GetRowsCols: " + (board.getRows() == 9 && board.getCols() == 9));
    }

    public static void numLocked() {
        Board board = new Board(15);
        System.out.println("NumLocked: " + (board.numLocked() == 15));
    }

    public static void value() {
        Board board = new Board();
        board.set(1, 1, 5);
        System.out.println("Value: " + (board.value(1, 1) == 5));
    }

    public static void validValue() {
        Board board = new Board();
        board.set(1, 1, 5);
        boolean result = !board.validValue(2, 1, 5) && board.validValue(2, 1, 6);
        System.out.println("ValidValue: " + result);
    }

    public static void validSolution() {
        Board board = new Board();
        System.out.println("ValidSolution: " + (!board.validSolution()));
    }

}