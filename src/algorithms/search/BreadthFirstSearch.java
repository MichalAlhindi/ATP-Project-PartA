package algorithms.search;
import java.util.*;

/**
 * BreadthFirstSearch class is for finding solution using the BreadthFirstSearch algorithm
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> openList;

    /**
     * constructor for initialize the fields
     */
    public BreadthFirstSearch(){
        super();
        this.name = "BreadthFirstSearch";
        openList =  new LinkedList<AState>();
    }


    /**
     * insert to the queue
     * @param s a state to insert
     */
    protected void insertOpenList(AState s){
        openList.add(s);
    }


    /**
     * find a solution using breadth first search algorithm
     * @param searchable the searchable problem
     * @return the solution for the problem
     */
    public Solution solve(ISearchable searchable) {

        searchable.ResetVisit(); // set all visit to false
        AState currState = searchable.getStartState();
        insertOpenList(currState);
        searchable.changeVisitTrue(currState);
        Solution sol;
        while (!(openList.isEmpty())) {
            currState = openList.poll();
            numVisited++;
            if (currState.equals(searchable.getGoalState())) {
                searchable.setGoalState(currState);
                sol = getSolution(currState);
                searchable.ResetVisit(); //reset visited fields
                return sol; //return solution
            }
            ArrayList<AState> successorsList = searchable.getAllPossibleStates(currState);
            for (int i = 0; i < successorsList.size(); i++) {
                if (!searchable.isVisited(successorsList.get(i))) {
                    successorsList.get(i).setParent(currState);//updates its parent
                    if (successorsList.get(i).equals(searchable.getGoalState())) {
                        searchable.setGoalState(successorsList.get(i)); //set end state
                        sol = getSolution(successorsList.get(i));
                        searchable.ResetVisit();
                        return sol;
                    }
                    searchable.changeVisitTrue(successorsList.get(i));
                    insertOpenList(successorsList.get(i));

                }

            }
        }
        return null;
    }
}

