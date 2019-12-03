package controller;


import gui.ConnectScreenPanel;
import model.ServerMessage;
import utility.Configs;
import utility.ModelUpdator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static utility.Configs.*;

/**
 * Listens to the server and updates the model
 */
public class ServerListener implements Runnable {



    ModelUpdator modelUpdator = new ModelUpdator();
    Map<String, Frame> chatBoxs = new HashMap<>();

    /**
     * The run method will listen to the server and update the model
     */
    public void run() {

        // The infinite loop
        while(true) {
            //read from the in and update the model

            try {
                String rMessage = in.readLine();

                if(rMessage == null) {
                    //server terminated, take back to Base screen, close the buffers
                    Configs.in.close();
                    Configs.out.close();
                    socket.close();
                    homeFrame.getContentPane().removeAll();
                    homeFrame.getContentPane().add(new ConnectScreenPanel());
                    homeFrame.revalidate();
                    homeFrame.repaint();

                    //close all chats
                    Iterator i = mapOfFrames.keySet().iterator();
                    while(i.hasNext()) {
                        JFrame frame = mapOfFrames.get(i.next());
                        frame.setVisible(false);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                    mapOfFrames = new HashMap<>();

                    //close the socket
                    socket.close();
                    homeFrame.getContentPane().removeAll();
                    ConnectScreenPanel connectScreenPanel = new ConnectScreenPanel();

                    ConnectButtonListener connectButtonListener = new ConnectButtonListener(
                            connectScreenPanel.server_address_ta,
                            connectScreenPanel.client_name_ta, homeFrame);

                    connectScreenPanel.addListener(connectButtonListener);

                    homeFrame.getContentPane().add(connectScreenPanel);
                    homeFrame.revalidate();
                    homeFrame.repaint();
                    homeFrame.setVisible(true);


                    break;
                }
                else {
                    System.out.println("Recieved message from Server:" + rMessage);
                    ServerMessage message = new ServerMessage(rMessage);


                    //update the model
                    ModelUpdator.updateModel(message);

                    //update the UI
                    guiUpdateUtility.updateUIWithModel(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
