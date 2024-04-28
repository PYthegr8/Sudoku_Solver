/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description: This extension uses command line arguments 0- number of fillerd cells 1- delay(10 RECOMMENDED). 
 * It then shows the time taken to solve the board in the terminal after running

*/
public class Extension3 {
        public static void main(String[] args) {
            if (args.length < 1) {
                System.out.println("Usage: Include integer value for the number of originally filled cells");
            } else {
                Sudoku gameSudoku = new Sudoku(Integer.parseInt(args[0]));
                System.out.println(gameSudoku);
    
                long timeBefore = System.currentTimeMillis();
                gameSudoku.solve(Integer.parseInt(args[1]));
                Long timeAfter = System.currentTimeMillis();
                long timeUsed = timeAfter - timeBefore;
                System.out.println(gameSudoku);
                System.out.println(timeUsed);
            }
        }
}

