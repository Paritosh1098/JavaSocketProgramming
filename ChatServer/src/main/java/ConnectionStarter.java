import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class ConnectionStarter implements Runnable {


    Socket clientSocket;

    public ConnectionStarter(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String clientName = in.readLine().split(":")[1].trim();
            //accept or reject
            synchronized(Server.socketMap) {
                if (Server.socketMap.containsKey(clientName)) {
                    out.println("rejected");
                    clientSocket.close();
                } else {
                    out.println("accepted");
                    System.out.println("NEW CLIENT CONNECTED:" + clientName);

                    //send list of connected users
                    out.println(Server.getConnectedUsers(Server.socketMap.keySet()));

                    //initiate a CONNECT message for everyone who is connected
                    for(String user: Server.socketMap.keySet())
                        Server.queueOfMessages.add(user + ":CONNECT:" + clientName + ":dummyvalue");

                    //add it to the socketMap
                    ClientConnection connection = new ClientConnection(clientSocket, clientName, in, out);
                    Server.socketMap.put(clientName, connection);

                    //initate a socket reader which will publish the queue of messages to be transmitted
                    Thread publisher = new Thread(new ClientReader(connection));
                    publisher.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
