package algorithms.mazeGenerators;

public class Position {
    private int rowIdx;
    private int colIdx;

    public Position(int row, int column){
        this.colIdx=column;
        this.rowIdx=row;

    }
    public int getRowIndex(){
        return rowIdx;
    }
    public int getColumnIndex(){
        return colIdx;
    }

    @Override
    public String toString() {
        return "{" +
                 + rowIdx +
                "," + colIdx +
                '}';
    }



}
