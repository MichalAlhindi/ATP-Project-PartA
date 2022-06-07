package Client;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Serializable {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    /**
     *
     * @param serverIP the ip of the server
     * @param serverPort the port of the server
     * @param strategy the algorithm to use
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**
     * connect to a server
     */
    public void communicateWithServer(){
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
