package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{
    public BestFirstSearch() {
        super();
        this.name = "BestFirstSearch";
    }

    public int compareCosts(AState o1, AState o2) {
        if (o1.getCost() > o2.getCost())
            return 1;
        if (o2.getCost() > o1.getCost())
            return -1;
        else
            return 0;
    }

    @Override
    public Solution solve(ISearchable searchableM) {
        if (searchableM == null)
            return null;
        searchableM.ResetVisit(); // set all visit to false
        PriorityQueue<AState> StepsGo = new PriorityQueue<>(this::compareCosts); // new link list to keep steps
        StepsGo.add(searchableM.getStartState()); // add the 1st state to queue
        searchableM.changeVisitTrue(searchableM.getStartState());
        Solution Sol; //new solution
        Sol = FindSol(StepsGo, searchableM);
        return Sol;
    }

    private Solution FindSol(PriorityQueue<AState> StepsGo, ISearchable searchableM) {
        if (StepsGo == null)
            return null;
        Solution Sol; //new solution
        ArrayList<AState> NeighboursList;
        while (StepsGo.size() != 0) { //as long as there are steps to do
            AState temp = StepsGo.poll();
            // searchableM.changeVisitTrue(temp);
            numVisited++;//get a state from queue
            if (searchableM.getGoalState().equals(temp)) { //if state equal to end state
                searchableM.setGoalState(temp); //set end state
                Sol = getSolution(searchableM.getGoalState());
                searchableM.ResetVisit(); //reset visited fields
                return Sol; //return solution
            }
            NeighboursList = searchableM.getAllPossibleStates(temp);
            for (int i = 0; i < NeighboursList.size(); i++) {
                if (!searchableM.isVisited(NeighboursList.get(i))) { // new state found
                    //searchableM.changeVisitTrue(NeighboursList.get(i));
                    NeighboursList.get(i).setParent(temp) ; //updates its parent
                    //StepsGo.add(NeighboursList.get(i));


                    if (NeighboursList.get(i).equals(searchableM.getGoalState())) {
                        searchableM.setGoalState(NeighboursList.get(i)); //set end state
                        Sol = getSolution(searchableM.getGoalState()); //function to add the path inside solu Solution

                        searchableM.ResetVisit(); //reset visited fields
                        return Sol; //return solution
                    }
                    searchableM.changeVisitTrue(NeighboursList.get(i));
                    StepsGo.add(NeighboursList.get(i));
                }
            }
        }
        return null;
    }
}
