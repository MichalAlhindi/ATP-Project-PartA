package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public void setGoalState(AState x) {

    }

    @Override
    public boolean isVisited(AState visit) {
        return false;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        return null;
    }

    @Override
    public void changeVisitTrue(AState visit) {

    }

    @Override
    public void ResetVisit() {

    }
}
