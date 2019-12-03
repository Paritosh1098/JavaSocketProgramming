package gui;

import controller.WhisperButtonListener;
import utility.GuiUpdateUtility;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel{


    /**
     * List of Users screen
     */
    public JLabel heading = new JLabel("List of Users");


    public JPanel listOfUsers = new JPanel();

    /**
     * Contructor
     */
    public HomeScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAutoscrolls(true);
        this.add(heading);
        this.add(listOfUsers);
        listOfUsers.setVisible(true);
        listOfUsers.setLayout(new BoxLayout(listOfUsers, BoxLayout.Y_AXIS));
    }

    public void addUser(String userName) {
        JPanel panel = new JPanel();
        panel.setName(userName);
        JLabel label = new JLabel();

        label.setText(userName);
        WhisperButton button = new WhisperButton(userName);
        button.addActionListener(new WhisperButtonListener(userName));
        panel.add(label);
        panel.add(button);
        panel.setVisible(true);
        listOfUsers.add(panel);

    }

    public void removeUser(String userName) {

        Component toBeRemoved = null;

        for(Component component: listOfUsers.getComponents()) {

            if(component.getName().equals(userName)) {
                toBeRemoved = component;
                break;
            }
        }

        if(toBeRemoved != null) {
            listOfUsers.remove(toBeRemoved);
        }
    }
}
