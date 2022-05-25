package algorithms.maze3D;

public interface IMazeGenerator3D {
    /**
     * generates a maze 3D
     * @param depth number of depth in the maze
     * @param row number of rows in the maze
     * @param column number of columns in the maze
     * @return the maze that was built
     */
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);
}
