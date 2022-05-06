package algorithms.search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected PriorityQueue<AState> openList;
    protected Map closedList;

    public BreadthFirstSearch(){
        openList = new PriorityQueue<AState>();
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
    public Solution solve(ISearchable searchable){
        AState currState = searchable.getStartState();
        insertOpenList(currState);
        Solution sol;
        while(!(openList.isEmpty())) {
            currState = popOpenList();
            insertClosedList(currState);
            if (currState.equals(searchable.getGoalState())) {
                //return the solution path////////////////////////////
                //sol += currState.getName();
                while(currState.getParent()!=null) {
                    currState = currState.getParent();
                    //sol += currState.getParent();
                }
                //return sol;
            }
            ArrayList<AState> successorsList = searchable.getAllSuccessors(currState);
            for (int i = 0; i < successorsList.size(); i++) {
                AState childState = successorsList.get(i);
                if (closedList.containsKey(childState.getName())) {
                    continue;
                }
                if (!(openList.contains(childState))) { // is the id needed????????????//
                    childState.setParent(currState);
                    insertOpenList(childState);
                }
            }
        }
        return null;
    }




}
