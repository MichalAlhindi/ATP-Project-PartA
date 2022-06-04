package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * the Position class handle a position of the maze
 */
public class Position implements Serializable {
    private int rowIdx;
    private int colIdx;

    /**
     * set the fields of the position
     * @param row number of the given row
     * @param column number of the given column
     */
    public Position(int row, int column) {
        this.colIdx = column;
        this.rowIdx = row;
    }

    /**
     * @return the row of the position
     */
    public int getRowIndex() {
        return rowIdx;
    }

    /**
     * @return the column of the position
     */
    public int getColumnIndex(){
        return colIdx;
    }

    /**
     * @return a string of the position
     */
    @Override
    public String toString() {
        return "{" +
                 + rowIdx +
                "," + colIdx +
                '}';
    }
}
