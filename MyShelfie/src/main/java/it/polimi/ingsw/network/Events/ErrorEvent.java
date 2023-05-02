package it.polimi.ingsw.network.Events;

public class ErrorEvent extends Event {

    private final String error;

    public ErrorEvent(String user, String error) {
        super(user, EventType.ERROR);
        this.error = error;
    }

    public String getError(){
        return error;
    }

    public String toString(){
        return "Error event for user " + getUser() + " of the type " + error;
    }
}
