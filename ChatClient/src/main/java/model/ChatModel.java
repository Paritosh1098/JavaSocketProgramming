package model;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {

    List<String> recievedMessages = new ArrayList<String>();
    List<String> sentMessages = new ArrayList<String>();

    public void sent(String message) {
        sentMessages.add(message);
    }

    public void recieved(String message) {
        recievedMessages.add(message);
    }
}
