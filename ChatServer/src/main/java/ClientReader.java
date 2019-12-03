import java.io.IOException;
import java.util.Map;
import java.util.Queue;

//Read the sockets and pass the message to the queues
public class ClientReader implements Runnable {



    ClientConnection connection;

    /**
     * The Constructor
     * @param connection
     */
    public ClientReader(ClientConnection connection) {
        this.connection = connection;
    }

    /**
     * Read the sockets and put the message in the message queues
     */
    public void run() {

        while(true) {
            try {
                String message = connection.in.readLine();

                if(message != null)
                    System.out.println("Recieved from "+ connection.name + " " + message);
                else {
                    //client dead, remov it from the connection list
                    Server.socketMap.remove(connection.name);
                    System.out.println("Client DISCONNECTED: " + connection.name );
                    connection.in.close();
                    connection.out.close();

                    //initiate a disconnect request
                    Server.queueOfMessages.add("DISCONNECT:"+connection.name+":disconnectedclient");
                    break;
                }
                if(message != null) {
                    String[] arr = message.split(":");
                    String destination = arr[0];
                    String from = connection.name;
                    String data = arr[1];
                    Server.queueOfMessages.add(destination + ":CHAT:" + from + ":" + data);
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
