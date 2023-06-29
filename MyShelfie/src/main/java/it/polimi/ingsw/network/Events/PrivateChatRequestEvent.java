package it.polimi.ingsw.network.Events;

import java.util.ArrayList;
import java.util.List;

public class PrivateChatRequestEvent extends Event{
    private List<String> users;
    private ArrayList<ArrayList<String>> buffer;
    String sender;

    public PrivateChatRequestEvent(String user, List<String> users, ArrayList<ArrayList<String>> buffer, String sender) {
        super(user, EventType.PRIVATECHATINIT);
        this.users = users;
        this.buffer = buffer;
        this.sender = sender;
    }


    public ArrayList<ArrayList<String>> getBuffer() {
        return buffer;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getSender() {return sender;}
}
