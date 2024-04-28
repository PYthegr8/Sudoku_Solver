/*
 * Papa Yaw Owusu Nti
 * April 11th, 2024
 * CS231 B
 * Project 5
 * 
 * Description: The Cell Tests test the functionality of the various constructors and methods in the cell class

*/

public class CellTests {

    public static void main(String[] args) {
        // Test case 1: Creating a Cell with default locked status
        {
            // Setup
            Cell cell = new Cell(0, 0, 5);

            // Verify
            System.out.println(cell.getRow() + " == 0");
            System.out.println(cell.getCol() + " == 0");
            System.out.println(cell.getValue() + " == 5");
            System.out.println(cell.isLocked() + " == false");

            // Test
            assert cell.getRow() == 0 : "Error in Cell::getRow()";
            assert cell.getCol() == 0 : "Error in Cell::getCol()";
            assert cell.getValue() == 5 : "Error in Cell::getValue()";
            assert !cell.isLocked() : "Error in Cell::isLocked()";
        }

        // Test case 2: Creating a Cell with specified locked status
        {
            // Setup
            Cell cell = new Cell(1, 1, 3, true);

            // Verify
            System.out.println(cell.getRow() + " == 1");
            System.out.println(cell.getCol() + " == 1");
            System.out.println(cell.getValue() + " == 3");
            System.out.println(cell.isLocked() + " == true");

            // Test
            assert cell.getRow() == 1 : "Error in Cell::getRow()";
            assert cell.getCol() == 1 : "Error in Cell::getCol()";
            assert cell.getValue() == 3 : "Error in Cell::getValue()";
            assert cell.isLocked() : "Error in Cell::isLocked()";
        }

        // Test case 3: Modifying value and locked status of a Cell
        {
            // Setup
            Cell cell = new Cell(2, 2, 7);

            // Modify value and locked status
            cell.setValue(9);
            cell.setLocked(true);

            // Verify
            System.out.println(cell.getRow() + " == 2");
            System.out.println(cell.getCol() + " == 2");
            System.out.println(cell.getValue() + " == 9");
            System.out.println(cell.isLocked() + " == true");

            // Test
            assert cell.getRow() == 2 : "Error in Cell::getRow()";
            assert cell.getCol() == 2 : "Error in Cell::getCol()";
            assert cell.getValue() == 9 : "Error in Cell::getValue()";
            assert cell.isLocked() : "Error in Cell::isLocked()";
        }
    }
}
