package algorithms.search;

/**
 * AState class is for handle some state
 */
public abstract class AState {
    private String name;
    private int rowIdx;
    private int colIdx;
    private double cost;
    private AState parent;

    /**
     * constructor to initialize the state fields
     */
    public AState() {
        this.name = "(0,0)"; //default
        parent = null;
        this.cost = 0;
    }

    /**
     *  constructor with parameters
     * @param n the name of the state
     * @param rI the row index of the state
     * @param cI the column index of the state
     * @param c the cost of the state
     */
    public AState(String n, int rI, int cI, double c) {
        name = n;
        rowIdx = rI;
        colIdx = cI;
        cost = c;
    }

    /**
     * @return the state's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cost of the state
     */
    public double getCost() {return this.cost;}

    /**
     * set the parent of the state
     * @param p the parent
     */
    public void setParent(AState p) {
        this.parent = p;
    }

    /**
     * @return the parent of the state
     */
    public AState getParent() {
        return parent;
    }

    /**
     * set the cost of the state
     * @param costI the cost
     */
    public void setCost(double costI) {
        this.cost = costI;
    }
}

