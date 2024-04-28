/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description: The Cell class represents one location in a Sudoku board.It can take on the values 1 - 9. 
                * A locked cell's value will be read from an input file
                * and an unlocked cell's value is to be determined by the Sudoku solver. 
                * It contains a constructor to initialize the Cell with a value, and various getter and setter methods

*/
import java.awt.Color;
import java.awt.Graphics;

public class Cell {

    private int row_index;
    private int column_index;
    private int value;
    private boolean locked;

    //  - initialize the row, column, and value fields to the given parameter values. Set the locked field to false;
    public Cell(int row, int col, int value){
        this.row_index = row;
        this.column_index = col;
        this.value = value;
        this.locked = false;
    }

    //  - initialize all of the Cell fields given the parameters.
    public Cell(int row, int col, int value, boolean locked){
        this.row_index = row;
        this.column_index = col;
        this.value = value;
        this.locked = locked;
    }


    // return the Cell's row index.
    public int getRow(){
        return this.row_index;
    }

    // return the Cell's column index.
    public int getCol() {
        return this.column_index;
    }

    // return the Cell's value.
    public int getValue() {
        return this.value;
    }

    // set the Cell's value.
    public void setValue(int newval){
        this.value = newval;
    }

    // return the value of the locked field.
    public boolean isLocked() {
        return this.locked;
    }

    // set the Cell's locked field to the new value.
    public void setLocked(boolean lock) {
        this.locked = lock;
    }


    //return a string with the Cell's numeric value. 
    public String toString() {
        String res = "";
        res+= getValue();
        return res;
    }

    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}
