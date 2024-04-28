import java.io.* ;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Board {
   
    private Cell[][] board;
    public static final int size = 9;
    boolean finished;
    

    // constructor that creates a 9x9 2D array of Cells, all of which are initialized to have value 0.
    public Board(){
        finished = false;
        board = new Cell[size][size];
        for (int i=0; i < size; i++){
            for(int j=0; j < size ; j++){
                board[i][j] = new Cell(i,j,0);
            }
        }
    }

    // An auxiliary constructor should take in a String filename and call the read method.
    public Board(String filename ){
        board = new Cell[size][size];
        for (int i=0; i < size; i++){
            for(int j=0; j < size ; j++){
                board[i][j] = new Cell(i,j,0);
            }
        }
        read(filename);
    }

// This constructor randomly generates a board with randomly choose initial cells to be locked
    public Board(int initial_numLocked ){
        this();
        Random rand = new Random();

        // / Lock cells randomly until the desired number of cells are locked
        for (int lockedCount = 0; lockedCount < initial_numLocked; ) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);

            if (!board[row][col].isLocked()) {
                int value = rand.nextInt(size) + 1; // Generate a rand value in the range [1, 9]
                if (validValue(row, col, value)) {
                    board[row][col].setValue(value);
                    board[row][col].setLocked(true);
                    lockedCount++;
                }
            }
        }
    }







    // returns the number of columns
    public int getCols() {
        return size;
    }
    // returns the number of rows
    public int getRows() {
        return size;
    }

    // returns whether the Cell at r, c, is locked.
    public boolean isLocked(int r, int c) {
        return board[r][c].isLocked();
    }

    // returns the number of locked Cells on the board.
    public int numLocked(){
        int count = 0;
        for (int i=0; i < size; i++){
            for(int j=0; j < size ; j++){
                if (board[i][j].isLocked()){
                    count ++;
                }
            }
        }
        return count;
    }

    // returns the value at Cell r, c.
    public int value(int r, int c) {
        return board[r][c].getValue();
    }


    // this returns the Cell at the given row and col
    public Cell get(int row, int col){
        return board[row][col];
    }


    // this sets the Cell at the given row and col to the given value
    public void set(int row, int col, int value){
        board[row][col] = new Cell(row,col,value); 
    }


    // this sets whether the Cell at the given row and col is locked
    public void set(int row, int col,int value, boolean locked){
        board[row][col] = new Cell(row, col, value, locked); 
    }


    // tests if the given value is a valid value at the given row and column of the board
    public boolean validValue(int row, int col, int value){
        // if the value is in the range [1, 9]
        if (value < 1 || value > 9) {
            return false;
        }

        // Check if the value is unique in its row
        for (int col_index = 0; col_index < size; col_index++) {
            if (col_index != col ){
                if ( board[row][col_index].getValue() == value) {
                    return false;
                }
            }
        }

        // Check if the value is unique in its column
        for (int row_index = 0; row_index < size; row_index++) {
            if (row_index != row ){
                if( board[row_index][col].getValue() == value) {
                    return false;
                }
            }
        }

        // Check if the value is unique in its local 3x3 square
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i != row && j != col && board[i][j].getValue() == value) {
                    return false;
                }
            }
        }

        return true;

    }


    // returns true if the board is solved
    public boolean validSolution() {

        // Loop over all cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getValue() < 1 || board[i][j].getValue()  > 9) {
                    return false;
                }

                //  if its value is not valid
                if (validValue(i, j, board[i][j].getValue()) == false) {
                    return false;
                }
            }
        }

        return true;
    }



    public boolean read(String filename) {
        try {
        // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
        FileReader fr = new FileReader(filename);
        // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
        BufferedReader br = new BufferedReader(fr);
        
        // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
        String line = br.readLine();
        int row = 0;
        // start a while loop that loops while line isn't null
        while(line != null){
            // print line
        System.out.println( line );
            // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
            String[] arr = line.split( "[ ]+" );
            // let's see what this array holds: 
            System.out.println("the first item in arr: " + arr[0] + ", the second item in arr: " + arr[1]);
            // print the size of the String array (you can use .length)
            System.out.println( arr.length );


            // use the line to set various Cells of this Board accordingly

            for (int col = 0; col < arr.length; col++){
                int value = Integer.valueOf(arr[col]);
                boolean locked = value != 0; // Set locked to true only if value is non-zero
                board[row][col] = new Cell(row, col, value, locked);
            }
            row++;

            // assign to line the result of calling the readLine method of your BufferedReader object.
            line = br.readLine();
        }
        // call the close method of the BufferedReader
        br.close();
        return true;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }
    public String toString(){
        String board_representation = "";
        
        for (int i = 0; i < 9; i++) {
            // Add horizontal separators for every third row
            if (i % 3 == 0 && i != 0) {
                board_representation += "------|-------|------\n";
            }
            
            for (int j = 0; j < 9; j++) {
                // Add vertical separators for every third column
                if (j % 3 == 0 && j != 0) {
                    board_representation += "| ";
                }
                
                board_representation += board[i][j] + " ";
            }
            board_representation += "\n"; // Move to a new line after printing each row
        }
        return board_representation;
    }

    public void draw(Graphics g, int scale){
        for(int i = 0; i<getRows(); i++){
            for(int j = 0; j<getCols(); j++){
                get(i, j).draw(g, j*scale+5, i*scale+10, scale);
            }
        } if(finished){
            if(validSolution()){
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
            }
        }
    }
    


    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("Usage: java [filename] [boardfile] command-line arguments...");
        }

        Board test_board = new Board();
        test_board.read(args[0]);


        System.out.println("Initial Board:");
        System.out.println(test_board);
        System.out.println("Number of Locked Cells: " + test_board.numLocked());

        // Check if the board is solved
        if (test_board.validSolution()) {
            System.out.println("Board is solved.");
        } else {
            System.out.println("Board is not solved.");
        }

        test_board.set(1, 1, 9, false);
        System.out.println("Setting value 9 at (1,1)");
        System.out.println(test_board);
        System.out.println("New value at (1,1)  "  + ": " + test_board.value(1, 1));
        System.out.println("is (1,1) locked? : " + test_board.get(1,1).isLocked());

        
        int numLockedCells = 10;
        Board test_board2 = new Board(numLockedCells);
        System.out.println("Random Board:");
        System.out.println(test_board2);
       
    }
}
