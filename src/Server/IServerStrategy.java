package Server;


import java.io.InputStream;
import java.io.OutputStream;

    public interface IServerStrategy {
        void ServerStrategy(InputStream in, OutputStream out);
    }

