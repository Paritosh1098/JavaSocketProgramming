import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {

    Socket socket;
    String name;
    PrintWriter out;
    BufferedReader in;



    public ClientConnection(Socket clientSocket, String clientName, BufferedReader in, PrintWriter out) {
        this.socket = clientSocket;
        this.name = clientName;
        this.in = in;
        this.out = out;
    }

    /**
     * close the connection
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals( ((ClientConnection)obj).name);
    }
}
