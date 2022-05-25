package algorithms.maze3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * MyMaze3DGenerator class handle our maze
 */
public class MyMaze3DGenerator extends  AMaze3DGenerator{
    private Maze3D MyMaze;
    private Position3D[][][] cells;
    private List<Position3D> candidates;

    public MyMaze3DGenerator() {
        candidates = new ArrayList<Position3D>();
    }
    /**
     * create our maze with prim algorithm
     * @param depth number of depth in the maze
     * @param row number of rows in the maze
     * @param column number of columns in the maze
     * @return the maze 3D
     */
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if (row < 2)
            row = 2;
        if (column < 2)
            column = 2;
        if (depth < 2)
            depth = 2;
        if (depth == 2 && row == 2 && column == 2){
            depth = 3;
            row = 3;
            column = 3;
        }
            MyMaze= new Maze3D(depth,row,column);
            cells = new Position3D[depth][row][column];
            for (int d=0; d<depth; d++) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        MyMaze.setMazeArr3D(d, i, j, 1);
                        cells[d][i][j] = new Position3D(d, i, j);
                    }
                }
            }
            candidates.add(MyMaze.getStartPosition()); //add start position to array
            Position3D currentPosition, goal = null;
            Position3D prePosition=null;//////
            while (candidates.size() > 0) { //while array not empty
                currentPosition = getRandomPos();//from candidates list
                if (isChangeAble(currentPosition)) {
                    addToPath(currentPosition);//change value to 0
                    prePosition=getMyFather(currentPosition);
                    connect(prePosition,currentPosition);
                    addCandidates(currentPosition);//neighbours are candidates
                }
                candidates.remove(currentPosition);
                goal=currentPosition;
            }

         if (depth==2 && (row==2 || column==2)) {
             MyMaze.setGoal(goal);
             if (goal.getColumnIndex()!= MyMaze.getStartPosition().getColumnIndex() || goal.getRowIndex()!= MyMaze.getStartPosition().getRowIndex()
              || goal.getDepthIndex()!= MyMaze.getStartPosition().getDepthIndex()){
                return MyMaze;
             }
             else{
                 if (column==2) {
                     Position3D newgoal = new Position3D(MyMaze.getStartPosition().getDepthIndex(), MyMaze.getStartPosition().getRowIndex() - 1, MyMaze.getStartPosition().getColumnIndex());
                     MyMaze.setMazeArr3D(MyMaze.getStartPosition().getDepthIndex(), MyMaze.getStartPosition().getRowIndex() - 1,MyMaze.getStartPosition().getColumnIndex() ,0);
                     MyMaze.setGoal(newgoal);
                     return MyMaze;
                 }
                 if (row ==2){
                     Position3D newgoal = new Position3D(MyMaze.getStartPosition().getDepthIndex(), MyMaze.getStartPosition().getRowIndex() ,MyMaze.getStartPosition().getColumnIndex()-1);
                     MyMaze.setMazeArr3D(MyMaze.getStartPosition().getDepthIndex(), MyMaze.getStartPosition().getRowIndex() ,MyMaze.getStartPosition().getColumnIndex()-1,0);
                     MyMaze.setGoal(newgoal);
                     return MyMaze;
                 }
             }
         }
         if (row==2 && column==2 && depth==3){
             if( MyMaze.getStartPosition().getDepthIndex()==1 ){
                 if ((MyMaze.getStartPosition().getRowIndex()==0 && MyMaze.getStartPosition().getColumnIndex()==0) ||
                         (MyMaze.getStartPosition().getRowIndex()==1 && MyMaze.getStartPosition().getColumnIndex()==1)) {
                     MyMaze.setMazeArr3D(1,1,0,0);
                     MyMaze.setGoal(new Position3D(1, 1, 0));
                     return MyMaze;
                 }
                 MyMaze.setMazeArr3D(1,0,0,0);
                 MyMaze.setGoal(new Position3D(0, 0, 0));
                 return MyMaze;
             }

         }
         makeGoalPosition();
            return MyMaze;
        }
    /**
     * find the position that "found" the given position
     * @param p the given position3D
     * @return the "father" of the given position
     */
        private Position3D getMyFather(Position3D p){
            List<Position3D> neighbours , potentialsFather;
            potentialsFather = new ArrayList<Position3D>();
            neighbours = myNeighbours(p);
            for (int i = 0; i < neighbours.size(); i++) {
                if (MyMaze.getCellValue(neighbours.get(i).getDepthIndex(), neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 0)
                    potentialsFather.add(cells[neighbours.get(i).getDepthIndex()][neighbours.get(i).getRowIndex()][neighbours.get(i).getColumnIndex()]);
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
        private boolean connect(Position3D p1, Position3D p2){
            if (p1==null ||p2==null)
                return false;
            else{
                if (p1.getDepthIndex()==p2.getDepthIndex()) {
                    if (p1.getRowIndex() == p2.getRowIndex()) {
                        if (p1.getColumnIndex() < p2.getColumnIndex())
                            MyMaze.setMazeArr3D(p1.getDepthIndex(), p1.getRowIndex(), p1.getColumnIndex() + 1, 0);
                        if (p1.getColumnIndex() > p2.getColumnIndex())
                            MyMaze.setMazeArr3D(p1.getDepthIndex(), p1.getRowIndex(), p1.getColumnIndex() - 1, 0);
                    }
                    else if (p1.getColumnIndex()== p2.getColumnIndex()){
                        if (p1.getRowIndex() < p2.getRowIndex())
                            MyMaze.setMazeArr3D(p1.getDepthIndex(), p1.getRowIndex()+1, p1.getColumnIndex() , 0);
                        if (p1.getRowIndex() > p2.getRowIndex())
                            MyMaze.setMazeArr3D(p1.getDepthIndex(), p1.getRowIndex()-1, p1.getColumnIndex() , 0);
                    }
                }
                if (p1.getDepthIndex()!=p2.getDepthIndex()) {
                    if (p1.getColumnIndex() == p2.getColumnIndex() && p1.getRowIndex() == p2.getRowIndex()) {
                        if (p1.getDepthIndex() < p2.getDepthIndex()) {
                            MyMaze.setMazeArr3D(p1.getDepthIndex() + 1, p1.getRowIndex(), p1.getColumnIndex(), 0);
                        }
                        if (p1.getDepthIndex() > p2.getDepthIndex()) {
                            MyMaze.setMazeArr3D(p1.getDepthIndex() - 1, p1.getRowIndex(), p1.getColumnIndex(), 0);
                        }
                    }
                }
                return true;
            }
        }
        /**
         * returns array list of 2-4 neighbours of a position
         *
         * @param p - position from user
         */

        private List<Position3D> myNeighbours(Position3D p) {
            if (p == null)
                return null;
            List<Position3D> neighbours;
            neighbours = new ArrayList<Position3D>();
            //check if can go up down left or right if so, add to list
            if (isLegal(p.getDepthIndex(), p.getRowIndex() - 2, p.getColumnIndex()))
                neighbours.add(cells[p.getDepthIndex()][p.getRowIndex() - 2][p.getColumnIndex()]);
            if (isLegal(p.getDepthIndex(), p.getRowIndex() + 2, p.getColumnIndex()))
                neighbours.add(cells[p.getDepthIndex()][p.getRowIndex() + 2][p.getColumnIndex()]);
            if (isLegal(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex() - 2))
                neighbours.add(cells[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex() - 2]);
            if (isLegal(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex() + 2))
                neighbours.add(cells[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex() + 2]);
            if (isLegal(p.getDepthIndex() - 2, p.getRowIndex(), p.getColumnIndex()))
                neighbours.add(cells[p.getDepthIndex() - 2][p.getRowIndex()][p.getColumnIndex()]);
            if (isLegal(p.getDepthIndex() + 2, p.getRowIndex(), p.getColumnIndex()))
                neighbours.add(cells[p.getDepthIndex() + 2][p.getRowIndex()][p.getColumnIndex()]);
            return neighbours; //return list
        }

        /**
         * add to candidates<position> list all position's neighbours who might become a path.
         *
         * @param p - position from user
         */

        private void addCandidates(Position3D p) {
            if (p != null) {
                List<Position3D> neighbours;
                neighbours = myNeighbours(p);
                for (int i = 0; i < neighbours.size(); i++) {
                    if (MyMaze.getCellValue(neighbours.get(i).getDepthIndex(), neighbours.get(i).getRowIndex(), neighbours.get(i).getColumnIndex()) == 1 && isChangeAble(neighbours.get(i)))
                        candidates.add(neighbours.get(i)); //only legal neighbours and that have 1
                }
            }
        }

        /**
         * changeable cell (from 1 to 0) is a cell that has maximum of one 0 neighbour,
         *
         * @param p - position from user
         */

        private void addToPath(Position3D p) {
            if (p != null && isChangeAble(p))
                MyMaze.setMazeArr3D(p.getDepthIndex(),p.getRowIndex(), p.getColumnIndex(), 0);
        }

        /**
         * checks if currently position can be changed to 0, if so, then do it
         *
         * @param p - position from user
         */

        private boolean isChangeAble(Position3D p) {
            if (p == null)
                return false;
            if (MyMaze.getCellValue(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex()) == 0)
                return false;
            return true;
        }

        /**
         * checks if the position is on maze's bounds
         *
         * @param column - column of maze
         * @param row    - row of maze
         */

        private boolean isLegal(int depth, int row, int column) {
            if (row < 0 || row >= MyMaze.getRow()) // check if out of bound
                return false;
            if (column < 0 || column >= MyMaze.getCol())
                return false;
            if (depth < 0 || depth >= MyMaze.getDepth())
                return false;
            return true;
        }

        /**
         * returns a random position from candidates list.
         *
         * @return - random position
         */
        private Position3D getRandomPos() {
            Random random = new Random();
            int index = random.nextInt(candidates.size()); // get a random candidate.
            return candidates.get(index);
        }

        /**
         * set a random goal position
         */

        private void makeGoalPosition() {
            boolean found = false;
            int rows = MyMaze.getRow();
            int columns = MyMaze.getCol();
            int depths = MyMaze.getDepth();
            while (!found) {
                Position3D p= MyMaze.pointsOnFrame(rows, columns, depths);
                if (MyMaze.getCellValue(p.getDepthIndex(), p.getRowIndex(), p.getColumnIndex()) == 0
                        &&
                        (p.getDepthIndex() != MyMaze.getStartPosition().getDepthIndex() ||
                                p.getRowIndex() != MyMaze.getStartPosition().getRowIndex() || p.getColumnIndex() != MyMaze.getStartPosition().getColumnIndex())) {//legal goal position in last row.) {//legal goal position in last row.
                    MyMaze.setGoal(p);
                    found = true;
                }
            }
        }
    }

