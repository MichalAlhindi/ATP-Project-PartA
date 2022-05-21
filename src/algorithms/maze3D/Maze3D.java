package algorithms.maze3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze3D {
    private int depthNum;
    private int rowNum;
    private int colNum;
    private Position3D startPosition;
    private Position3D goalPosition;
    private int[][][] map;

    public Maze3D(int depthNum, int rowNum, int colNum) {
        this.depthNum = depthNum;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.map= new int[depthNum][rowNum][colNum];

        Position3D p1 ,p2;
        p1= pointsOnFrame(rowNum,colNum,depthNum);
        p2=pointsOnFrame(rowNum,colNum,depthNum);
        while (p1.getRowIndex()== p2.getRowIndex() && p1.getColumnIndex()== p2.getColumnIndex()){
            p2=pointsOnFrame(rowNum,colNum,depthNum);
        }
        setStart(p1);
        setGoal(p2);
    }

    public Position3D pointsOnFrame(int rows, int columns, int depths){
        List<Position3D> points;
        points = new ArrayList<Position3D>();
        Random r= new Random();
        int first= r.nextInt(rows);
        int second= r.nextInt(columns);
        int third = r.nextInt(depths);
        // (0,0-c) ,(0-r,0) , (r-1, 0-c) , (0-r, c-1),
        points.add(new Position3D(third,0,second));
        points.add(new Position3D(third,first,0));
        points.add(new Position3D(third,rows-1,r.nextInt(columns)));
        points.add(new Position3D(third,r.nextInt(rows),columns-1));
        Random random = new Random();
        int index = random.nextInt(points.size());
        return points.get(index);
    }

    public Maze3D() { }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }

    public int[][][] getMap() {
        return map;
    }

    public int getDepth() {
        return depthNum;
    }

    public int getRow() {
        return rowNum;
    }

    public int getCol() {
        return colNum;
    }

    public void setDepth(int depthNum) {
        this.depthNum = depthNum;
    }

    public void setRow(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setCol(int colNum) {
        this.colNum = colNum;
    }

    public void setStart(Position3D start) {
        this.startPosition = start;
    }

    public void setGoal(Position3D goal) {
        this.goalPosition = goal;
    }

    public void setMap(int[][][] map) {
        this.map = map;
    }

    public void setMazeArr3D(int d, int r, int c, int val){
        this.map[d][r][c]= val;
    }


    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < map.length; depth++){
            for(int row = 0; row < map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < map[0][0].length; col++) {
                    if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(map[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    /**
     * @param row
     * @param col
     * @return the cell value(int) and in case the indexes not part of the maze return -1.
     */
    public int getCellValue(int depth, int row, int col) {
        if ( depth < this.depthNum && row < this.rowNum && col < this.colNum && depth >= 0 && row >= 0 && col >=0) {
            return this.map[depth][row][col];
        }
        else {return -1;}
    }
}



