package it.polimi.ingsw.network.Events;

/**
 * Event used by the client to request a login to the server.
 */
public class LoginRequestEvent extends Event{

    public LoginRequestEvent(String user) {
        super(user, EventType.LOGIN_REQUEST);
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "nickname=" + getUser() +
                '}';
    }
}
