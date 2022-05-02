package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {

    private int rows;
    private int columns;
    private int[][] mazeArr;
    private Position StartPoint;
    private Position EndPoint;

    public Maze(int r, int c){
        this.rows=r;
        this.columns=c;
        this.mazeArr= new int[r][c];
       // this.StartPoint= new Position(0,0); //default
//        Random r2 = new Random();
//        int low1 = 0;
//        int high1 = rows-1;
        Position p1 ,p2;
        p1= pointsOnFrame(rows,columns);
        p2=pointsOnFrame(rows,columns);
        while (p1.getRowIndex()== p2.getRowIndex() && p1.getColumnIndex()== p2.getColumnIndex()){
            p2=pointsOnFrame(rows,columns);
        }
        setStartPoint(p1.getRowIndex(), p1.getColumnIndex());
       setEndPoint(p2.getRowIndex(), p2.getColumnIndex());
    }
    public int[][] getMazeArr(){
        return this.mazeArr;
    }
//check if points on frame!!!
    public Position pointsOnFrame(int rows, int columns){
        List<Position> points;
        points = new ArrayList<Position>();
        Random r= new Random();
        int first= r.nextInt(rows);
        int second= r.nextInt(columns);
        // (0,0-c) ,(0-r,0) , (r-1, 0-c) , (0-r, c-1)
        points.add(new Position(0,second));
        points.add(new Position(first,0));
        points.add(new Position(rows-1,r.nextInt(columns)));
        points.add(new Position(r.nextInt(rows),columns-1));
        Random random = new Random();
        int index = random.nextInt(points.size());
        return points.get(index);
    }
    public void setStartPoint(int r, int c) {

        this.StartPoint = new Position(r,c);
    }
    public void setEndPoint(int r, int c) {
        this.EndPoint = new Position(r, c);

    }
    public void setMazeArr(int r, int c, int val){
        this.mazeArr[r][c]= val;
    }

    public int getCellValue(int row, int column) {
        return mazeArr[row][column];
    }

    public int numOfRows() {
        return mazeArr.length;
    }


    public int numOfColumns() {
        return mazeArr[0].length;
    }

    public  Position getStartPosition(){
        return StartPoint;
    }
    public  Position getGoalPosition(){
        return EndPoint;
    }
    public void print(){

            for (int i = 0; i < mazeArr.length; i++) {
                System.out.println(" "); //go down at end of line of array
                for (int j = 0; j < mazeArr[0].length; j++)
                    if (i == getStartPosition().getRowIndex() && j == getStartPosition().getColumnIndex())
                        System.out.print(" S"); //start position
                    else if (i == getGoalPosition().getRowIndex() && j == getGoalPosition().getColumnIndex())
                        System.out.print(" E"); //end position
                    else
                        System.out.print(" " + mazeArr[i][j]); //print if 0 or 1 and add space
            }
            System.out.println(" "); //just so it looks better
        }

//        int StartIdxRow =getStartPosition().getRowIndex();
//        int StartIdxCol = getStartPosition().getColumnIndex();
//        int GoalIdxRow= getGoalPosition().getRowIndex();
//        int GoalIdxCol= getGoalPosition().getColumnIndex();
//        System.out.println("{\n");
//        for (int i=0; i< rows; i++){
//            System.out.println("{");
//            for ( int j=0; j< columns; j++){
//                if (i== StartIdxRow && j== StartIdxCol){
//                    System.out.println("S");
//                }
//                else if (i== GoalIdxRow && j== GoalIdxCol){
//                    System.out.println("E");
//                }
//                else {
//                    System.out.println(mazeArr[i][j] );
//                }
//                if (j!=columns-1){
//                    System.out.println(',');
//                }
//
//            }
//            System.out.println("} ,\n" );
//        }
//        System.out.println("}");
//    }

}
