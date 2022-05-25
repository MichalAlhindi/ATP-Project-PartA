package algorithms.maze3D;

public abstract  class  AMaze3DGenerator implements IMazeGenerator3D {
    /**
     * measure the time of creating maze 3D
     * @param depth number of depth in the maze
     * @param row number of rows in the maze
     * @param column number of columns in the maze
     * @return the time took to create the maze
     */
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start = System.currentTimeMillis();
        this.generate(depth, row, column);
        long end = System.currentTimeMillis();
        return end - start;
    }

}
