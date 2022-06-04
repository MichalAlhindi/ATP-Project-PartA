package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *class to represent solution fo the maze
 */
public class Solution implements Serializable {

    private ArrayList<AState> solution;

    /**
     *constructor of the solution - represents as array of states
     */
    public Solution() {
        solution = new ArrayList<AState>();
    }

    /**
     * @param newState a new atate to add
     *add state to the solution
     */
    public void addState(AState newState) {
        if (newState != null)
            solution.add(newState);
    }

    /**
     *@return the solution path of the maze
     */
    public ArrayList<AState> getSolutionPath() {
        return solution;
    }
}
