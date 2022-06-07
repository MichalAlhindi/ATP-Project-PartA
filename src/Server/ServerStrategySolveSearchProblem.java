package Server;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Properties;

public class ServerStrategySolveSearchProblem  implements IServerStrategy{
    static int solutionNumber = 1;
    String tempDirectoryPath;
    Hashtable<String,String> hashtableSolutions;      // Hashtable in order to store solutions of mazes
    String hashPath = System.getProperty("java.io.tmpdir") + "hashtableSolutions.ser";

    /**
     * constructor
     */
    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");
        File hashFile = new File(this.hashPath);
        try {
            if (hashFile.exists()){
                FileInputStream InFile = new FileInputStream(this.hashPath);
                ObjectInputStream from = new ObjectInputStream(InFile);
                this.hashtableSolutions = (Hashtable<String,String>) from.readObject();
                from.close();
                InFile.close();
            }
            else{
                this.hashtableSolutions = new Hashtable<String,String>();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get a maze from the client, check if the solution is saved, if not, solve the maze and save the solution
     * and send it to the client
     * @param inFromClient the input from the client - a maze
     * @param outToClient the output for the client - the solution for the maze
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] compressedMaze = mazeToCompress(maze);
            String solutionPath = getSolutionFromHash(compressedMaze);
            if(solutionPath == null) {
                SearchableMaze searchableMaze = new SearchableMaze((Maze) maze);
                ASearchingAlgorithm searchingAlgorithm = findSearchAlgorithm();
                solution = searchingAlgorithm.solve(searchableMaze);
                saveSolution(compressedMaze,solution);
            }
            else{
                solution = getSolutionByPath(solutionPath);
            }
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    /**
     * finds the relevant maze generator
     * @return the relevant maze generator
     */
    private ASearchingAlgorithm findSearchAlgorithm() {
        ASearchingAlgorithm searchingAlgorithm =  null;
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input); // load a properties file
            String mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm"); // get the property value and print it out
            if(mazeSearchingAlgorithm.compareTo("BestFirstSearch") == 0){
                searchingAlgorithm = new BestFirstSearch();
            }
            else if (mazeSearchingAlgorithm.compareTo("BreadthFirstSearch")== 0){
                searchingAlgorithm = new BreadthFirstSearch();
            }
            else if (mazeSearchingAlgorithm.compareTo("DepthFirstSearch") == 0) {
                searchingAlgorithm = new DepthFirstSearch();
            }
            else { return null;}

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return searchingAlgorithm;
    }

    /**
     * return the solution that is in the path input
     * @param solutionPath the path of the file of the solution
     * @return the solution that is in the path
     * @throws IOException .
     * @throws ClassNotFoundException .
     */
    private Solution getSolutionByPath(String solutionPath) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(solutionPath));
        Solution solution =(Solution) objectInputStream.readObject();
        return  solution;
    }

    /**
     * save the solution and the maze in the hash table with a unique name
     * @param compressedMaze the maze
     * @param solution the solution
     * @throws IOException .
     */
    private void saveSolution(byte[] compressedMaze, Solution solution) throws IOException {
        String fileName = String.valueOf(getSolutionNumber());
        String finalPath = getTempDirectoryPath() + fileName + "_" + String.valueOf(System.currentTimeMillis())+".Solution";
        FileOutputStream fileOut = new FileOutputStream(finalPath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(solution);
        out.close();
        fileOut.close();
        this.hashtableSolutions.put(ourToString(compressedMaze),finalPath);
        FileOutputStream fileHash =  new FileOutputStream(this.hashPath);
        ObjectOutputStream outHash = new ObjectOutputStream(fileHash);
        outHash.writeObject(this.hashtableSolutions);
        solutionNumber++;
        outHash.close();
    }

    /**
     *
     * @return the curr solution number
     */
    public static int getSolutionNumber() {
        return solutionNumber;
    }

    /**
     *
     * @return the directory path
     */
    public String getTempDirectoryPath() {
        return tempDirectoryPath;
    }

    private String getSolutionFromHash(byte[] compressedMaze) {
        if (this.hashtableSolutions.containsKey(ourToString(compressedMaze))) {
            return this.hashtableSolutions.get(ourToString(compressedMaze));
        }
        return null;
    }

    /**
     * convert the maze to string
     * @param compressedMaze byte array of a compressed maze
     * @return the maze as a string
     */
    private String ourToString(byte[] compressedMaze) {
        String s = new String(compressedMaze, StandardCharsets.UTF_8);
        return s;
    }

    /**
     * compress the maze
     * @param maze the maze to compress
     * @return
     */
    private byte[] mazeToCompress(Maze maze) {
        ByteArrayOutputStream b = new ByteArrayOutputStream(Math.round((maze.toByteArray().length)));
        try {
            MyCompressorOutputStream out = new MyCompressorOutputStream(b);
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] compressedMaze = b.toByteArray();
        return compressedMaze;
    }


}
