package model;

public class ConnectedUserM {

    public enum ChatStatus{
        ACTIVE,
        NOTACTIVE;
    }

    //chat status
    public ChatStatus chatStatus;

    //current user name
    public String userName;

    //data for chat
    public ChatModel chat;


    public ConnectedUserM(String userName){
        this.userName = userName;
        chatStatus = ChatStatus.NOTACTIVE;
        chat = new ChatModel();
    }

    public boolean sentMessage(String message) {
        return false;
    }

    public boolean recievedMessage(String message) {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return userName.equals(((ConnectedUserM)obj).userName);
    }
}
