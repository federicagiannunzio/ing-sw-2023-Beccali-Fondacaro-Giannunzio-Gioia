package it.polimi.ingsw.network.Events;

import java.util.List;

public class ChatRequestEvent extends Event{

    private List<String> users;
    private List<String> buffer;
    private boolean priv;

    public ChatRequestEvent(String user, List<String> users, boolean priv, List<String> buffer) {
        super(user, EventType.CHATINIT);
        this.users = users;
        this.priv = priv;
        this.buffer = buffer;
    }

    public boolean isPriv() {return priv;}

    public List<String> getBuffer() {
        return buffer;
    }

    public List<String> getUsers() {
        return users;
    }
}
