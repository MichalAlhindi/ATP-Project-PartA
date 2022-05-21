package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    private maze3DState startPoint;
    private maze3DState endPoint;
    private boolean[][][] visitedMap;
    public SearchableMaze3D(Maze3D m) {
        if (m != null) {
            maze = m;
            startPoint = new maze3DState(m.getStartPosition().getDepthIndex(), m.getStartPosition().getRowIndex(), m.getStartPosition().getColumnIndex());
            endPoint = new maze3DState(m.getGoalPosition().getDepthIndex(), m.getGoalPosition().getRowIndex(), m.getGoalPosition().getColumnIndex());
            visitedMap = new boolean[m.getDepth()][m.getRow()][m.getCol()];
        }
    }
    @Override
    public AState getStartState() {
        return startPoint;
    }

    @Override
    public AState getGoalState() {
        return endPoint;
    }

    /**
     *
     * @param x
     */
    @Override
    public void setGoalState(AState x) {
        if (x != null && x instanceof maze3DState) //make sure aState is a maze3DState
            endPoint = (maze3DState) x;
    }

    @Override
    public boolean isVisited(AState visit) {
        if (visit != null &&((maze3DState) visit).getDepth() < maze.getDepth() &&((maze3DState) visit).getRow() < maze.getRow() &&
                ((maze3DState) visit).getCol() < maze.getCol()  && ((maze3DState) visit).getRow() >= 0 &&
                ((maze3DState) visit).getCol() >= 0 &&((maze3DState) visit).getDepth() >= 0) {
                    boolean x = visitedMap[((maze3DState) visit).getDepth()][((maze3DState) visit).getRow()]
                            [((maze3DState) visit).getCol()];
                    return x;
        }
        else
            return false;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> temp = new ArrayList<AState>(); //array to keep possible states
        ArrayList<AState> tempD; //array to keep Diagonal states
        maze3DState mazestate;
        if (s != null && s instanceof maze3DState) //make sure State is a MazeState
        {
            maze3DState tempM;
            mazestate = ((maze3DState) s);
            int x = mazestate.getRow();
            int y = mazestate.getCol();
            int z = mazestate.getDepth();
            maze3DState TempAdd; //temp mazestate
            TempAdd = CheckLegal(z, x - 1, y); //check if legal to add
            if (TempAdd != null)
                temp.add(TempAdd); //add to arraylist
            TempAdd = CheckLegal(z, x, y + 1);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(z, x + 1, y);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(z, x, y - 1);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(z + 1, x, y);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(z - 1 , x, y);
            if (TempAdd != null)
                temp.add(TempAdd);
        }
        return temp;
    }

    private maze3DState CheckLegal(int z, int x, int y) {
        maze3DState tempM;
        if (isLegal(z, x, y)) {
            if (maze.getCellValue(z, x, y) == 0) {
                tempM = new maze3DState(z, x, y);
                return tempM;
            }
        }
        return null;
    }

    @Override
    public void changeVisitTrue(AState visit) {
        if (visit != null && isLegal(((maze3DState) visit).getDepth(), ((maze3DState) visit).getRow(),((maze3DState) visit).getCol())==true)
            visitedMap[((maze3DState) visit).getDepth()][((maze3DState) visit).getRow()][((maze3DState) visit).getCol()] = true;
    }

    private boolean isLegal(int depth, int row, int column) {
        if (depth < 0 || row < 0 || column < 0 ||  depth >= maze.getDepth() || row >= maze.getRow() || column >= maze.getCol())
            return false;
        if (visitedMap[depth][row][column] == false)
            return true;
        return false;
    }

    @Override
    public void ResetVisit() {
        for (int d = 0; d < maze.getDepth(); d++) {
            for (int i = 0; i < maze.getRow(); i++) {
                for (int j = 0; j < maze.getCol(); j++) {
                    visitedMap[d][i][j] = false;
                }
            }
        }
    }
}