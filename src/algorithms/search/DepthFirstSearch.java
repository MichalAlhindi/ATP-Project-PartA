package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    public DepthFirstSearch() {
        super();
        this.name = "DepthFirstSearch";
    }

    public Solution solve(ISearchable searchable){
        searchable.ResetVisit();
        Stack<AState> path= new Stack<AState>();
        path.add(searchable.getStartState());
        searchable.changeVisitTrue(searchable.getStartState());
        //numVisited++;
        ArrayList<AState> NeighboursList;
        Solution sol;
        while (path.size() != 0) { //as long as there are steps to do
            AState temp = path.pop();
            numVisited++;//get a state from queue
            if (searchable.getGoalState().equals(temp)) { //if state equal to end state
                searchable.setGoalState(temp); //set end state
                sol = getSolution(searchable.getGoalState());
                //searchable.ResetVisit(); //reset visited fields
                return sol; //return solution
            }
            NeighboursList = searchable.getAllPossibleStates(temp);
            for (int i = 0; i < NeighboursList.size(); i++) {
                if (!searchable.isVisited(NeighboursList.get(i))) { // new state found
                    NeighboursList.get(i).setParent(temp) ; //updates its parent
                    //numVisited++;
                    if (NeighboursList.get(i).equals(searchable.getGoalState())) {
                        searchable.setGoalState(NeighboursList.get(i)); //set end state
                        sol = getSolution(searchable.getGoalState()); //function to add the path inside solu Solution

                        searchable.ResetVisit(); //reset visited fields
                        return sol; //return solution
                    }
                    searchable.changeVisitTrue(NeighboursList.get(i));
                    path.push(NeighboursList.get(i));

                }
            }
        }
        return null;
    }
}