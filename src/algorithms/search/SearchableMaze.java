package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements  ISearchable{
    private Maze maze;
    private MazeState startPoint;
    private MazeState endPoint;
    private boolean[][] visitedMap;


    public SearchableMaze(Maze m) {
        if (m != null) {
            maze = m;
            startPoint = new MazeState(m.getStartPosition().getRowIndex(), m.getStartPosition().getColumnIndex());
            endPoint = new MazeState(m.getGoalPosition().getRowIndex(), m.getGoalPosition().getColumnIndex());
            visitedMap = new boolean[m.getRows()][m.getColumns()];
        }
    }

    @Override
    public AState getStartState() {
        return startPoint;
    }

    @Override
    public AState getGoalState() {
        return endPoint;
    }

    @Override
    public void setGoalState(AState x) {
        if (x != null && x instanceof MazeState) //make sure aState is a MazeState
            endPoint = (MazeState) x;
    }

    private boolean isLegal(int row, int column) {
        if (row < 0 || column < 0 || row >= maze.getRows() || column >= maze.getColumns())
            return false;
        if (visitedMap[row][column] == false)
            return true;
        return false;
    }

    private ArrayList<AState> getAllDiagonal(int x, int y,double camefromcost) {
        ArrayList<AState> temp = new ArrayList<AState>();
        MazeState tempM;
        if (isLegal(x - 1, y - 1) && visitedMap[x - 1][y - 1] == false && maze.getCellValue(x - 1, y - 1) == 0)
            if (visitedMap[x - 1][y] == false || visitedMap[x][y - 1] == false)
                if (maze.getCellValue(x - 1, y) == 0 || maze.getCellValue(x, y - 1) == 0) {
                    tempM = new MazeState(x - 1, y - 1);
                    tempM.setCost(camefromcost+15);
                    temp.add(tempM);
                }
        if (isLegal(x + 1, y + 1) && visitedMap[x + 1][y + 1] == false && maze.getCellValue(x + 1, y + 1) == 0)
            if (visitedMap[x + 1][y] == false || visitedMap[x][y + 1] == false)
                if (maze.getCellValue(x, y + 1) == 0 || maze.getCellValue(x + 1, y) == 0) {
                    tempM = new MazeState(x + 1, y + 1);
                    tempM.setCost(camefromcost+15);
                    temp.add(tempM);
                }
        if (isLegal(x + 1, y - 1) && visitedMap[x + 1][y - 1] == false && maze.getCellValue(x + 1, y - 1) == 0)
            if (visitedMap[x][y - 1] == false || visitedMap[x + 1][y] == false)
                if (maze.getCellValue(x, y - 1) == 0 || maze.getCellValue(x + 1, y) == 0) {
                    tempM = new MazeState(x + 1, y - 1);
                    tempM.setCost(camefromcost+15);
                    temp.add(tempM);
                }
        if (isLegal(x - 1, y + 1) && visitedMap[x - 1][y + 1] == false && maze.getCellValue(x - 1, y + 1) == 0)
        {
            if (isLegal(x-1,y)&&visitedMap[x - 1][y] == false&&maze.getCellValue(x - 1, y) == 0 || isLegal(x,y+1)&&visitedMap[x][y+1] == false&&maze.getCellValue(x, y + 1) == 0)
            {
                tempM = new MazeState(x - 1, y - 1);
                tempM.setCost(camefromcost+15);
                temp.add(tempM);
            }
        }
        return temp;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> temp = new ArrayList<AState>(); //array to keep possible states
        ArrayList<AState> tempD; //array to keep Diagonal states
        MazeState mazestate;
        double camefromcost=s.getCost();
        if (s != null && s instanceof MazeState) //make sure State is a MazeState
        {

            mazestate = ((MazeState) s);
            int x = mazestate.getRow();
            int y = mazestate.getCol();
            tempD = getAllDiagonal(x, y, camefromcost); //get all diagonal states
            for (int i = 0; i < tempD.size(); i++)
                temp.add(tempD.get(i)); //add them to array
            MazeState TempAdd; //temp mazestate
            TempAdd = CheckLegal(x - 1, y,camefromcost); //check if legal to add
            if (TempAdd != null)
                temp.add(TempAdd); //add to arraylist
            TempAdd = CheckLegal(x + 1, y,camefromcost);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(x, y - 1,camefromcost);
            if (TempAdd != null)
                temp.add(TempAdd);
            TempAdd = CheckLegal(x, y + 1,camefromcost);
            if (TempAdd != null)
                temp.add(TempAdd);
//            tempD = getAllDiagonal(x, y); //get all diagonal states
//            for (int i = 0; i < tempD.size(); i++)
//                temp.add(tempD.get(i)); //add them to array
        }
        return temp;
    }


    private MazeState CheckLegal(int x, int y,double camefromcost) {
        MazeState tempM;
        if (isLegal(x, y))
            if (maze.getCellValue(x, y) == 0) {
                tempM = new MazeState(x, y);
                tempM.setCost(camefromcost+10);
                return tempM;
            }
        return null;
    }


    @Override
    public boolean isVisited(AState visit) {
        if (visit != null && ((MazeState) visit).getRow() < maze.getRows() && ((MazeState) visit).getCol() < maze.getColumns()  && ((MazeState) visit).getRow() >= 0 && ((MazeState) visit).getCol() >= 0) {
            boolean x = visitedMap[((MazeState) visit).getRow()][((MazeState) visit).getCol()];
            return x;
        } else
            return false;
    }


    @Override
    public void changeVisitTrue(AState visit) {
        if (visit != null && isLegal(((MazeState) visit).getRow(),((MazeState) visit).getCol())==true)
            visitedMap[((MazeState) visit).getRow()][((MazeState) visit).getCol()] = true;
    }


    @Override
    public void ResetVisit() {
        for (int i = 0; i < maze.getRows(); i++)
            for (int j = 0; j < maze.getColumns(); j++)
                visitedMap[i][j] = false;
    }
}
