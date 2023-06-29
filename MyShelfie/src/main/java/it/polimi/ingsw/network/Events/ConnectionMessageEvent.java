package it.polimi.ingsw.network.Events;

/**
 * Message to notify a connection to the other players.
 */
public class ConnectionMessageEvent extends Event {
    public final String connectedUser;


    public ConnectionMessageEvent(String user, String conUser) {
        super(user, EventType.USERCONNECTED);
        this.connectedUser = conUser;
    }

    public String getConnectedUser() {
        return connectedUser;
    }
}
