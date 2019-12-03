package model;

public class ServerMessage {

    public MessageType type;

    public String fullMessage;

    public String message;

    public String endUserName;

    public ServerMessage(String fullMessage) {
        this.fullMessage = fullMessage;
        String[] message_parts = fullMessage.split(":");
        String mType = message_parts[0];
        endUserName = message_parts[1];
        message = message_parts[2];

        // set the message type
        switch(mType) {

            case "CHAT": type = MessageType.CHAT;break;

            case "CONNECT": type = MessageType.CONNECT;break;

            case "DISCONNECT": type = MessageType.DISCONNECT;break;

            case "CONNECTION": type = MessageType.CONNECTION;break;

        }

        //set the message
        this.message = message;
    }

    @Override
    public String toString() {
        return this.fullMessage;
    }
}
