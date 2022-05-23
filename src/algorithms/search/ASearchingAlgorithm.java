package algorithms.search;

import java.util.Stack;

/**
 * ASearchingAlgorithm class to use a searching algorithm to look for solution for a problem
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected ISearchable searchable;
    protected String name;
    protected int numVisited;

    /**
     * constructor that initiallize the number of visited nodes
     */
    ASearchingAlgorithm(){
        numVisited = 0;
    }

    /**
     * @return the name of the searching algorithm
     */
    public String getName(){
        return name;
    }

    /**
     * @return the number of the visited nodes
     */
    public int getNumberOfNodesEvaluated() { return numVisited; }

    public Solution getSolution(AState state) {
        Solution Sol = new Solution();
        Stack<AState> FSol = new Stack<AState>();
        if (state != null) {
            while (state.getParent() != null) {
                FSol.push(state);
                state = state.getParent();
            }
            FSol.push(state);
        }
        while (FSol.size() != 0) {
            Sol.addState(FSol.pop());
        }
        return Sol;
    }
}


