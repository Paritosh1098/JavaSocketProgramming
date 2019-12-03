import com.sun.security.ntlm.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Server class accepts the ports and initiate a thread which will read the message queue and pass the message to Queue
 */
public class Server {

    private ServerSocket serverSocket;

    public static volatile Map<String, ClientConnection> socketMap = new HashMap<String, ClientConnection>();

    public static Queue<String> queueOfMessages = new ArrayBlockingQueue<String>(99999);

    public void start(int port) throws IOException {

        serverSocket = new ServerSocket(port);

        //initiate the data transmitter thread
        Thread dataTransmitter = new Thread(new DataTransmitter(socketMap,queueOfMessages));
        dataTransmitter.start();

        //loop
        while(true) {

            Socket newClientSocket = serverSocket.accept();

            ConnectionStarter starter = new ConnectionStarter(newClientSocket);
            Thread starterThread = new Thread(starter);
            starterThread.start();

        }

    }


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(4444);
    }

    public static String getConnectedUsers(Set<String> connectedUsers) {
        String list = "";

        if(connectedUsers.size() == 0)
            return "dummy";
        else {
            Iterator<String> i = connectedUsers.iterator();
            while (i.hasNext()) {
                list = list.concat(i.next() + ",");
            }

            return list.substring(0, list.length() - 1);

        }
    }

    public void stop() {
        try {
            serverSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
