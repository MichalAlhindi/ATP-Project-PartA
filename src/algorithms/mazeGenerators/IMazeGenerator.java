package algorithms.mazeGenerators;

/**
 * the IMazeGenerator interface is for creating some type of maze
 */
public interface IMazeGenerator {
    /**
     * generates a maze
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return the maze that was built
     */
    Maze generate(int rows, int columns);

    /**
     * measure the time of creating maze
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return the time took to create the maze
     */
    long measureAlgorithmTimeMillis(int rows, int columns);
}
