package it.polimi.ingsw.network.Events;

import java.util.ArrayList;
import java.util.List;

public class PrivateChatRequestEvent extends Event{
    private List<String> users;
    private ArrayList<ArrayList<String>> buffer;
    private boolean priv;
    String sender;

    public PrivateChatRequestEvent(String user, List<String> users, boolean priv, ArrayList<ArrayList<String>> buffer, String sender) {
        super(user, EventType.PRIVATECHATINIT);
        this.users = users;
        this.priv = priv;
        this.buffer = buffer;
        this.sender = sender;
    }

    public boolean isPriv() {return priv;}

    public ArrayList<ArrayList<String>> getBuffer() {
        return buffer;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getSender() {return sender;}
}
