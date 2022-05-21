package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    private maze3DState startPoint;
    private maze3DState endPoint;
    private boolean[][][] visitedMap;
    public SearchableMaze3D(Maze3D m) {
        if (m != null) {
            maze = m;
            startPoint = new maze3DState(m.getStartPosition().getRowIndex(), m.getStartPosition().getColumnIndex());
            endPoint = new maze3DState(m.getGoalPosition().getRowIndex(), m.getGoalPosition().getColumnIndex());
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
        if (visit != null &&((maze3DState) visit).getDep() < maze.getDep() &&((maze3DState) visit).getRow() < maze.getRows() && ((MazeState) visit).getCol() < maze.getColumns()  && ((MazeState) visit).getRow() >= 0 && ((MazeState) visit).getCol() >= 0 &&((MazeState) visit).getDep() >= 0) {
            boolean x = visitedMap[((MazeState) visit).getDep()][((MazeState) visit).getRow()][((MazeState) visit).getCol()];
            return x;
        }
        else
            return false;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        return null;
    }

    @Override
    public void changeVisitTrue(AState visit) {
        if (visit != null && isLegal(((MazeState) visit).getRow(),((MazeState) visit).getCol())==true)
            visitedMap[((MazeState) visit).getDep()][((MazeState) visit).getRow()][((MazeState) visit).getCol()] = true;
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
