package utility;

import controller.SendButtonListener;
import gui.ChatUI;
import gui.HomeScreen;
import model.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import static utility.Configs.*;

public class GuiUpdateUtility {

    Map<String,ChatUI> mapOfFrames = Configs.mapOfFrames;

    /**
     * Update model
     * @param message
     */
    public synchronized void updateUIWithModel(ServerMessage message) {


        if(message.type == MessageType.CONNECT || message.type == MessageType.DISCONNECT) {
            //if message.type ==  CONNECT/DISCONNECT

            //update home screen
            updateHomeScreenWithModel(model);
        }
        else if(message.type == MessageType.CHAT) {
            //else if message.type == chat
            //update one of the frames and the model
            if(mapOfFrames.containsKey(message.endUserName)) {

                ChatUI chatBox = mapOfFrames.get(message.endUserName);
                chatBox.setVisible(true);
                chatBox.addChatText(message.endUserName+ ":" + message.message);

            } else {
                //create a new frame
                ChatUI chatBox = new ChatUI(message.endUserName);
                SendButtonListener listener = new SendButtonListener(message.endUserName, chatBox.chatBox, chatBox);
                chatBox.addActionListener(listener);

                chatBox.addChatText(message.fullMessage);
                mapOfFrames.put(message.endUserName, chatBox);
            }

        }
    }

    /**
     * update home screen
     * @param model
     */
    public static synchronized void updateHomeScreenWithModel(ClientModel model) {

        if(model.state == ClientState.ACCEPTED) {

            //List Of users
            HomeScreen homeScreen = Configs.homeScreen;
            homeScreen.listOfUsers.removeAll();

            for (ConnectedUserM userM : model.connectedUsers) {
                homeScreen.addUser(userM.userName);
            }

            Configs.homeScreen.revalidate();
        }
        // rejected
        else if(model.state == ClientState.REJECTED) {

            Configs.homeFrame.add(new JLabel("Please try again with different name"));
            Configs.homeFrame.revalidate();
        }
        else {

        }


    }
}
