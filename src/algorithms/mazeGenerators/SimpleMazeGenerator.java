package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator(){}

    @Override
    public Maze generate(int rows, int columns) {
        Maze SimpleMaze= new Maze(rows,columns);
        int MazeValue;
        for (int i=0; i<rows; i++){
            for (int j=0; j< columns; j++){
                MazeValue = (Math.random() < 0.5) ? 0 : 1;
                SimpleMaze.setMazeArr(i, j, MazeValue);
            }
        }
        for (int i=0 ; i<columns; i++){
            SimpleMaze.setMazeArr(0, i, 0);
            SimpleMaze.setMazeArr(rows-1, i, 0);
        }
        for (int i=0 ; i<rows; i++){
            SimpleMaze.setMazeArr(i, 0, 0);
            SimpleMaze.setMazeArr(i, columns-1, 0);
        }
        return SimpleMaze;
    }
}
