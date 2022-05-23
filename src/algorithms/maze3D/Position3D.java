package algorithms.maze3D;

/**
 * the Position3D class handle a position of 3d maze
 */
public class Position3D {
    private int depthIndex;
    private int rowIndex;
    private int colIndex;

    public Position3D(int depthIndex, int rowIndex, int colIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return colIndex;
    }

    @Override
    public String toString() {
        return "{" + depthIndex + "," + rowIndex + "," + colIndex + '}';
    }
}
