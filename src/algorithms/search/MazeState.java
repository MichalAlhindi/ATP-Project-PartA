package algorithms.search;

import java.io.Serializable;

/**
 *class represents a state of the maze
 */
public class MazeState extends AState implements Serializable {
    private int row;
    private int col;

    /**
     *constructor of the maze state
     * @param row of the maze
     * @param col of the maze
     */
    public MazeState(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     *@return a string of the maze state
     */
    @Override
    public String toString() {
        String x;
        x = row + "," + col;
        return x;
    }

    /**
     *@return the row of the maze state
     */
    public int getRow() {
        return row;
    }

    /**
     *@return the col of the maze state
     */
    public int getCol() {
        return col;
    }

    /**
     *@return if object maze state equals to this maze state
     */
    public boolean equals(Object o) {
        if (!(o instanceof algorithms.search.MazeState))
            return false;
        return ((algorithms.search.MazeState) o).row == row && ((algorithms.search.MazeState) o).col == col;
    }

    public int hashCode() {
        return 3 * (row * 5 + col * 3) + row;
    }
}

