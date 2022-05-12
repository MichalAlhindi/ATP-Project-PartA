package algorithms.search;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> openList;
    protected Map closedList;

    public BreadthFirstSearch(){
        this.name = "BreadthFirstSearch";
        openList =  new LinkedList<AState>();
        closedList = new HashMap();
        numVisited = 0;
    }

    protected AState popOpenList(){
        numVisited++;
        return openList.poll();
    }

    protected void insertOpenList(AState s){
        openList.add(s);
    }

    protected void insertClosedList(AState s){
        closedList.put(s.getName(), s);
    }

    /* solution without consider the cost
    */
    public Solution solve(ISearchable searchable) {

        searchable.ResetVisit(); // set all visit to false

        AState currState = searchable.getStartState();
        insertOpenList(currState);
        searchable.changeVisitTrue(searchable.getStartState());
        //numVisited++;
        Solution sol;
        while (!(openList.isEmpty())) {

            currState = openList.poll();
            numVisited++;
            insertClosedList(currState);
            if (currState.equals(searchable.getGoalState())) {
                searchable.setGoalState(currState);
                sol = getSolution(currState);
                searchable.ResetVisit(); //reset visited fields
                return sol; //return solution

            }
            ArrayList<AState> successorsList = searchable.getAllPossibleStates(currState);
            for (int i = 0; i < successorsList.size(); i++) {
                if (!searchable.isVisited(successorsList.get(i))) {// new state found
                    //numVisited++;
                    searchable.changeVisitTrue(successorsList.get(i));
                    successorsList.get(i).setParent(currState); //updates its parent
                    openList.add(successorsList.get(i));
                }
                if (successorsList.get(i).equals(searchable.getGoalState())) {
                    successorsList.get(i).setParent(currState);
                    searchable.setGoalState(successorsList.get(i)); //set end state
                    sol = getSolution(successorsList.get(i));
                    searchable.ResetVisit();
                    return sol; //return solution
                }
            }
        }
        return null;
    }
}

