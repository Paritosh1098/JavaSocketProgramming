package controller;

import model.ServerMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static utility.Configs.guiUpdateUtility;

public class WhisperButtonListener implements ActionListener {

    String endUserName;

    /**
     * The constructor
     * @param endUserName
     */
    public WhisperButtonListener(String endUserName) {
        this.endUserName = endUserName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServerMessage message = new ServerMessage("CHAT" + ":" + endUserName + ":" + " ");
        guiUpdateUtility.updateUIWithModel(message);
    }
}
