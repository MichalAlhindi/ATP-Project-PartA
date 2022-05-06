package algorithms.search;

public abstract class ASearchingAlgorithm {
    protected ISearchable searchable;
    public String name;
    public int numVisited;
    public String getName(){
        return name;
    }
    public int getNumberOfNodesEvaluated(){
        return numVisited;
    }

}
