package it.polimi.ingsw.network.Events;

/**
 * Event to notify generic information to the user.
 */
public class GenericMessageEvent extends Event {
    private final String message;

    public GenericMessageEvent(String message) {
        super("server", EventType.GENERICMESSAGE);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

