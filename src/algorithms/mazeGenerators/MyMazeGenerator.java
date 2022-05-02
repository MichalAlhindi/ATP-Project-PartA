package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{

    private Maze MyMaze;
    private Position[][] cells;
    private List<Position> candidates;


    public MyMazeGenerator() {
        candidates = new ArrayList<Position>();
    }

    @Override
    public Maze generate(int rows, int columns) {
         MyMaze= new Maze(rows,columns);

        cells = new Position[rows][columns];

        for (int i=0; i<rows; i++){
            for (int j=0; j< columns; j++){
                MyMaze.setMazeArr(i,j, 1);
                cells[i][j] = new Position(i, j);
            }

        }
////DOOOOOOOOOOOOOOOOOO
        candidates.add(MyMaze.getStartPosition()); //add start position to array
        Position currentPosition;
        Position prePosition=null;//////
        while (candidates.size() > 0) { //while array not empty
            currentPosition = getRandomPos();//from candidates list
            if (isChangeAble(currentPosition)) {
                addToPath(currentPosition);//change value to 0
                prePosition=getMyFather(currentPosition);
                connect(prePosition,currentPosition);
                addCandidates(currentPosition);//neighbours are candidates
            }

            candidates.remove(currentPosition);
        }
        makeGoalPosition();
        return MyMaze;
    }

    public Position getMyFather(Position p){
        List<Position> neighbours , potentialsFather;
        potentialsFather = new ArrayList<Position>();
        neighbours = myNeighbours(p);

        for (int i = 0; i < neighbours.size(); i++)
            if (MyMaze.getCellValue(neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 0)
                potentialsFather.add(cells[neighbours.get(i).getRowIndex()][neighbours.get(i).getColumnIndex()]);
        if (potentialsFather.size()==0){
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(potentialsFather.size()); // get a random father
        return potentialsFather.get(index);
    }
    public boolean connect(Position p1, Position p2){
        if (p1==null ||p2==null)
            return false;
        else{
            if (p1.getRowIndex()==p2.getRowIndex()) {
                if (p1.getColumnIndex() < p2.getColumnIndex())
                    MyMaze.setMazeArr(p1.getRowIndex(), p1.getColumnIndex() + 1, 0);
                if (p1.getColumnIndex() > p2.getColumnIndex())
                    MyMaze.setMazeArr(p1.getRowIndex(), p1.getColumnIndex() - 1, 0);
            }
            else  if (p1.getColumnIndex()== p2.getColumnIndex()){
                if (p1.getRowIndex() < p2.getRowIndex())
                    MyMaze.setMazeArr(p1.getRowIndex()+1, p1.getColumnIndex() , 0);
                if (p1.getRowIndex() > p2.getRowIndex())
                    MyMaze.setMazeArr(p1.getRowIndex()-1, p1.getColumnIndex() , 0);
            }
            return true;
        }

    }
    /**
     * returns array list of 2-4 neighbours of a position
     *
     * @param p - position from user
     */

    private List<Position> myNeighbours(Position p) {
        if (p == null)
            return null;
        List<Position> neighbours;
        neighbours = new ArrayList<Position>();
        //check if can go up down left or right if so, add to list
        if (isLegal(p.getRowIndex() - 2, p.getColumnIndex()))
            neighbours.add(cells[p.getRowIndex() - 2][p.getColumnIndex()]);
        if (isLegal(p.getRowIndex() + 2, p.getColumnIndex()))
            neighbours.add(cells[p.getRowIndex() + 2][p.getColumnIndex()]);
        if (isLegal(p.getRowIndex(), p.getColumnIndex() - 2))
            neighbours.add(cells[p.getRowIndex()][p.getColumnIndex() - 2]);
        if (isLegal(p.getRowIndex(), p.getColumnIndex() + 2))
            neighbours.add(cells[p.getRowIndex()][p.getColumnIndex() + 2]);
        return neighbours; //return list
    }

    /**
     * add to candidates<position> list all position's neighbours who might become a path.
     *
     * @param p - position from user
     */

    private void addCandidates(Position p) {
        if (p != null) {
            List<Position> neighbours;
            neighbours = myNeighbours(p);
            for (int i = 0; i < neighbours.size(); i++)
                if (MyMaze.getCellValue(neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 1 && isChangeAble(neighbours.get(i)))
                    candidates.add(neighbours.get(i)); //only legal neighbours and that have 1
        }
    }

    /**
     * changeable cell (from 1 to 0) is a cell that has maximum of one 0 neighbour,
     *
     * @param p - position from user
     */

    private void addToPath(Position p) {
        if (p != null && isChangeAble(p))
            MyMaze.setMazeArr(p.getRowIndex(), p.getColumnIndex(), 0);
    }

    /**
     * checks if currently position can be changed to 0, if so, then do it
     *
     * @param p - position from user
     */

    private boolean isChangeAble(Position p) {
        if (p == null)
            return false;
        if (MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex()) == 0)
            return false;
        return true;
      // return (numOfPathNeighbours(p) <= 1);
    }

    /**
     * returns number of neighbours which are already on the path
     *
     * @param p - position from user
     */

//    private int numOfPathNeighbours(Position p) {
//        if (p == null)
//            return 0;
//        int count = 0;
//        //if neighbour legal and has 0 count++;
//        if (isLegal(p.getRowIndex(), p.getColumnIndex() + 2) &&
//                MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex() + 2) == 0)
//            count++;
//        if (isLegal(p.getRowIndex(), p.getColumnIndex() - 2) &&
//                MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex() - 2) == 0)
//            count++;
//        if (isLegal(p.getRowIndex() + 2, p.getColumnIndex()) &&
//                MyMaze.getCellValue(p.getRowIndex() + 2, p.getColumnIndex()) == 0)
//            count++;
//        if (isLegal(p.getRowIndex() - 2, p.getColumnIndex()) &&
//                MyMaze.getCellValue(p.getRowIndex() - 2, p.getColumnIndex()) == 0)
//            count++;
//        return count;
//    }

    /**
     * checks if the position is on maze's bounds
     *
     * @param column - column of maze
     * @param row    - row of maze
     */

    private boolean isLegal(int row, int column) {
        if (row < 0 || row >= MyMaze.numOfRows()) // check if out of bound
            return false;
        return column >= 0 && column < MyMaze.numOfColumns();
    }


    /**
     * returns a random position from candidates list.
     *
     * @return - random position
     */
    private Position getRandomPos() {
        Random random = new Random();
        int index = random.nextInt(candidates.size()); // get a random candidate.
        return candidates.get(index);
    }

    /**
     * set a random goal position
     */

    private void makeGoalPosition() {

        boolean found = false;
        int rows = MyMaze.numOfRows();
        int columns = MyMaze.numOfColumns();

        while (!found) {
            Position p= MyMaze.pointsOnFrame(rows, columns);

            if (MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex()) == 0) {//legal goal position in last row.
                MyMaze.setEndPoint(p.getRowIndex(), p.getColumnIndex());
                found = true;
            }

        }
    }
}



