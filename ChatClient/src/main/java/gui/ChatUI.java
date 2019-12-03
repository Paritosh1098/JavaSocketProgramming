package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import static utility.Configs.*;

public class ChatUI extends JFrame {

    ActionListener sendButtonListener;

    public JLabel chatArea = new JLabel();


    JButton sendButton = new JButton("Send");
    public JTextField chatBox = new JTextField("Enter text here...");

    JLabel heading = new JLabel("");
    String chat = "";

    /**
     * ChatUI
     */
    public ChatUI(String endUser){
        heading.setText("Chat b/w "+ model.name + "-" + endUser);
        chatArea.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        chatArea.setAutoscrolls(true);

        panel.add(heading);
        panel.add(chatArea);
        panel.add(chatBox);
        panel.add(sendButton);
        this.add(panel);
        panel.setVisible(true);
        this.setVisible(true);
        this.setSize(300,300);
    }

    /**
     * set action listener
     * @param listener
     */
    public void addActionListener(ActionListener listener) {
        sendButton.addActionListener(listener);

    }

    /**
     * Add text
     * @param message
     */
    public void addChatText(String message){
        chat = chat.concat("<br>" + message);
       chatArea.setText("<html>" + chat + "</html>" );
       chatArea.repaint();
       chatArea.revalidate();
    }
}
