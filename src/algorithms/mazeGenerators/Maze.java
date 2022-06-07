package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.*;

/**
 * the Maze class is for handle a maze
 */
public class Maze implements Serializable {
    private int rows;
    private int columns;
    private int[][] mazeArr;
    private Position StartPoint;
    private Position EndPoint;

    /**
     * maze consturctor, initialize it's fields
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
     * maze constructor, using a byte array with details of the maze
     * @param byteArr the array with details of the maze
     */
    public Maze(byte[] byteArr){
        //rows = 0;
        rows = byteArr[0] < 0 ? byteArr[0]+256 : byteArr[0];
        rows |= (byteArr[1] << 8);
        columns = byteArr[2]< 0 ? byteArr[2]+256 : byteArr[2];;
        columns |= (byteArr[3] << 8);
        int rowStart = byteArr[4]< 0 ? byteArr[4]+256 : byteArr[4];;
        rowStart |= (byteArr[5] << 8);
        int colStart= byteArr[6]< 0 ? byteArr[6]+256 : byteArr[6];;
        colStart |= (byteArr[7] << 8);
        int rowEnd = byteArr[8]< 0 ? byteArr[8]+256 : byteArr[8];;
        rowEnd |= (byteArr[9] << 8);
        int colEnd= byteArr[10]< 0 ? byteArr[10]+256 : byteArr[10];;
        colEnd |= (byteArr[11] << 8);
        setStartPoint(rowStart, colStart);
        setEndPoint(rowEnd,colEnd);
        this.mazeArr= new int[rows][columns];
        int b = 12;
        for (int r=0; r<rows; r++){
            for (int c=0; c<columns; c++){
                setMazeArr(r,c,byteArr[b]);
                b++;
            }
        }
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

    /**
     *  the details of the maze get in an array of bytes
     * @return the maze in byte array
     */
    public byte[] toByteArray(){
        byte[] byteArr = new byte[rows*columns+12];
        byteArr[0] = (byte)(rows & 255);
        byteArr[1] = (byte)((rows >> 8) & 255);
        byteArr[2] = (byte)(columns & 255);
        byteArr[3] = (byte)((columns >> 8) & 255);
        byteArr[4] = (byte)(getStartPosition().getRowIndex()& 255);
        byteArr[5] = (byte)((getStartPosition().getRowIndex() >> 8 ) & 255);
        byteArr[6] = (byte)(getStartPosition().getColumnIndex() & 255);
        byteArr[7] = (byte)((getStartPosition().getColumnIndex() >> 8 ) & 255);
        byteArr[8] = (byte)(getGoalPosition().getRowIndex() & 255);
        byteArr[9] = (byte)((getGoalPosition().getRowIndex() >> 8 ) & 255);
        byteArr[10] = (byte)(getGoalPosition().getColumnIndex() & 255);
        byteArr[11] = (byte)((getGoalPosition().getColumnIndex() >> 8 ) & 255);
        int b=12;
        for(int r=0; r<rows; r++){
            for(int c=0; c<columns; c++){
                byteArr[b] = (byte)getCellValue(r,c);
                b++;
            }
        }
        return byteArr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        return rows == maze.rows && columns == maze.columns && Arrays.equals(mazeArr, maze.mazeArr) && StartPoint.equals(maze.StartPoint) && EndPoint.equals(maze.EndPoint);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns, StartPoint, EndPoint);
        result = 31 * result + Arrays.hashCode(mazeArr);
        return result;
    }
}
