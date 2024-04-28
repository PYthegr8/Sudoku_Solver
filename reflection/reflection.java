public class reflection {
    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku(10);
    
        System.out.println("Initial Board:");
        System.out.println(sudoku);
     

        if (sudoku.solve(10)) {
            System.out.println("Solution found:");
            System.out.println(sudoku);
            System.out.println("Random Permuation board found:");
            sudoku.randomPermute();
            System.out.println(sudoku);
            
        }
}
}
