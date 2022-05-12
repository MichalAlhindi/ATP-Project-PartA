package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    void setGoalState(AState x);
    boolean isVisited(AState visit);
    ArrayList<AState> getAllPossibleStates(AState s);
    void changeVisitTrue(AState visit);
    void ResetVisit();

}
