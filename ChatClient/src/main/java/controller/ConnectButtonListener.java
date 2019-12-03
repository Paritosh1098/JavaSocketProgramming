package controller;

import gui.HomeScreen;
import utility.Configs;
import utility.Constants;
import model.ClientModel;
import model.ClientState;
import model.ConnectedUserM;
import utility.GuiUpdateUtility;
import utility.HomeScreenUpdateUtility;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import static utility.Configs.*;

/**
 * Connect the client to server and save the socket
 */
public class ConnectButtonListener implements ActionListener {


    //Home Text fields
    JTextField serverAddress_ta; JTextField clientName_ta;

    /**
     * Constructor for the Controller
     * @param serverAddress_ta
     * @param clientName_ta
     */
    public ConnectButtonListener(JTextField serverAddress_ta, JTextField clientName_ta, JFrame frame) {
        this.serverAddress_ta = serverAddress_ta;
        this.clientName_ta = clientName_ta;
    }

    public void actionPerformed(ActionEvent e) {

        String serverAddress = serverAddress_ta.getText().split(":")[0];
        int port = Integer.parseInt(serverAddress_ta.getText().split(":")[1]);
        String clientName = clientName_ta.getText();
        Socket clientSocket = null;
        model = new ClientModel(clientName);
        guiUpdateUtility = new GuiUpdateUtility();
        homeScreen = new HomeScreen();
        try {

            //new socket
            clientSocket = new Socket(serverAddress, port);
            Configs.socket = clientSocket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //send your name and check if accepted
            out.println("Clientname:" + clientName);
            String serverSaid = in.readLine();

            System.out.println(serverSaid);

            if(serverSaid.equals("accepted")){

                //accepted, need to update the model and gui
                model.state = ClientState.ACCEPTED;
                model.name = clientName;
                model.connectionProperties.clientSocket = clientSocket;
                model.connectionProperties.port = port;
                model.connectionProperties.serverAddress = serverAddress;

                //update the list of connected users
                String listOfConectedUsers = in.readLine();

                for(String user: listOfConectedUsers.split(Constants.userSeparator)) {
                    if(user.equals("dummy"))
                        continue;
                    ConnectedUserM connectedUser = new ConnectedUserM(user);
                    model.connectedUsers.add(connectedUser);
                }

                //update the UI
                //hide the panel, set the new panel with the updated model


                HomeScreenUpdateUtility.updateHomeScreenWithModel(homeScreen, model);
                homeScreen.heading.setText("List Of Users (" + model.name + ")");
                homeFrame.getContentPane().removeAll();
                homeScreen.setVisible(true);
                homeFrame.getContentPane().add(homeScreen);
                homeScreen.revalidate();
                //initiate a thread which will run and listen to server and update the model


                ServerListener listener = new ServerListener();
                Thread thread = new Thread(listener);
                thread.start();
            }
            else {

                //print the error message on the UI (update the model and ui)
                model.state = ClientState.REJECTED;
            }


        }
        catch (ConnectException cex) {
            cex.printStackTrace();
        }
        catch (Exception e1) {
            e1.printStackTrace();
            model.state = ClientState.REJECTED;
        }

        if(model.state == ClientState.REJECTED) {
            //do nothing
        }

    }


}
