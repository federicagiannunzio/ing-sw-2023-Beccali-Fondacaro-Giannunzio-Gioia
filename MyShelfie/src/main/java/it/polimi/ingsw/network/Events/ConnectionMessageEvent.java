package it.polimi.ingsw.network.Events;

public class ConnectionMessageEvent extends Event {
    public final String connectedUser;


    public ConnectionMessageEvent(String user, String conUser) {
        super(user, EventType.USERDISCONNECTED);
        this.connectedUser = conUser;
    }

    public String getConnectedUser() {
        return connectedUser;
    }
}
