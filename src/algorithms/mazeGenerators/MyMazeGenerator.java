package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MyMazeGenerator class handle our maze
 */
public class MyMazeGenerator extends AMazeGenerator {
    private Maze MyMaze;
    private Position[][] cells;
    private List<Position> candidates;

    public MyMazeGenerator() {
        candidates = new ArrayList<Position>();
    }

    /**
     * create our maze with prim algorithm
     * @param rows number of rows in the maze
     * @param columns number of columns in the maze
     * @return
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows<2)
            rows=2;
        if(columns<2)
            columns=2;
        MyMaze= new Maze(rows,columns);
        cells = new Position[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j=0; j< columns; j++){
                MyMaze.setMazeArr(i,j, 1);
                cells[i][j] = new Position(i, j);
            }
        }
        if (rows==2 && columns ==2)
            MyMaze.setMazeArr(MyMaze.getStartPosition().getRowIndex(),MyMaze.getStartPosition().getColumnIndex(),0);
        candidates.add(MyMaze.getStartPosition()); //add start position to array
        Position currentPosition;
        Position prePosition=null;//////
        while (candidates.size() > 0) { //while array not empty
            currentPosition = getRandomPos();//from candidates list
            if (isChangeAble(currentPosition)) {
                addToPath(currentPosition);//change value to 0
                prePosition = getMyFather(currentPosition);
                connect(prePosition, currentPosition);
                addCandidates(currentPosition);//neighbours are candidates
            }
            candidates.remove(currentPosition);
        }
            if (rows==2 && columns ==2) {
                MyMaze.setMazeArr(MyMaze.getGoalPosition().getRowIndex(), MyMaze.getGoalPosition().getColumnIndex(), 0);
                if (MyMaze.getStartPosition().getColumnIndex()!=MyMaze.getGoalPosition().getColumnIndex() &&
                        (MyMaze.getStartPosition().getRowIndex()!=MyMaze.getGoalPosition().getRowIndex())){
                    if ((MyMaze.getStartPosition().getColumnIndex()==0 &&
                            (MyMaze.getStartPosition().getRowIndex()==0) || (MyMaze.getGoalPosition().getColumnIndex()==0 &&
                                    (MyMaze.getGoalPosition().getRowIndex()==0))))
                        MyMaze.setMazeArr(0,1, 0);
                    else
                        MyMaze.setMazeArr(0,0, 0);
                }
            }
            else
                makeGoalPosition();
        return MyMaze;
    }

    /**
     * find the position that "found" the given position
     * @param p the given position
     * @return the "father" of the given position
     */
    private Position getMyFather(Position p){
        List<Position> neighbours , potentialsFather;
        potentialsFather = new ArrayList<Position>();
        neighbours = myNeighbours(p);
        for (int i = 0; i < neighbours.size(); i++) {
            if (MyMaze.getCellValue(neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 0)
                potentialsFather.add(cells[neighbours.get(i).getRowIndex()][neighbours.get(i).getColumnIndex()]);
        }
        if (potentialsFather.size()==0){
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(potentialsFather.size()); // get a random father
        return potentialsFather.get(index);
    }

    /**
     * connect between to positions - put 0 between them and break the wall
     * @param p1 position 1
     * @param p2 position 2
     * @return if the connection has succeeed, return true, else return false
     */
    private boolean connect(Position p1, Position p2){
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
     * @param p - position from user
     */

    private void addCandidates(Position p) {
        if (p != null) {
            List<Position> neighbours;
            neighbours = myNeighbours(p);
            for (int i = 0; i < neighbours.size(); i++) {
                if (MyMaze.getCellValue(neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 1 && isChangeAble(neighbours.get(i)))
                    candidates.add(neighbours.get(i)); //only legal neighbours and that have 1
            }
        }
    }

    /**
     * changeable cell (from 1 to 0) is a cell that has maximum of one 0 neighbour,
     * @param p - position from user
     */

    private void addToPath(Position p) {
        if (p != null && isChangeAble(p))
            MyMaze.setMazeArr(p.getRowIndex(), p.getColumnIndex(), 0);
    }

    /**
     * checks if currently position can be changed to 0, if so, then do it
     * @param p - position from user
     */

    private boolean isChangeAble(Position p) {
        if (p == null)
            return false;
        if (MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex()) == 0)
            return false;
        return true;
    }

    /**
     * checks if the position is on maze's bounds
     * @param column - column of maze
     * @param row    - row of maze
     */

    private boolean isLegal(int row, int column) {
        if (row < 0 || row >= MyMaze.getRows()) // check if out of bound
            return false;
        return column >= 0 && column < MyMaze.getColumns();
    }

    /**
     * returns a random position from candidates list.
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
        int rows = MyMaze.getRows();
        int columns = MyMaze.getColumns();
        while (!found) {
            Position p= MyMaze.pointsOnFrame(rows, columns);
            if (MyMaze.getCellValue(p.getRowIndex(), p.getColumnIndex()) == 0 &&
                    (p.getRowIndex() != MyMaze.getStartPosition().getRowIndex() || p.getColumnIndex() != MyMaze.getStartPosition().getColumnIndex())) {//legal goal position in last row.
                        MyMaze.setEndPoint(p.getRowIndex(), p.getColumnIndex());
                        found = true;
            }
        }
    }
}