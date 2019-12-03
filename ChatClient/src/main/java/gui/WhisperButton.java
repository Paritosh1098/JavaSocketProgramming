package gui;

import controller.WhisperButtonListener;

import javax.swing.*;

public class WhisperButton extends JButton {

    String endUserName;
    public WhisperButton(String endUserName) {
        this.endUserName = endUserName;
        this.setText("Whisper");
    }
}
