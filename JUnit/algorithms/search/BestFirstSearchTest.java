package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BestFirstSearchTest {

    @Test
    public void testName() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        assertEquals("BestFirstSearch", bestFirstSearch.getName());
    }

    @Test
    public void solveNull() throws Exception {

        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        assertEquals(null, bestFirstSearch.solve(null));

    }

    @Test
    public void solveSmall() throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze1 = mg.generate(1, 3);
        Maze maze2 = mg.generate(1, -2);
        SearchableMaze searchableMaze1 = new SearchableMaze(maze1);
        assertEquals(2, maze1.getRows());
        assertEquals(3, maze1.getColumns());
        assertEquals(2, maze2.getRows());
        assertEquals(2, maze2.getColumns());
    }



    BestFirstSearch bestFirstSearch = new BestFirstSearch();
    Maze temp = new Maze(50, 50);
    ISearchable MazeSearch = new SearchableMaze(temp);
    Solution tempSolve = new Solution();

    @Test
    void solve() {
        assertNotEquals(bestFirstSearch.solve(MazeSearch), null);
    }
}



