package it.polimi.ingsw.network.Events;

public class LoginReplyEvent {

    public final boolean usernameAccepted;
    public final boolean connectionSuccessful;


    public LoginReplyEvent(boolean usernameAccepted, boolean connectionSuccessful) {
        //manca un pezzo, capire bene
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
