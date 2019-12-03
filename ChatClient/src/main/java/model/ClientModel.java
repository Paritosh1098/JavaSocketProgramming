package model;

import java.util.ArrayList;
import java.util.List;

public class ClientModel {

    //Name of the client
    public String name = " " ;

    //State of the client
    public ClientState state ;

    //List of connected users
    public List<ConnectedUserM> connectedUsers = new ArrayList<>();


    //Server connection properties
    public ServerConnectionModel connectionProperties = new ServerConnectionModel();

    /**
     * public constructor for the ClientModel
     */
    public ClientModel(String name) {

        this.name = name;
        state = ClientState.DISCONNECTED;
        connectedUsers = new ArrayList<ConnectedUserM>();

    }

    /**
     * Add a connected user
     * @param userName
     * @return
     */
    public boolean addConnectedUser(String userName) {
       ConnectedUserM userM = new ConnectedUserM(userName);

       if(connectedUsers.contains(userM))
            return false;
       else {
           connectedUsers.add(userM);
           return true;
       }
    }

    /**
     * Remove a connected user
     */
    public boolean removeConnectedUser(String userName) {
        ConnectedUserM userM = new ConnectedUserM(userName);

        if(connectedUsers.contains(userM)) {
            connectedUsers.remove(userM);
            return true;
        }else {
            return false;
        }
    }

    public boolean addChat(ServerMessage message) {
        return false;
    }

}
