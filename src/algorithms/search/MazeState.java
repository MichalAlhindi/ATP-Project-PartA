package algorithms.search;

public class MazeState extends AState{

    private int row;
    private int col;



    public MazeState(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }



    @Override
    public String toString() {
        String x;
        x = row + "," + col;
        return x;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public boolean equals(Object o) {
        if (!(o instanceof MazeState))
            return false;
        return ((MazeState) o).row == row && ((MazeState) o).col == col;
    }

    public int hashCode() {
        return 3 * (row * 5 + col * 3) + row;
    }
}

