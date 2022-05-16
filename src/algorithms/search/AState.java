package algorithms.search;

public abstract class AState {
    private String name;
    private int rowIdx;
    private int colIdx;
    private double cost;
    private AState parent;

    public AState() {
        this.name = "(0,0)"; //default
        parent = null;
        this.cost = 0;
    }

    public AState(String n, int rI, int cI, double c) {
        name = n;
        rowIdx = rI;
        colIdx = cI;
        cost = c;
    }

    public String getName() {
        return name;
    }

    public double getCost() {return this.cost;}

    public void setParent(AState p) {
        this.parent = p;
    }

    public AState getParent() {
        return parent;
    }

    public void setCost(double costI) {
        this.cost = costI;
    }
}

