package algorithms.mazeGenerators;

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
    }
    public int[][] getMazeArr(){
        return this.mazeArr;
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
    public  Position getStartPosition(){
        return StartPoint;
    }
    public  Position getGoalPosition(){
        return EndPoint;
    }
    public void Print(){
        int StartIdxRow =getStartPosition().getRowIndex();
        int StartIdxCol = getStartPosition().getColumnIndex();
        int GoalIdxRow= getGoalPosition().getRowIndex();
        int GoalIdxCol= getGoalPosition().getColumnIndex();
        System.out.println("{\n");
        for (int i=0; i< rows; i++){
            System.out.println("{");
            for ( int j=0; j< columns; j++){
                if (i== StartIdxRow && j== StartIdxCol){
                    System.out.println("S");
                }
                else if (i== GoalIdxRow && j== GoalIdxCol){
                    System.out.println("E");
                }
                else {
                    System.out.println(mazeArr[i][j] );
                }
                if (j!=columns-1){
                    System.out.println(',');
                }

            }
            System.out.println("} ,\n" );
        }
        System.out.println("}");
    }

}
