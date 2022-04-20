package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends  AMazeGenerator{
    public SimpleMazeGenerator(){

    }
    @Override
    public Maze generate(int rows, int columns) {
        Maze SimpleMaze= new Maze(rows,columns);
        SimpleMaze.setStartPoint(0,0);
        SimpleMaze.setEndPoint(rows-1, columns-1);
        for (int i=0; i<rows; i++){
            for (int j=0; j< columns; j++){
                SimpleMaze.setMazeArr(i,j, );
            }

        }
        return SimpleMaze;
    }
}
