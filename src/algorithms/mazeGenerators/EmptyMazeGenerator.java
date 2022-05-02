package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator(){

    }
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
