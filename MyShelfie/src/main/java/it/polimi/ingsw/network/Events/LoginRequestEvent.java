package it.polimi.ingsw.network.Events;

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
