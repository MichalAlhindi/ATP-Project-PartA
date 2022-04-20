package algorithms.mazeGenerators;

public  abstract class AMazeGenerator implements IMazeGenerator{
    public long measureAlgorithmTimeMillis(int rows, int columns){

        long curr=System.currentTimeMillis();
        generate(rows, columns);
        long after= System.currentTimeMillis();
        long time= after-curr;
        return time;
    }

}
