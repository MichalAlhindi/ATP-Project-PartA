package algorithms.search;


import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> solution;

    public Solution() {
        solution = new ArrayList<AState>();
    }

    public void addState(AState newState) {
        if (newState != null)
            solution.add(newState);
    }

    public ArrayList<AState> getSolutionPath() {
        return solution;
    }
}
