import java.util.Random;

public class Exploration {

    public static void main(String[] args) {
        
        int numofTrials = 5;
        int timeoutThreshold = 10000000; 
        int[] initialLockedValues = {10, 20, 30, 40};

        // Experiment 1: Relationship between the number of initial values and solveability
        System.out.println("Experiment 1 - Relationship between initial values and solveability:");
        for (int num_Locked : initialLockedValues) {
            int solvedCount = 0, timeoutCount = 0;
            for (int i = 0; i < numofTrials; i++) {
                Sudoku sudoku = new Sudoku(num_Locked);
                sudoku.timeoutIterations = timeoutThreshold;
                // System.out.println(sudoku.timeoutIterations);
                if (sudoku.solve(0)) {
                    solvedCount++;
                } else {
                    timeoutCount++;
                }
            }
            System.out.println("Initial Values: " + num_Locked);
            System.out.println("Solved: " + solvedCount + " out of " + numofTrials);
            System.out.println("Timeout: " + timeoutCount + " out of " + numofTrials);
            System.out.println();
        }

        // Experiment 2: Relationship between the number of initial values and solve time
        System.out.println("Experiment 2 - Relationship between initial values and solve time:");
        for (int num_Locked : initialLockedValues) {
            int numSolutions = 0;
            int numTimeouts = 0;
            long totalTime = 0;

            for (int i = 0; i < numofTrials; i++) {
                Sudoku sudoku = new Sudoku(num_Locked);
                // System.out.println(sudoku);
                long startTime = System.currentTimeMillis();
                boolean solved = sudoku.solve(10);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                // Record the outcome
                if (solved) {
                    numSolutions++;
                } else {
                    numTimeouts++;
                }
                totalTime += elapsedTime;
            }
            // Calculate average time taken for the current case
            long averageTime = totalTime / numofTrials;

            System.out.println("Initial Values: " + num_Locked);
            System.out.println("Average Solve Time: " + averageTime + " milliseconds");
            System.out.println("Number of Timeouts "+ numTimeouts);
            // System.out.println(sudoku);
            System.out.println();
        }
    }
}

