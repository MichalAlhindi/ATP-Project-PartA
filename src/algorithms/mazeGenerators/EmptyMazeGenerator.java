package algorithms.mazeGenerators;

/**
 * the EmptyMazeGenerator class is for creating an empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    public EmptyMazeGenerator(){}

    /**
     * generates an empty maze
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return the empty maze that was built
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze EmptyMaze= new Maze(rows,columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j< columns; j++){
                EmptyMaze.setMazeArr(i,j, 0);
            }
        }
        return EmptyMaze;
    }
}
