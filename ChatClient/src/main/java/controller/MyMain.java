package controller;

import gui.ConnectScreenPanel;
import utility.Configs;

import javax.swing.*;
import java.io.IOException;

/**
 * myMain class
 */
public class MyMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        //Main frame
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        //initial screen
        ConnectScreenPanel connectScreenPanel = new ConnectScreenPanel();

        ConnectButtonListener connectButtonListener = new ConnectButtonListener(
                                                            connectScreenPanel.server_address_ta,
                                                            connectScreenPanel.client_name_ta, frame);

        connectScreenPanel.addListener(connectButtonListener);

        frame.getContentPane().add(connectScreenPanel);
        frame.setVisible(true);
        Configs.homeFrame = frame;
    }
}
