package controller;

import gui.ChatUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static utility.Configs.*;


/**
 * Corresponds to one chat, will send the message to server on send button click
 */
public class SendButtonListener implements ActionListener {


    JTextField textArea;

    String endUserName;

    ChatUI chatUI;

    public SendButtonListener(String endUserName, JTextField textArea, ChatUI chatBox) {
        this.endUserName = endUserName;
        this.textArea = textArea;
        chatUI = chatBox;
    }

    //sendButton handler
    public void actionPerformed(ActionEvent e) {
        //read the textArea and send the message to out

        String message = textArea.getText();
        textArea.setText("");
        String fullMessage = endUserName + ":" + message;

        out.println(fullMessage);
        out.flush();
        System.out.println("Written to server:" + fullMessage);
        chatUI.addChatText("Me:" + message);
    }



}
