package it.polimi.ingsw.network.Events;

public class LoginReplyEvent extends Event{

    public final boolean usernameAccepted;
    public final boolean connectionSuccessful;

    public LoginReplyEvent(String user, EventType eventType, boolean usernameAccepted, boolean connectionSuccessful) {
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
        
    }
}
