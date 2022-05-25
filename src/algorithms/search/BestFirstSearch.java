package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BestFirstSearch class is for finding solution using the BestFirstSearch algorithm
 */
public class BestFirstSearch extends ASearchingAlgorithm{
    /**
     * constructor for initialize the fields
     */
    public BestFirstSearch() {
        super();
        this.name = "BestFirstSearch";
    }

    /**
     * CostComparator class is for implement comparator between to states
     */
    class CostComparator implements Comparator<AState> {

        /**
         * Overriding compare() method of Comparator for ascending order of states
         * @param o1 state 1
         * @param o2 state 2
         * @return 1 if state 1 is bigger, -1 if it's smaller and 0 if they're equal
         */
        public int compare(AState o1, AState o2) {
            if (o1.getCost() > o2.getCost())
                return 1;
            if (o1.getCost() < o2.getCost())
                return -1;
            else
                return 0;
        }
    }


    /**
     * find a solution using best first search algorithm
     * @param searchableM the searchable problem to solve
     * @return the solution for the problem
     */
    @Override
    public Solution solve(ISearchable searchableM) {
        if (searchableM == null)
            return null;
        searchableM.ResetVisit(); // set all visit to false
        PriorityQueue<AState> StepsGo = new PriorityQueue<AState>(new CostComparator()); // new linked list to keep steps
        StepsGo.add(searchableM.getStartState()); // add the 1st state to queue
        searchableM.changeVisitTrue(searchableM.getStartState());
        Solution Sol; //new solution
        Sol = FindSol(StepsGo, searchableM);
        return Sol;
    }

    /**
     * find solution for the problem
     * @param StepsGo priority queue of states
     * @param searchableM the searchable problem
     * @return the solution
     */
    private Solution FindSol(PriorityQueue<AState> StepsGo, ISearchable searchableM) {
        if (StepsGo == null)
            return null;
        Solution Sol; //new solution
        ArrayList<AState> NeighboursList;
        while (StepsGo.size() != 0) { //as long as there are steps to do
            AState temp = StepsGo.poll();
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
                    NeighboursList.get(i).setParent(temp) ; //updates its parent

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
