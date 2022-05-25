package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the Maze class is for handle a maze
 */
public class Maze {
    private int rows;
    private int columns;
    private int[][] mazeArr;
    private Position StartPoint;
    private Position EndPoint;

    /**
     * maze consturctor, initiallize it's fields
     * @param r number of rows in the maze
     * @param c number of columns in the maze
     */
    public Maze(int r, int c){
        this.rows=r;
        this.columns=c;
        this.mazeArr= new int[r][c];
        Position p1 ,p2;
        p1= pointsOnFrame(rows,columns);
        p2=pointsOnFrame(rows,columns);
        while (p1.getRowIndex()== p2.getRowIndex() && p1.getColumnIndex()== p2.getColumnIndex()){
            p2=pointsOnFrame(rows,columns);
        }
        setStartPoint(p1.getRowIndex(), p1.getColumnIndex());
        setEndPoint(p2.getRowIndex(), p2.getColumnIndex());
    }

    /**
     * @return the maze in an array
     */
    public int[][] getMazeArr(){
        return this.mazeArr;
    }
    /**
     * picking randomly a point from the maze frame
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return a position (point) on the frame of the maze
     */
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

    /**
     * set the start point of the maze
     * @param r number of rows in the maze
     * @param c number of columns in the maze
     */
    public void setStartPoint(int r, int c) {
        this.StartPoint = new Position(r,c);
    }

    /**
     * set the end point of the maze
     * @param r number of rows in the maze
     * @param c number of columns in the maze
     */
    public void setEndPoint(int r, int c) {
        this.EndPoint = new Position(r, c);
    }

    /**
     * set value in specific index of the maze
     * @param r number of rows in the maze
     * @param c number of columns in the maze
     * @param val the value to set
     */
    public void setMazeArr(int r, int c, int val){
        this.mazeArr[r][c]= val;
    }

    /**
     * return the value of a specific point
     * @param row the row index
     * @param column the column index
     * @return the value
     */
    public int getCellValue(int row, int column) {
        return mazeArr[row][column];
    }

    /**
     * @return number of the rows in the maze
     */
    public int getRows() {
        return mazeArr.length;
    }

    /**
     * @return number of the columns in the maze
     */
    public int getColumns() {
        return mazeArr[0].length;
    }

    /**
     * @return the start point of the maze
     */
    public Position getStartPosition(){
        return StartPoint;
    }

    /**
     * @return the end point of the maze
     */
    public Position getGoalPosition(){
        return EndPoint;
    }

    /**
     * print the maze
     */
    public void print(){
        int StartIdxRow =getStartPosition().getRowIndex();
        int StartIdxCol = getStartPosition().getColumnIndex();
        int GoalIdxRow= getGoalPosition().getRowIndex();
        int GoalIdxCol= getGoalPosition().getColumnIndex();
        System.out.print("{");
        for (int i=0; i< rows; i++){
            System.out.print("{");
            for ( int j=0; j< columns; j++){
                if (i== StartIdxRow && j== StartIdxCol){
                    System.out.print("S");
                }
                else if (i== GoalIdxRow && j== GoalIdxCol){
                    System.out.print("E");
                }
                else {
                    System.out.print(mazeArr[i][j] );
                }
                if (j!=columns-1){
                    System.out.print(',');
                }

            }
            if (i!=rows-1)
                System.out.print("}\n," );
            else
                System.out.print("}");
        }
        System.out.print("}\n");
    }

}
