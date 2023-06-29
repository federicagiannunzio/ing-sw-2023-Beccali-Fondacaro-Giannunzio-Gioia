package it.polimi.ingsw.network.Events;

/**
 * Event used to confirm or discard a login request of a client.
 */
public class LoginReplyEvent extends Event{

    public final boolean usernameAccepted;
    public final boolean connectionSuccessful;

    public LoginReplyEvent(String user, boolean usernameAccepted, boolean connectionSuccessful) {
        super(user, EventType.LOGIN_REPLY);
        this.usernameAccepted = usernameAccepted;
        this.connectionSuccessful = connectionSuccessful;
    }


    public boolean isUsernameAccepted() {
        return usernameAccepted;
    }

    public boolean isConnectionSuccessful() {
        return connectionSuccessful;
    }

    public String toString() {
        return "The username is acceptable and the connection was successful.";
    }
}
