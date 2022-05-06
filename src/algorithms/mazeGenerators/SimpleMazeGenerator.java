package algorithms.mazeGenerators;


public class SimpleMazeGenerator extends AMazeGenerator {
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
    for (int i=0 ; i<columns; i++){
        SimpleMaze.setMazeArr(0, i, 0);
        SimpleMaze.setMazeArr(rows-1, i, 0);
    }
        for (int i=0 ; i<rows; i++){
            SimpleMaze.setMazeArr(i, 0, 0);
            SimpleMaze.setMazeArr(i, columns-1, 0);
        }
//        int x1 = SimpleMaze.getStartPosition().getRowIndex();
//        int y1 = SimpleMaze.getStartPosition().getColumnIndex();
//        int x2 = SimpleMaze.getGoalPosition().getRowIndex();
//        int y2 = SimpleMaze.getGoalPosition().getColumnIndex();
//        //int Direction;
//        //make sure maze has a solution
//
//        if( x1==x2 && y1 < y2){
//            for(int j= y1; j<y2 +1;j++)
//                SimpleMaze.setMazeArr(x1, j , 0);
//        }
//        if( x1==x2 && y2 < y1){
//            for(int j= y2; j<y1 +1;j++)
//                SimpleMaze.setMazeArr(x1, j , 0);
//        }
//        if( y1==y2 && x1 < x2){
//            for(int j= x1; j<x2 +1;j++)
//                SimpleMaze.setMazeArr(j, y1 , 0);
//        }
//        if( y1==y2 && x2 < x1){
//            for(int j= x2; j<x1 +1;j++)
//                SimpleMaze.setMazeArr(j, y1 , 0);
//        }
//        while (!(x1==x2 && y1 == y2)){
//
//        }
//        while (!(i == rows - 1 && j == columns - 1)) {
//            if (i == rows - 1) {
//                SimpleMaze.setMazeArr(i, j + 1, 0);
//                j++;
//            } else if (j == columns - 1) {
//                SimpleMaze.setMazeArr(i + 1, j, 0);
//                i++;
//            } else {
//                Direction = (Math.random() < 0.5) ? 0 : 1;
//                if (Direction == 1) {
//                    SimpleMaze.setMazeArr(i + 1, j, 0);
//                    i++;
//                } else {
//                    SimpleMaze.setMazeArr(i, j + 1, 0);
//                    j++;
//                }
//            }
//        }
        return SimpleMaze;
    }
}
