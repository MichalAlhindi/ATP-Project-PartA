package algorithms.maze3D;

import algorithms.search.AState;

public class maze3DState extends AState {

    private int row;
    private int col;
    private int depth;



    public maze3DState(int depth, int row, int col) {
        super();
        this.depth = depth;
        this.row = row;
        this.col = col;

    }

    @Override
    public String toString() {
        String x;
        x = depth + ", " + row + "," + col;
        return x;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDepth() {
        return depth;
    }

    public boolean equals(Object o) {
        if (!(o instanceof maze3DState))
            return false;
        if (((maze3DState) o).depth == depth && ((maze3DState) o).row == row && ((maze3DState) o).col == col)
            return true;
        return false;
    }

    /*public int hashCode() {
        return 3 * (row * 5 + col * 3) + row;
    }*/
}

