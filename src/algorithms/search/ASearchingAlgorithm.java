package algorithms.search;

import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected ISearchable searchable;
    public String name;
    public int numVisited;
    public String getName(){
        return name;
    }
    public int getNumberOfNodesEvaluated(){
        return numVisited;
    }






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


