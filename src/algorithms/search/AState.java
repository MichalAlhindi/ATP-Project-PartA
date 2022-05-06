package algorithms.search;

public abstract class AState {
    private String name;
    private int rowIdx;
    private int colIdx;
    private double cost;
    private AState parent;

    public AState(String n, int rI, int cI, double c) {
        name = n;
        rowIdx = rI;
        colIdx = cI;
        cost = c;
    }

    public String getName(){
        return name;
    }

    public void setParent(AState p) {
        this.parent = p;
    }

    public AState getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        AState other = (AState) o;
/*        if (this.cost == (other.cost)) return true;
        if(this.cost > other.cost) return true;
        if (this.cost < other.cost) return false;*/
        //if (other == null || getClass() != o.getClass()) return false;
        return Double.compare(other.cost, cost) == 0;
    }

    //compare function?

/*    @Override    needed?
    public int hashCode() {
        return Objects.hash(cost);
    }*/
}
