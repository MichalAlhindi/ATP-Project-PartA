package algorithms.maze3D;

/**
 * the Position3D class handle a position of 3d maze
 */
public class Position3D {
    private int depthIndex;
    private int rowIndex;
    private int colIndex;

    /**
     * set the fields of the position
     * @param depthIndex number of the given depth
     * @param rowIndex number of the given row
     * @param colIndex number of the given column
     */
    public Position3D(int depthIndex, int rowIndex, int colIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    /**
     * @return the depth of the position
     */
    public int getDepthIndex() {
        return depthIndex;
    }

    /**
     * @return the row of the position
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * @return the column of the position
     */
    public int getColumnIndex() {
        return colIndex;
    }

    /**
     * @return a string of the position
     */
    @Override
    public String toString() {
        return "{" + depthIndex + "," + rowIndex + "," + colIndex + '}';
    }
}
