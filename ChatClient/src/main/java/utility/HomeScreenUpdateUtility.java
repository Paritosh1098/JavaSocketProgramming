package utility;

import gui.HomeScreen;
import model.ClientModel;
import model.ClientState;
import model.ConnectedUserM;

import javax.swing.*;
import java.awt.*;

public class HomeScreenUpdateUtility {

    public static void updateHomeScreenWithModel(Container container, ClientModel model) {

        if(model.state == ClientState.ACCEPTED) {
            HomeScreen homeScreen = (HomeScreen) container;
            homeScreen.listOfUsers.removeAll();

            for (ConnectedUserM userM : model.connectedUsers) {
                homeScreen.addUser(userM.userName);
            }
        }
        // rejected
        else if(model.state == ClientState.REJECTED) {

            container.add(new JLabel("Please try again with different name"));
            container.revalidate();
            container.repaint();
        }
        else {

        }

        container.revalidate();
    }
}
