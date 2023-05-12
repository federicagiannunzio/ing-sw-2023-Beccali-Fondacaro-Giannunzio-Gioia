package it.polimi.ingsw.network.Events;

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

