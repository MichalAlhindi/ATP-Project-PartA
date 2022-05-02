package algorithms.mazeGenerators;

import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Math.random;

public class SimpleMazeGenerator extends  AMazeGenerator{
    public SimpleMazeGenerator(){

    }
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

        //set a random start point
        int MazeStartPoint = (int) (Math.random() * rows);

        SimpleMaze.setStartPoint(MazeStartPoint, 0);
        //end position is at corner on right bottom

        SimpleMaze.setEndPoint(rows - 1, columns - 1);
        int i = MazeStartPoint, j = 0;
        int Direction;
        //make sure maze has a solution

        while (!(i == rows - 1 && j == columns - 1)) {
            if (i == rows - 1) {
                SimpleMaze.setMazeArr(i, j + 1, 0);
                j++;
            } else if (j == columns - 1) {
                SimpleMaze.setMazeArr(i + 1, j, 0);
                i++;
            } else {
                Direction = (Math.random() < 0.5) ? 0 : 1;
                if (Direction == 1) {
                    SimpleMaze.setMazeArr(i + 1, j, 0);
                    i++;
                } else {
                    SimpleMaze.setMazeArr(i, j + 1, 0);
                    j++;
                }
            }
        }
        return SimpleMaze;
    }
}
