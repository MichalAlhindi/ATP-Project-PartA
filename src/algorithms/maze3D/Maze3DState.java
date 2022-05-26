package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {

    private int row;
    private int col;
    private int depth;

    /**
     * constructor to initialize the 3D state fields
     * @param depth the maze depth
     * @param row the number of rows in each depth of the maze
     * @param col the number of columns in each depth of the maze
     */
    public Maze3DState(int depth, int row, int col) {
        super();
        this.depth = depth;
        this.row = row;
        this.col = col;

    }

    /**
     * @return the state
     */
    @Override
    public String toString() {
        String x;
        x = depth + "," + row + "," + col;
        return x;
    }

    /**
     * @return the number of rows in each depth of the maze
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the number of columns in each depth of the maze
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the depth of the maze
     */
    public int getDepth() {
        return depth;
    }

    /**
     * check if an input state is the same as this one
     * @param o the state
     * @return weather the input state equals to this one
     */
    public boolean equals(Object o) {
        if (!(o instanceof Maze3DState))
            return false;
        if (((Maze3DState) o).depth == depth && ((Maze3DState) o).row == row && ((Maze3DState) o).col == col)
            return true;
        return false;
    }


}

