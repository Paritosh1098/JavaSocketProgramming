package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ConnectScreenPanel extends JPanel {

    public JTextField server_address_ta = new JTextField("127.0.0.1:4444");
    public JTextField client_name_ta = new JTextField("Client name");
    public JButton connectButton = new JButton("Connect");

    public ConnectScreenPanel() {
        this.add(server_address_ta);
        this.add(client_name_ta);
        this.add(connectButton);
    }

    public void addListener(ActionListener listener) {
        connectButton.addActionListener(listener);
    }
}
