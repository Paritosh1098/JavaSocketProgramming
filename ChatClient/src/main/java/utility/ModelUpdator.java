package utility;

import model.ClientState;
import model.MessageType;
import model.ServerMessage;
import static utility.Configs.*;

public class ModelUpdator {

    public static synchronized void updateModel(ServerMessage serverMessage) {

        //connection accepted or rejected
        if(serverMessage.type == MessageType.CONNECTION) {

            if(serverMessage.message.equalsIgnoreCase("accepted"))
                model.state = ClientState.ACCEPTED;
            else
                model.state = ClientState.REJECTED;

        }

        //user added
        if(serverMessage.type == MessageType.CONNECT)
            model.addConnectedUser(serverMessage.endUserName);

        //user removed
        if(serverMessage.type == MessageType.DISCONNECT)
            model.removeConnectedUser(serverMessage.endUserName);

        //chat message
        if(serverMessage.type == MessageType.CHAT) {
            model.addChat(serverMessage);
        }




    }
}
